package com.example.lab11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab11.Adapter.NoteAdapter;
import com.example.lab11.DBHandle.DBHandler;
import com.example.lab11.Model.Note;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    private EditText uTitle, uContent;
    private TextView uId;
    private Button uUpdate, uCancel;
    private DBHandler dbHandler;
    String noteTitle, noteContent, noteID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        uTitle = findViewById(R.id.edTitle);
        uContent = findViewById(R.id.edContent);
        uUpdate = findViewById(R.id.btUpdate);
        uCancel = findViewById(R.id.btUpdateCancel);
        uId = findViewById(R.id.tvId);

        dbHandler = new DBHandler(UpdateActivity.this);

        noteID = getIntent().getStringExtra("title");
        noteTitle = getIntent().getStringExtra("content");
        noteContent = getIntent().getStringExtra("id");


        uTitle.setText(noteTitle);
        uContent.setText(noteContent);

        uUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.updateNote(noteID, uTitle.getText().toString(), uContent.getText().toString());
                Toast.makeText(UpdateActivity.this, "Note Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
        uCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(UpdateActivity.this, "Note cancel..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);
                // launching our main activity.


            }
        });

        }


}
