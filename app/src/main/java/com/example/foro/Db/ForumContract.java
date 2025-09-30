package com.example.foro.Db;

public class ForumContract {

    private ForumContract()
    {

    }

    public static class ForumEntry
    {
        public static final String TABLE_NAME = "forum";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_MESSAGE = "message";
    }
}
