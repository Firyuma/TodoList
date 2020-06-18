package com.example.todolist.Model;

import java.io.Serializable;

import io.realm.RealmObject;

public class Note extends RealmObject implements Serializable {
    private String Title;
    private String Description;
    private String Create;
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Note(String title, String description, String create, String id) {
        Title = title;
        Description = description;
        Create = create;
        Id = id;
    }

    public Note() {
    }

    public Note(String id) {

        Id = id;
    }



    public void copyFrom(Note newNote){
        newNote.setId(newNote.getId());
        newNote.setDescription(newNote.getDescription());
        newNote.setTitle(newNote.getTitle());
        newNote.setCreate(newNote.getCreate());
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCreate() {
        return Create;
    }

    public void setCreate(String create) {
        Create = create;
    }


    public Note(String title, String description, String create) {
        Title = title;
        Description = description;
        Create = create;

    }
}