package com.example.assessment8.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Drink.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DrinkDao drinkDao();
}
