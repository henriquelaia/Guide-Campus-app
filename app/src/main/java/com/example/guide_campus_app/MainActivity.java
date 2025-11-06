package com.example.guide_campus_app;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.SampleData;
import com.example.guide_campus_app.ui.CampusMapFragment;
import com.example.guide_campus_app.ui.ProfessorListFragment;
import com.example.guide_campus_app.ui.RoomSearchFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inicializar a base de dados e popular com dados de exemplo
        CampusDatabase db = CampusDatabase.getInstance(this);
        SampleData.populateIfEmpty(db);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(this);

        // Carregar o fragmento inicial
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CampusMapFragment()).commit();
            bottomNav.setSelectedItemId(R.id.navigation_map);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        int itemId = item.getItemId();
        if (itemId == R.id.navigation_map) {
            selectedFragment = new CampusMapFragment();
        } else if (itemId == R.id.navigation_professors) {
            selectedFragment = new ProfessorListFragment();
        } else if (itemId == R.id.navigation_rooms) {
            selectedFragment = new RoomSearchFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
        return false;
    }
}