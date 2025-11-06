package com.example.guide_campus_app.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDao {
    @Query("SELECT * FROM rooms ORDER BY code ASC")
    List<RoomEntity> getAll();

    @Query("SELECT * FROM rooms WHERE code LIKE :pattern OR building LIKE :pattern OR description LIKE :pattern")
    List<RoomEntity> search(String pattern);

    @Query("SELECT * FROM rooms WHERE id = :id LIMIT 1")
    RoomEntity getById(int id);

    @Insert
    void insertAll(RoomEntity... rooms);

    @Query("SELECT COUNT(*) FROM rooms")
    int count();
}