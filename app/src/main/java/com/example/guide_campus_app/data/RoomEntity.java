package com.example.guide_campus_app.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rooms")
public class RoomEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String code;
    public String name;
    public String campus; // Novo campo para o Polo
    public String building;
    public String type;   // Novo campo para o tipo de sala
    public String floor;
    public String description;
    public Double latitude;
    public Double longitude;
}