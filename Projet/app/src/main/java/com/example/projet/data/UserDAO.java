package com.example.projet.data;

import com.example.projet.model.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM USER WHERE prenom =:prenom and nom =:nom")
    User getUser(String nom,String prenom);

    @Insert
    void insertUser(User user);

    @Query("DELETE FROM USER")
    void nukeUser();

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM USER ORDER BY id DESC")
    List<User> getAllusers();

}
