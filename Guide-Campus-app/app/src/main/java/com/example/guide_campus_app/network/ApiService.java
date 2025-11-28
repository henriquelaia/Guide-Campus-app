package com.example.guide_campus_app.network;

import com.example.guide_campus_app.data.LocationEntity;
import com.example.guide_campus_app.data.ProfessorEntity;
import com.example.guide_campus_app.data.RoomEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("locations")
    Call<List<LocationEntity>> getLocations();

    @GET("professors")
    Call<List<ProfessorEntity>> getProfessors();

    @GET("rooms")
    Call<List<RoomEntity>> getRooms();
}
