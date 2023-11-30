package com.example.junior.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.junior.Classes.UsersDocument;
import com.example.junior.R;

import java.util.List;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.FilesViewHolder> {

    List<String> list;
//
//    public FilesAdapter(List<UsersDocument> list) {
//
//        list.add(new UsersDocument());
//        list.forEach((e)->this.list.add(e.nameOfDocument));
//    }
    public FilesAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FilesAdapter.FilesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_to_files_list, parent, false);

        view.setLongClickable(true);
        return new FilesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilesAdapter.FilesViewHolder holder, int position) {

        holder.name.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class FilesViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public FilesViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_of_file_in_list);
        }
    }
}
