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
import com.example.guide_campus_app.data.LocationEntity;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class LocationDetailActivity extends AppCompatActivity {

    private static final String EXTRA_LOCATION_ID = "extra_location_id";

    public static Intent newIntent(Context context, int locationId) {
        Intent intent = new Intent(context, LocationDetailActivity.class);
        intent.putExtra(EXTRA_LOCATION_ID, locationId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int locationId = getIntent().getIntExtra(EXTRA_LOCATION_ID, -1);
        if (locationId == -1) {
            finish();
            return;
        }

        LocationEntity location = CampusDatabase.getInstance(this).locationDao().getById(locationId);

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        ImageView locationImage = findViewById(R.id.location_image);
        TextView locationHours = findViewById(R.id.location_hours);
        TextView locationHoursNotes = findViewById(R.id.location_hours_notes);
        TextView locationDetails = findViewById(R.id.location_details);

        if (location != null) {
            toolbarLayout.setTitle(location.name);
            locationHours.setText(location.operatingHours);
            locationHoursNotes.setText(location.hoursNotes);
            locationDetails.setText(location.details);

            int imageResId = getResources().getIdentifier(location.imageName, "drawable", getPackageName());
            if (imageResId != 0) {
                locationImage.setImageResource(imageResId);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}