package com.example.junior.Adapters;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.junior.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FieldsAdapter extends RecyclerView.Adapter<FieldsAdapter.FieldsViewHolder> {

    public HashMap<String, String> list;

    public List<String> keys = new ArrayList<>();

    public List<String> values = new ArrayList<>();

    public final Activity activity;

    public FieldsAdapter(HashMap<String, String> list, Activity activity) {
        this.list = list;
        this.keys.addAll(list.keySet());
        this.values.addAll(list.values());
        this.activity = activity;
    }

    public HashMap<String, String> getList() {
        list.clear();
        for (String key: keys) {
            list.put(key, values.get(keys.indexOf(key)));
        }
        return list;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void addToList(String key, String value, int position) {
        keys.add(position, key);
        notifyDataSetChanged();
        values.add(position, value);
    }

    @NonNull
    @Override
    public FieldsAdapter.FieldsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_input_field, parent, false);

        view.setLongClickable(true);
        return new FieldsAdapter.FieldsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull FieldsAdapter.FieldsViewHolder holder, int position) {

        switch (keys.get(holder.getAdapterPosition())) {
            case "Заголовок":
                holder.inputLayout.setHint("Заголовок");
                break;
            case "Подзаголовок":
                holder.inputLayout.setHint("Подзаголовок");
                break;
            case "Абзац":
                holder.inputLayout.setHint("Абзац");
                break;
            default: holder.inputLayout.setHint(fetchKeyOfCurrentField(holder.getAdapterPosition()));
        }

        holder.input.setText(values.get(holder.getAdapterPosition()));

        holder.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                values.set(holder.getAdapterPosition(), editable.toString());
            }
        });
    }

    public static class FieldsViewHolder extends RecyclerView.ViewHolder {

        // private TextView type;

        private TextInputLayout inputLayout;

        private EditText input;

        public FieldsViewHolder(View itemView) {
            super(itemView);
//            type = itemView.findViewById(R.id.type_of_field);
            inputLayout = itemView.findViewById(R.id.text_of_field);
            input = itemView.findViewById(R.id.text_input_of_field);
        }
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    private String fetchKeyOfCurrentField(int position) {
        String key = keys.get(position);

        String featuredKey = "";

        switch (key.charAt(0)) {
            case 'З':
                featuredKey = key.substring(0, 9);
                break;
            case 'П':
                featuredKey = key.substring(0, 12);
                break;
            case 'А':
                featuredKey = key.substring(0, 5);
                break;
            default: return key;
        }
        return featuredKey;
    }
}
