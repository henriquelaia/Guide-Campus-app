package com.example.guide_campus_app.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Esta classe serve para aceder aos dados das localizações na base de dados.
 */
@Dao
public interface LocationDao {
    /**
     * Este método devolve todas as localizações.
     */
    @Query("SELECT * FROM locations")
    List<LocationEntity> getAll();

    /**
     * Este método procura localizações por nome ou tipo.
     */
    @Query("SELECT * FROM locations WHERE name LIKE :pattern OR type LIKE :pattern")
    List<LocationEntity> searchByName(String pattern);

    /**
     * Este método devolve uma localização pelo seu ID.
     */
    @Query("SELECT * FROM locations WHERE id = :id LIMIT 1")
    LocationEntity getById(int id);

    /**
     * Este método insere novas localizações na base de dados.
     */
    @Insert
    void insertAll(LocationEntity... locations);

    /**
     * Este método conta o número de localizações.
     */
    @Query("SELECT COUNT(*) FROM locations")
    int count();
}
