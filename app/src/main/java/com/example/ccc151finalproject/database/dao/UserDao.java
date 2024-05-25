package com.example.ccc151finalproject.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ccc151finalproject.database.models.UserModel;

import java.util.List;

/**
 *      Data Access Object that allows me to do basic database functionalities for the User entity
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<UserModel> getAllUsers();

    @Query("SELECT * FROM User WHERE id = :userId")
    UserModel getUserById(int userId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(UserModel user);

    @Update
    void updateUser(UserModel user);

    @Delete
    void deleteUser(UserModel user);
}
