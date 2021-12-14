package com.example.projet.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.projet.model.Question;
import com.example.projet.model.User;

@Database(entities = {User.class, Question.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract QuestionDAO questionDao();
    public abstract UserDAO userDao();

}