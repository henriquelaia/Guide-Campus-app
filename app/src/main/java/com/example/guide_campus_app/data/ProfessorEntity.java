package com.example.guide_campus_app.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "professors")
public class ProfessorEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String department;
    public String office;
    public String email;
    public String phone;
    public String notes;

    // Novos campos
    public String courses;        // Cursos que leciona (ex: "Eng. Informática, Eng. Eletrotécnica")
    public String subjects;       // Cadeiras que leciona (ex: "Programação, Bases de Dados")
}