package com.example.todolist.DataBase;

import com.example.todolist.Model.Note;

import java.util.ArrayList;

public class NoteUtils {
    // singleton
    private ArrayList<Note> noteData;

    private static NoteUtils noteUtils;
    private NoteUtils() {
        noteData = new ArrayList<>();
    }
    public static NoteUtils getInstance(){
        if(noteUtils == null){
            noteUtils = new NoteUtils();
        }
        return noteUtils;
    }

//    public ArrayList<Note> getAll() {
//        return noteData;
//    }
//
//    public void addNote(Note note) {
//        if (noteData.isEmpty()) {
//            note.setId(0);
//        } else {
//            int lastNoteId = noteData.get(noteData.size() - 1).getId();
//            note.setId(lastNoteId + 1);
//        }
//        noteData.add(note);
//    }
//
//    public void updateNote(int id, Note newNote) {
//        Note oldNote = getNoteById(id);
//        if (oldNote != null) {
//            oldNote.setTitle(newNote.getTitle());
//            oldNote.setDescription(newNote.getDescription());
//        }
//    }
//
//    public void deleteNote(int id) {
//        for (int i = 0; i < noteData.size(); i++) {
//            if (noteData.get(i).getId() == id) {
//                noteData.remove(i);
//                return;
//            }
//        }
//    }
//
//    private Note getNoteById(int id) {
//        for (Note note : noteData) {
//            if (note.getId() == id) return note;
//        }
//        return null;
//    }
}
