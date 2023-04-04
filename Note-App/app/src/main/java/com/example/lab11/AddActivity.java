package com.example.lab11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab11.DBHandle.DBHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    private EditText edTitle, edContent, edDate;
    private Button btCancel, btSave;
    private DBHandler dbHandler;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);

        edTitle = findViewById(R.id.edTitle);
        edContent = findViewById(R.id.edContent);
        edDate = findViewById(R.id.edDate);

        btCancel = findViewById(R.id.btCancel);
        btSave = findViewById(R.id.btSave);

        dbHandler = new DBHandler(AddActivity.this);

        java.util.Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat.format(currentDate);

        edDate.setText(dateString);


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteTitle = edTitle.getText().toString();
                String noteContent = edContent.getText().toString();
                String noteDate = edDate.getText().toString();
//                System.out.println(noteDate);
//
//                String tDate = "Dkhang";



                if (noteTitle.isEmpty()){
                    Toast.makeText(AddActivity.this, "Title can not be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHandler.addNewNote(noteTitle, noteContent, noteDate);

                Toast.makeText(AddActivity.this, "Note has been added.", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);



            }
        });

    }
}
