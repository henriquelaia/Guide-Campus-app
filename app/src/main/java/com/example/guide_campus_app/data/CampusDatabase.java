package com.example.guide_campus_app.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LocationEntity.class, ProfessorEntity.class, RoomEntity.class}, version = 4, exportSchema = false) // VERSÃO ATUALIZADA PARA 4
public abstract class CampusDatabase extends RoomDatabase {

    public abstract LocationDao locationDao();
    public abstract ProfessorDao professorDao();
    public abstract RoomDao roomDao();

    private static volatile CampusDatabase INSTANCE;

    public static CampusDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (CampusDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CampusDatabase.class, "campus_guide_database")
                            .fallbackToDestructiveMigration() // Para simplificar a migração
                            .allowMainThreadQueries() // Para simplificar, como pedido
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}