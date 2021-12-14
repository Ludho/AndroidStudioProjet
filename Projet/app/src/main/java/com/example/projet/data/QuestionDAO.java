package com.example.projet.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projet.model.Question;
import com.example.projet.model.User;

import java.util.List;

@Dao
public interface QuestionDAO {

    @Query("SELECT * FROM QUESTION WHERE theme=:theme")
    List<Question> getQuestionsTheme(String theme);

    @Query("DELETE FROM QUESTION")
    void nukeQuestion();

    @Insert
    void insertQuestion(Question question);

    @Update
    void updateQuestion(Question question);

    @Delete
    void deleteQuestion(Question question);

    @Query("SELECT * FROM QUESTION")
    List<Question> getAllQuestions();
}
