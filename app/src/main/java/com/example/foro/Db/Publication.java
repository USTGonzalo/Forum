package com.example.foro.Db;

public class Publication {
    private long id;
    private String title;
    private String message;
    private int userId;
    private String time;
    private String userName; // Nuevo campo

    public Publication(long id, String title, String message, int userId, String time, String userName) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.userId = userId;
        this.time = time;
        this.userName = userName;
    }

    // Getters
    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public int getUserId() { return userId; }
    public String getTime() { return time; }
    public String getUserName() { return userName; }
}