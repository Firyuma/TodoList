package com.example.todolist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Model.Note;
import com.example.todolist.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> noteData;
    private OnItemClickListener listener;

    public NoteAdapter(List<Note> noteData, OnItemClickListener listener) {
        this.noteData = noteData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindView(noteData.get(i));
    }

    public void refreshData(List<Note> newList){
        noteData.clear();
        noteData.addAll(newList);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return noteData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvCreateDate;
        ImageView imvDelete;

        private Note note;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            init(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(note);
                }
            });

            imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnDeleteClick(note.getId());
                }
            });
        }

        private void init(View itemView) {
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCreateDate = itemView.findViewById(R.id.tv_create);
            imvDelete = itemView.findViewById(R.id.image_view);
        }

        private void bindView(Note note) {
            this.note = note;

            tvTitle.setText(note.getTitle());
            tvCreateDate.setText(note.getCreate());
        }
    }

}

