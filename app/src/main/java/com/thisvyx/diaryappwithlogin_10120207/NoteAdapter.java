package com.thisvyx.diaryappwithlogin_10120207;

/*
NIM : 10120207
Nama : Vikri Frediansyah
Kelas : IF 5
 */


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.SimpleDateFormat;

public class NoteAdapter extends FirestoreRecyclerAdapter <Note, NoteAdapter.NoteViewHolder> {

    Context context;

    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        holder.tittleView.setText(note.tittle);
        holder.kategoriView.setText(note.kategori);
        holder.contentView.setText(note.content);
        holder.timeStampView.setText(Utility.timeStampToString(note.timestamp));

        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context, DetailNoteActivity.class);
            intent.putExtra("tittle",note.tittle);
            intent.putExtra("kategori",note.kategori);
            intent.putExtra("content",note.content);
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId",docId);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item,parent,false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView tittleView, kategoriView,contentView, timeStampView;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tittleView = itemView.findViewById(R.id.tv_note_tittle);
            kategoriView = itemView.findViewById(R.id.tv_note_kategori);
            contentView = itemView.findViewById(R.id.tv_note_content);
            timeStampView = itemView.findViewById(R.id.tv_note_time_stamp);

        }
    }
}
