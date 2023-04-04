package com.example.lab11.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab11.DBHandle.DBHandler;
import com.example.lab11.MainActivity;
import com.example.lab11.Model.Note;
import com.example.lab11.R;
import com.example.lab11.UpdateActivity;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    // variable for our array list and context
    private ArrayList<Note> noteModalArrayList;
    private Context context;
    private DBHandler dbHandler;
    int noteID;

    // constructor
    public NoteAdapter(ArrayList<Note> noteModalArrayList, Context context) {
        this.noteModalArrayList = noteModalArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout. note_layout, parent, false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        Note modal = noteModalArrayList.get(position);
        holder.noteId.setText(modal.getId());
        holder.noteTitle.setText(modal.getTitle());
        holder.noteContent.setText(modal.getContent());
        holder.noteDate.setText(modal.getDate());

        noteID = Integer.parseInt(holder.noteTitle.getText().toString());


        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.setHeaderTitle("Select");
                contextMenu.add(0,R.id.ctDelete,0,"Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                       dbHandler = new DBHandler(context);

                        dbHandler.deleteNote(noteID);
                        Toast.makeText(context, "Delete "+ noteID , Toast.LENGTH_SHORT).show();
                        noteModalArrayList.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                        return false;
                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateActivity.class);
                Intent ii = new Intent(context, MainActivity.class);

                // below we are passing all our values.
                i.putExtra("date",modal.getDate());
                i.putExtra("id",modal.getId());
                i.putExtra("title", modal.getTitle());
                i.putExtra("content", modal.getContent());
                ii.putExtra("id",modal.getId());



                // starting our activity.
                context.startActivity(i);
            }
        });

    }
    @Override
    public int getItemCount() {
        // returning the size of our array list
        return noteModalArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView noteTitle, noteContent,noteId, noteDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
           noteId = itemView.findViewById(R.id.Content);
           noteTitle = itemView.findViewById(R.id.tvId);
           noteContent = itemView.findViewById(R.id.Title);
           noteDate = itemView.findViewById(R.id.txtDate);



        }
    }



}

