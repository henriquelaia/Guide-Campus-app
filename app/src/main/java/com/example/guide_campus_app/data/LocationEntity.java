package com.example.guide_campus_app.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Esta classe serve para representar uma localização na base de dados.
 */
@Entity(tableName = "locations")
public class LocationEntity {
    
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String type;
    public String campus;
    public String shortDescription;
    public String details;
    public double latitude;
    public double longitude;
    public String operatingHours;
    public String hoursNotes;
    public String imageName;

    /**
     * Este método devolve o nome da localização.
     * É usado para mostrar o nome em listas e menus.
     */
    @Override
    public String toString() {
        return name != null ? name : "";
    }
}
