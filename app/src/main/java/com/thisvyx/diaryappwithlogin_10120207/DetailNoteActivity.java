package com.thisvyx.diaryappwithlogin_10120207;

/*
NIM : 10120207
Nama : Vikri Frediansyah
Kelas : IF 5
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailNoteActivity extends AppCompatActivity {

    ImageButton btnSaveNote,btnDeleteNote;
    EditText etTittle, etContent, etKategori;
    TextView tvTittle;
    String tittle, content, kategori,docId;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);

        etTittle = findViewById(R.id.et_note_tittle);
        etKategori = findViewById(R.id.et_kategori);
        etContent = findViewById(R.id.et_content_note);

        tvTittle = findViewById(R.id.tv_tittle);
        btnDeleteNote = findViewById(R.id.btn_delete_note);

        //receive data
        tittle = getIntent().getStringExtra("tittle");
        kategori = getIntent().getStringExtra("kategori");
        content = getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");

        if (docId != null && !docId.isEmpty()){
            isEditMode = true;
        }

        etTittle.setText(tittle);
        etKategori.setText(kategori);
        etContent.setText(content);
        if (isEditMode){
            tvTittle.setText("Edit Your Diary");
            btnDeleteNote.setVisibility(View.VISIBLE);
        }

        btnSaveNote = findViewById(R.id.btn_save_note);
        btnSaveNote.setOnClickListener( (v)-> saveNote());

        btnDeleteNote.setOnClickListener((v)->deleteNoteFromFirebase());

    }

    private void deleteNoteFromFirebase() {
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForNote().document(docId);

        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(DetailNoteActivity.this,"Berhasil Menghapus Catatan",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    //failed to delete
                    Toast.makeText(DetailNoteActivity.this,"Gagal Menghapus Catatan",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveNote() {
        String noteTittle = etTittle.getText().toString();
        String noteKategori = etKategori.getText().toString();
        String noteContent = etContent.getText().toString();

        if(noteTittle == null || noteTittle.isEmpty()){
            etTittle.setError("Tittle harus diisi");
            return;
        }

        Note note = new Note();
        note.setTittle(noteTittle);
        note.setKategori(noteKategori);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        saveNoteToFirebase(note);
    }

    private void saveNoteToFirebase(Note note) {
        DocumentReference documentReference;
        if (isEditMode){
            //mode update
            documentReference = Utility.getCollectionReferenceForNote().document(docId);
        }else {
            //mode create
        documentReference = Utility.getCollectionReferenceForNote().document();
        }

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(DetailNoteActivity.this,"Berhasil Menambah Catatan",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    //failed to add
                    Toast.makeText(DetailNoteActivity.this,"Gagal Menambah Catatan",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}