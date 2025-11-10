package com.example.guide_campus_app.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Esta classe serve para representar um professor na base de dados.
 */
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
    public String courses;
    public String subjects;
}
