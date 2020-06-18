package com.example.todolist.Adapter;

import com.example.todolist.Model.Note;

public interface OnItemClickListener {
    void OnItemClick(Note note);
    void OnLongItemClick(Note note);
    void OnDeleteClick(String id);
}

