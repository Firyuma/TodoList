package com.example.todolist.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.todolist.Adapter.NoteAdapter;
import com.example.todolist.Adapter.OnItemClickListener;
import com.example.todolist.FireStoreContext.CloudFireStoreContext;
import com.example.todolist.Model.Note;
import com.example.todolist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    RecyclerView recyclerView;
    List<Note> noteData;
    NoteAdapter noteAdapter;
    FloatingActionButton buttonFloat;
    private Note note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllNote();
    }

    private void getAllNote() {
        CloudFireStoreContext.getInstance().getAllNote(new CloudFireStoreContext.FireBaseCallback() {
            @Override
            public void onSuccess(ArrayList<Note> notes) {
                noteData.clear();
                noteData.addAll(notes);
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String message) {
            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.rv_notes);
        buttonFloat = findViewById(R.id.btn_float);

        noteData = new ArrayList<>();
        noteAdapter = new NoteAdapter(noteData, this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    private void addListener() {
        buttonFloat.setOnClickListener(v -> goToAddActivity());
    }

    private void goToAddActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    private void goToUpdateActivity(Note note) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("note_data", note);
        startActivity(intent);
    }

    @Override
    public void OnItemClick(Note note) {
        goToUpdateActivity(note);
    }

    @Override
    public void OnDeleteClick(final String id) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa!")
                .setMessage("Bạn có chắc chắn muốn xóa ghi chú này không?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Có", (dialog, which) -> {
                    CloudFireStoreContext.getInstance().deleteNote(id, new CloudFireStoreContext.ModifyCallBack() {
                        @Override
                        public void onSuccess() {
                            getAllNote();
                        }

                        @Override
                        public void onFail(String message) {
                        }
                    });
                })
                .setNegativeButton("Không", null)
                .show();
    }



    @Override
    public void OnLongItemClick(Note note) {

    }


}



