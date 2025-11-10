package com.example.guide_campus_app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guide_campus_app.R;
import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.RoomEntity;

public class RoomDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ROOM_ID = "room_id";

    // Factory method para ser usado pelos fragments
    public static Intent newIntent(Context context, int roomId) {
        Intent i = new Intent(context, RoomDetailActivity.class);
        i.putExtra(EXTRA_ROOM_ID, roomId);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        ImageView photo = findViewById(R.id.room_photo);
        TextView name = findViewById(R.id.room_name);
        TextView building = findViewById(R.id.room_building);
        TextView campus = findViewById(R.id.room_campus);
        TextView floor = findViewById(R.id.room_floor);
        TextView details = findViewById(R.id.room_details);

        int id = getIntent().getIntExtra(EXTRA_ROOM_ID, -1);
        if (id == -1) {
            finish();
            return;
        }

        RoomEntity room = CampusDatabase
                .getInstance(this)
                .roomDao()
                .getById(id);

        if (room == null) {
            finish();
            return;
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(room.name);
        }

        // Preenche campos principais
        if (name != null) {
            String text = room.name;
            if (room.code != null && !room.code.isEmpty()) {
                text += " (" + room.code + ")";
            }
            name.setText(text);
        }

        if (building != null) {
            building.setText("Edifício: " + room.building);
        }

        if (campus != null) {
            campus.setText("Polo: " + room.campus);
        }

        if (floor != null && room.floor != null && !room.floor.isEmpty()) {
            floor.setText("Piso: " + room.floor);
        }

        // Campo "detalhes" no layout: usa uma descrição composta com info extra
        if (details != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Edifício: ").append(room.building);
            sb.append(" | Polo: ").append(room.campus);
            if (room.floor != null && !room.floor.isEmpty()) {
                sb.append(" | Piso ").append(room.floor);
            }
            if (room.code != null && !room.code.isEmpty()) {
                sb.append("\nCódigo: ").append(room.code);
            }
            details.setText(sb.toString());
        }

        // FOTO: room_<id>.png em res/drawable
        int photoResId = getResources().getIdentifier(
                "room_" + room.id, "drawable", getPackageName());
        if (photoResId != 0 && photo != null) {
            photo.setImageResource(photoResId);
        }
    }
}
