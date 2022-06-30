package com.example.sp18_bse_024;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteTable;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE Username LIKE :Name")
    User findByName(String Name);

    @Insert
    long insertOne(User user);

    @Update
    void updateOne(User user);

    @Query("DELETE FROM user WHERE Username LIKE :Name")
    void del(String Name);

    @Query("DELETE FROM user")
    void DeleteAll();
}
