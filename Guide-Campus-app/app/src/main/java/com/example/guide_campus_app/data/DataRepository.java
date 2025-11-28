package com.example.guide_campus_app.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.guide_campus_app.network.ApiService;
import com.example.guide_campus_app.network.RetrofitClient;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private static final String TAG = "DataRepository";
    private final CampusDatabase db;
    private final ApiService apiService;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public DataRepository(Context context) {
        this.db = CampusDatabase.getInstance(context);
        this.apiService = RetrofitClient.getApiService();
    }

    public void syncData(Context context) {
        syncLocations(context);
        syncProfessors(context);
        syncRooms(context);
    }

    private void syncLocations(Context context) {
        apiService.getLocations().enqueue(new Callback<List<LocationEntity>>() {
            @Override
            public void onResponse(Call<List<LocationEntity>> call, Response<List<LocationEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<LocationEntity> locations = response.body();
                    executor.execute(() -> {
                        db.locationDao().deleteAll(); // Limpa dados antigos (estratégia simples)
                        db.locationDao().insertAll(locations.toArray(new LocationEntity[0]));
                        Log.d(TAG, "Locations synced: " + locations.size());
                    });
                } else {
                    Log.e(TAG, "Failed to sync locations: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<LocationEntity>> call, Throwable t) {
                Log.e(TAG, "Error syncing locations", t);
                // Opcional: Mostrar toast apenas se não houver dados locais
            }
        });
    }

    private void syncProfessors(Context context) {
        apiService.getProfessors().enqueue(new Callback<List<ProfessorEntity>>() {
            @Override
            public void onResponse(Call<List<ProfessorEntity>> call, Response<List<ProfessorEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProfessorEntity> professors = response.body();
                    executor.execute(() -> {
                        db.professorDao().deleteAll();
                        db.professorDao().insertAll(professors.toArray(new ProfessorEntity[0]));
                        Log.d(TAG, "Professors synced: " + professors.size());
                    });
                } else {
                    Log.e(TAG, "Failed to sync professors: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ProfessorEntity>> call, Throwable t) {
                Log.e(TAG, "Error syncing professors", t);
            }
        });
    }

    private void syncRooms(Context context) {
        apiService.getRooms().enqueue(new Callback<List<RoomEntity>>() {
            @Override
            public void onResponse(Call<List<RoomEntity>> call, Response<List<RoomEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<RoomEntity> rooms = response.body();
                    executor.execute(() -> {
                        db.roomDao().deleteAll();
                        db.roomDao().insertAll(rooms.toArray(new RoomEntity[0]));
                        Log.d(TAG, "Rooms synced: " + rooms.size());
                    });
                } else {
                    Log.e(TAG, "Failed to sync rooms: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<RoomEntity>> call, Throwable t) {
                Log.e(TAG, "Error syncing rooms", t);
            }
        });
    }
}
