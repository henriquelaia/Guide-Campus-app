package com.example.guide_campus_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.ui.CampusMapFragment;
import com.example.guide_campus_app.ui.ProfessorListFragment;
import com.example.guide_campus_app.ui.RoomSearchFragment;
import com.example.guide_campus_app.ui.SettingsFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Sincronizar dados com o servidor
        com.example.guide_campus_app.data.DataRepository repository = new com.example.guide_campus_app.data.DataRepository(this);
        repository.syncData(this);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CampusMapFragment()).commit();
            bottomNav.setSelectedItemId(R.id.navigation_map);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SettingsFragment())
                .addToBackStack(null)
                .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
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