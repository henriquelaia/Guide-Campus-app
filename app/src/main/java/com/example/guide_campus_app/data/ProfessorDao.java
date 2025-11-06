package com.example.guide_campus_app.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProfessorDao {
    @Query("SELECT * FROM professors ORDER BY name ASC")
    List<ProfessorEntity> getAll();

    @Query("SELECT * FROM professors WHERE name LIKE :pattern OR department LIKE :pattern OR office LIKE :pattern")
    List<ProfessorEntity> search(String pattern);

    @Query("SELECT * FROM professors WHERE id = :id LIMIT 1")
    ProfessorEntity getById(int id);

    @Query("SELECT DISTINCT department FROM professors ORDER BY department ASC")
    List<String> getUniqueDepartments();

    @Query("SELECT * FROM professors WHERE department = :department ORDER BY name ASC")
    List<ProfessorEntity> getByDepartment(String department);

    @Insert
    void insertAll(ProfessorEntity... professors);

    @Query("SELECT COUNT(*) FROM professors")
    int count();
}