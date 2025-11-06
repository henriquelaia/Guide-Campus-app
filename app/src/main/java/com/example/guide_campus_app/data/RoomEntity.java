package com.example.guide_campus_app.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rooms")
public class RoomEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String code;
    public String building;
    public String floor;
    public String description;
    public Double latitude;
    public Double longitude;

    // Novo campo
    public String imageName; // Nome da imagem específica da sala (ex: "sala_fe_8_1")
}