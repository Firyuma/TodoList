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

public class EditActivity extends AppCompatActivity {

    EditText edtTitle;
    EditText edtDescription;
    Button btnSave;
    Button btnCancel;

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit);

        init();
        addListener();
    }

    private void init() {
        edtTitle = findViewById(R.id.edit_title);
        edtDescription = findViewById(R.id.edit_description);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

        note = (Note) getIntent().getSerializableExtra("note_data");

        edtTitle.setText(note.getTitle());
        edtDescription.setText(note.getDescription());
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
                String title = edtTitle.getText().toString();
                String description = edtDescription.getText().toString();
                if (title.isEmpty()) {
                    showToast("Bạn chưa nhập tiêu đề!");
                    return;
                }
                if (description.isEmpty()) {
                    showToast("Bạn chưa nhập nội dung!");
                    return;
                }

                note.setTitle(title);
                note.setDescription(description);

                CloudFireStoreContext.getInstance().updateNote(note.getId(),note, new CloudFireStoreContext.ModifyCallBack() {
                    @Override
                    public void onSuccess() {
                        showToast("Chỉnh sửa thành công!");
                        finish();
                    }

                    @Override
                    public void onFail(String message) {
                        showToast("Chỉnh sửa thất bại!");
                    }
                });
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
