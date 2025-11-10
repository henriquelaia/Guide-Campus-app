package com.example.guide_campus_app.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Esta classe serve para aceder aos dados dos professores na base de dados.
 */
@Dao
public interface ProfessorDao {
    /**
     * Este método devolve todos os professores.
     */
    @Query("SELECT * FROM professors ORDER BY name ASC")
    List<ProfessorEntity> getAll();

    /**
     * Este método procura professores por nome, departamento ou gabinete.
     */
    @Query("SELECT * FROM professors WHERE name LIKE :pattern OR department LIKE :pattern OR office LIKE :pattern")
    List<ProfessorEntity> search(String pattern);

    /**
     * Este método devolve um professor pelo seu ID.
     */
    @Query("SELECT * FROM professors WHERE id = :id LIMIT 1")
    ProfessorEntity getById(int id);

    /**
     * Este método devolve uma lista de departamentos únicos.
     */
    @Query("SELECT DISTINCT department FROM professors ORDER BY department ASC")
    List<String> getUniqueDepartments();

    /**
     * Este método devolve os professores de um departamento.
     */
    @Query("SELECT * FROM professors WHERE department = :department ORDER BY name ASC")
    List<ProfessorEntity> getByDepartment(String department);

    /**
     * Este método insere novos professores na base de dados.
     */
    @Insert
    void insertAll(ProfessorEntity... professors);

    /**
     * Este método conta o número de professores.
     */
    @Query("SELECT COUNT(*) FROM professors")
    int count();
}
