package com.example.guide_campus_app.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Esta classe serve para aceder aos dados das salas na base de dados.
 */
@Dao
public interface RoomDao {
    /**
     * Este método devolve todas as salas.
     */
    @Query("SELECT * FROM rooms ORDER BY code ASC")
    List<RoomEntity> getAll();

    /**
     * Este método procura salas por código, edifício, descrição ou nome.
     */
    @Query("SELECT * FROM rooms WHERE code LIKE :pattern OR building LIKE :pattern OR description LIKE :pattern OR name LIKE :pattern")
    List<RoomEntity> search(String pattern);

    /**
     * Este método devolve uma sala pelo seu ID.
     */
    @Query("SELECT * FROM rooms WHERE id = :id LIMIT 1")
    RoomEntity getById(int id);

    /**
     * Este método devolve uma lista de polos (campus) únicos.
     */
    @Query("SELECT DISTINCT campus FROM rooms ORDER BY campus ASC")
    List<String> getUniqueCampuses();

    /**
     * Este método insere novas salas na base de dados.
     */
    @Insert
    void insertAll(RoomEntity... rooms);

    /**
     * Este método conta o número de salas.
     */
    @Query("SELECT COUNT(*) FROM rooms")
    int count();
}
