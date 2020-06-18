package com.example.todolist.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.FireStoreContext.CloudFireStoreContext;
import com.example.todolist.Model.Note;
import com.example.todolist.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity  {
    EditText editTitle;
    EditText editDescription;
    Button btnSave;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit);
        init();
        addListener();
    }

    private void init() {
        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_description);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

    }

    private void addListener() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String description = editDescription.getText().toString();
                if (title.isEmpty()) {
                    showToast("Enter title");
                }
                if (description.isEmpty()) {
                    showToast("Enter desciption");
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Note note = new Note(title, description, simpleDateFormat.format(new Date()));
                CloudFireStoreContext.getInstance().addNote(note, new CloudFireStoreContext.ModifyCallBack() {
                    @Override
                    public void onSuccess() {
                        showToast("Thêm mới thành công!");
                        finish();
                    }

                    @Override
                    public void onFail(String message) {
                        showToast("Thêm mới thất bại!");
                    }
                });
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
