package com.example.guide_campus_app.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    @Query("SELECT * FROM locations")
    List<LocationEntity> getAll();

    @Query("SELECT * FROM locations WHERE name LIKE :pattern OR type LIKE :pattern")
    List<LocationEntity> searchByName(String pattern);

    @Query("SELECT * FROM locations WHERE id = :id LIMIT 1")
    LocationEntity getById(int id);

    @Insert
    void insertAll(LocationEntity... locations);

    @Query("SELECT COUNT(*) FROM locations")
    int count();
}