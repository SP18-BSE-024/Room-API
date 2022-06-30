package com.example.sp18_bse_024;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1 )
public abstract class Userdatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
