package com.example.user.testcase;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by User on 23/08/2017.
 */
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}
