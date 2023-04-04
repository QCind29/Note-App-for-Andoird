package com.example.lab11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lab11.Adapter.NoteAdapter;
import com.example.lab11.DBHandle.DBHandler;
import com.example.lab11.Model.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Note> noteArrayList;
    private DBHandler dbHandler;
    private NoteAdapter noteAdapter;
    private RecyclerView noteRV;
    String noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteArrayList =new ArrayList<>();
        dbHandler = new DBHandler(MainActivity.this);

        noteArrayList = dbHandler.readNote();

        noteAdapter = new NoteAdapter(noteArrayList, MainActivity.this);
        noteRV = findViewById(R.id.NoteRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        noteRV.setLayoutManager(linearLayoutManager);

        noteRV.setAdapter(noteAdapter);
//        registerForContextMenu(noteRV);


//        noteID = getIntent().getStringExtra("id");
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        getMenuInflater().inflate(R.menu.context_menu,menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.ctDelete:
//               // dbHandler.deleteNote(noteID);
//                Toast.makeText(this, "Delete"+ noteID, Toast.LENGTH_SHORT).show();
//                return true;
//        }
//        return super.onContextItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
//
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.Add:
                startActivity(new Intent(MainActivity.this, AddActivity.class));
        }
        return super .onOptionsItemSelected(item);
    }
}