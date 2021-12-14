package com.example.projet.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projet.model.Question;
@Database(entities = {Question.class},version = 1,exportSchema = false)
public abstract class QuestionDataBase extends RoomDatabase {

    public abstract QuestionDAO getQuestionDAO();

}
