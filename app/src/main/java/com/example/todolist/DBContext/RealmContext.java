package com.example.todolist.DBContext;

import io.realm.Realm;

public class RealmContext {
    private Realm realm;
    private static RealmContext realmContext;

    private RealmContext() {
        realm = Realm.getDefaultInstance();
    }

    public static RealmContext getInstance() {
        if (realmContext == null) {
            realmContext = new RealmContext();
        }
        return realmContext;
    }

//    public List<Note> getAllNote() {
//        return realm.copyFromRealm(realm.where(Note.class).findAll());
//    }
//
//    public void addNote(Note newNote) {
//        Number maxId = realm.where(Note.class).max("id");
//        if (maxId == null) {
//            newNote.setId(0);
//        } else {
//            newNote.setId(maxId.intValue() + 1);
//        }
//
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(newNote);
//        realm.commitTransaction();
//    }
//
//    public void updateNote(Note newNote) {
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(newNote);
//        realm.commitTransaction();
//    }
//
//    public void deleteNote(int id) {
//        Note note = realm.where(Note.class).equalTo("id", id).findFirst();
//        if (note == null) return;
//
//        realm.beginTransaction();
//        note.deleteFromRealm();
//        realm.commitTransaction();
//    }



}
