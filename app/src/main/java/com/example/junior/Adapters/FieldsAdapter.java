package com.example.junior.Adapters;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.junior.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FieldsAdapter  extends RecyclerView.Adapter<FieldsAdapter.FieldsViewHolder>  {

    public HashMap<String, String> getList() {
        return list;
    }

    public void setList(HashMap<String, String> list) {
        this.list = list;
    }

    HashMap<String, String> list;
    List<String> keys = new ArrayList<>();
    List<String> values = new ArrayList<>();

    public final Activity activity;

public void addToList(String key, String value){
    list.put(key, value);
    keys.add(key);
    values.add(value);
    notifyDataSetChanged();
}
    public FieldsAdapter(HashMap<String, String> list, Activity activity) {
        this.list = list;
        keys.addAll(list.keySet());
        this.values.addAll(list.values());
        this.activity = activity;
    }
    @NonNull
    @Override
    public FieldsAdapter.FieldsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_to_new_text_field, parent, false);

        view.setLongClickable(true);
        return new FieldsAdapter.FieldsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldsAdapter.FieldsViewHolder holder, int position) {


    if (keys.get(position).contains("Заголовок")) holder.inputLayout.setHint("Заголовок");
   else if (keys.get(position).contains("Подзаголовок")) holder.inputLayout.setHint("Подзаголовок");
    else if (keys.get(position).contains("Абзац")) holder.inputLayout.setHint("Абзац");
    else holder.inputLayout.setHint(keys.get(position));

    holder.input.setText(values.get(position));
        int pos = position;
        holder.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                values.set(pos, editable.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class FieldsViewHolder extends RecyclerView.ViewHolder {
//TextView type;
EditText input;
TextInputLayout inputLayout;
        public FieldsViewHolder(View itemView) {
            super(itemView);
//            type = itemView.findViewById(R.id.type_of_field);
            input = itemView.findViewById(R.id.text_input_of_field);
            inputLayout = itemView.findViewById(R.id.text_of_field);


        }
    }

}
