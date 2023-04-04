package com.example.lab11.Model;

public class Note {
    public Note(String title, String content, String id, String date) {
        Id = id;
        Title = title;
        Content = content;
        Date = date;
    }
    String Id;
    String Title;


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    String Date;
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {Id = id;}

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    String Content;
}
