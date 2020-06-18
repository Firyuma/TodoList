package com.example.todolist.FireStoreContext;

import androidx.annotation.NonNull;

import com.example.todolist.Model.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CloudFireStoreContext {

    public interface FireBaseCallback{
void onSuccess(ArrayList<Note> notes);
void onFail(String message);
    }

    public interface ModifyCallBack{
        void onSuccess();
        void onFail(String message);
    }
    FirebaseFirestore firebaseFirestore;
    private CloudFireStoreContext(){
        firebaseFirestore = FirebaseFirestore.getInstance();
    }
    private static CloudFireStoreContext instance;
    public static CloudFireStoreContext getInstance() {
        if (instance== null){
            instance = new CloudFireStoreContext();
        }
        return instance;
    }

    public void getAllNote(FireBaseCallback callback){
firebaseFirestore.collection("notes")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<Note> notes = new ArrayList<>();
                if (task.isSuccessful() && task.getResult() != null){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String id = document.getId();
                        String title = (String) document.getData().get("title");
                        String description = (String) document.getData().get("description");
                        String createDate = (String) document.getData().get("createDate");
                        notes.add(new Note(id, title, description, createDate));
                    }
                    callback.onSuccess(notes);
            }
                    else {callback.onFail(task.getException().toString());

                }
            }
        });

    }
    public void addNote(Note newNote, ModifyCallBack callBack) {
        Map<String, String> noteMap = new HashMap<>();
        noteMap.put("title", newNote.getTitle());
        noteMap.put("description", newNote.getDescription());
        noteMap.put("createDate", newNote.getCreate());

        firebaseFirestore.collection("notes")
                .add(noteMap)
                .addOnSuccessListener(documentReference -> {
                    callBack.onSuccess();
                })
                .addOnFailureListener(e -> {
                    callBack.onFail(e.getMessage());
                });
    }

    public void updateNote(String noteID,Note newNote, ModifyCallBack callBack) {

        Map<String, Object> noteMap = new HashMap<>();
        noteMap.put("title", newNote.getTitle());
        noteMap.put("description", newNote.getDescription());
        noteMap.put("createDate", newNote.getCreate());

        firebaseFirestore.collection("notes")
                .document(noteID)
                .update(noteMap)
                .addOnSuccessListener(aVoid -> callBack.onSuccess())
                .addOnFailureListener(e -> callBack.onFail(e.getMessage()));
    }

    public void deleteNote(String id, ModifyCallBack callBack) {
        firebaseFirestore.collection("notes")
                .document(id)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    callBack.onSuccess();
                })
                .addOnFailureListener(e -> {
                    callBack.onFail(e.getMessage());
                });
    }

}
