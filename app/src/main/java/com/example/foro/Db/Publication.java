package com.example.foro.Db;

public class Publication {
    private long id;
    private String title;
    private String message;
    private int userId;
    private String time;

    public Publication(long id, String title, String message, int userId, String time) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.userId = userId;
        this.time = time;
    }

    // Getters
    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public int getUserId() { return userId; }
    public String getTime() { return time; }
}
