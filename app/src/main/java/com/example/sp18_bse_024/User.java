package com.example.sp18_bse_024;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    public int getID() {
        return ID;
    }

    @NonNull

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "ID")
    public int ID;



    @ColumnInfo(name = "UserName")
    public String UserName ;

    @ColumnInfo(name = "Password")
    public String Password;

}
