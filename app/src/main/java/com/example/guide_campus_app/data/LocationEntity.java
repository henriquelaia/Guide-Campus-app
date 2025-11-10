package com.example.guide_campus_app.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "locations")
public class LocationEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String type;
    public String campus; // Polo I, II, III, IV
    public String shortDescription;
    public String details;
    public double latitude;
    public double longitude;

    // Novos campos
    public String operatingHours; // Ex: "Seg-Sex: 09h-18h"
    public String hoursNotes;     // Ex: "Horário de Verão: 09h-13h"
    public String imageName;      // Nome do drawable (ex: "ubi_reitoria")

    @Override
    public String toString() {
        return name != null ? name : "";
    }
}
