package com.example.foro.Db;

public class UsersContract {

    private UsersContract()
    {

    }

    public static class UsersEntry
    {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "firstname";
        public static final String COLUMN_LAST = "lastname";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_PASS = "pass";
        public static final String COLUMN_DATE = "datebirth";
    }
}
