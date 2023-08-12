package com.thisvyx.diaryappwithlogin_10120207;

/*
NIM : 10120207
Nama : Vikri Frediansyah
Kelas : IF 5
 */


import com.google.firebase.Timestamp;

public class Note {

    Timestamp timestamp;
    String tittle;
    String kategori;
    String content;

    public Note() {
    }


    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
