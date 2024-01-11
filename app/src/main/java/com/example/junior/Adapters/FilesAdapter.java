package com.example.junior.Adapters;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.StringUtil;
import androidx.room.util.TableInfo;

import com.example.junior.Activities.App;
import com.example.junior.Activities.NewActivity;
import com.example.junior.Activities.ViewActivity;
import com.example.junior.Classes.UsersDocument;
import com.example.junior.R;
import com.itextpdf.io.util.StreamUtil;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.FilesViewHolder> {

    List<String> list;
    Activity activity;
//
//    public FilesAdapter(List<UsersDocument> list) {
//
//        list.add(new UsersDocument());
//        list.forEach((e)->this.list.add(e.nameOfDocument));
//    }
    public FilesAdapter(List<String> list, Activity activity) {
        this.list = list;
        this.activity = activity;
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
String filename = list.get(position);
//String filename = list.get(position).substring(0, list.get(position).length() - 4);
        holder.name.setText(filename);
        holder.name.setClickable(true);
        holder.name.setOnClickListener(gh->{
           goFile(filename);

    });
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
    private void goFile(String fileName) {

        File file = new File(App.directory, fileName );
//        File file = new File(fileName);
        Intent intent = new Intent(activity, ViewActivity.class);

        intent.putExtra(App.EXTRA_TO_SEE_DOCUMENT, Uri.fromFile(file));
        intent.setType("application/data");

        activity.startActivity(intent);
        activity.finish();
    }
}

