package com.example.guide_campus_app.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Esta classe serve para representar uma sala na base de dados.
 */
@Entity(tableName = "rooms")
public class RoomEntity {
    
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String code;
    public String name;
    public String campus;
    public String building;
    public String type;
    public String floor;
    public String description;
    public Double latitude;
    public Double longitude;
}
