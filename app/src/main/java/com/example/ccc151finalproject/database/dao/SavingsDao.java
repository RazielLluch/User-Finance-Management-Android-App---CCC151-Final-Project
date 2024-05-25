package com.example.ccc151finalproject.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ccc151finalproject.database.models.SavingsModel;

import java.util.List;

/**
 *      Data Access Object that allows me to do basic database functionalities for the Savings entity
 */
@Dao
public interface SavingsDao {
    @Query("SELECT * FROM Savings")
    List<SavingsModel> getAllSavings();

    @Query("SELECT * FROM Savings WHERE id = :savingsId")
    SavingsModel getSavingsById(int savingsId);

    @Insert
    void insertSavings(SavingsModel savings);

    @Update
    void updateSavings(SavingsModel savings);

    @Delete
    void deleteSavings(SavingsModel savings);
}
