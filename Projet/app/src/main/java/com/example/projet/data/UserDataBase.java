package com.example.projet.data;
import com.example.projet.model.User;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDAO getUserDAO();

}
