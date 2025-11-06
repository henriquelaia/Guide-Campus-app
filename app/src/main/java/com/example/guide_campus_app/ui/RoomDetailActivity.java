package com.example.guide_campus_app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.guide_campus_app.R;
import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.RoomEntity;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class RoomDetailActivity extends AppCompatActivity {

    private static final String EXTRA_ROOM_ID = "extra_room_id";

    public static Intent newIntent(Context context, int roomId) {
        Intent intent = new Intent(context, RoomDetailActivity.class);
        intent.putExtra(EXTRA_ROOM_ID, roomId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int roomId = getIntent().getIntExtra(EXTRA_ROOM_ID, -1);
        if (roomId == -1) {
            finish();
            return;
        }

        RoomEntity room = CampusDatabase.getInstance(this).roomDao().getById(roomId); // Assumes getById exists

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        ImageView roomImage = findViewById(R.id.room_image);
        TextView building = findViewById(R.id.room_building);
        TextView floor = findViewById(R.id.room_floor);
        TextView description = findViewById(R.id.room_description);

        if (room != null) {
            toolbarLayout.setTitle(room.code);
            building.setText(room.building);
            floor.setText("Piso: " + room.floor);
            description.setText(room.description);

            int imageResId = getResources().getIdentifier(room.imageName, "drawable", getPackageName());
            if (imageResId != 0) {
                roomImage.setImageResource(imageResId);
            } else {
                roomImage.setImageResource(R.drawable.ic_room); // Imagem padrão
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}