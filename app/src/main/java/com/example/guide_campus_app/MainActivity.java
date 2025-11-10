package com.example.guide_campus_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.SampleData;
import com.example.guide_campus_app.ui.CampusMapFragment;
import com.example.guide_campus_app.ui.ProfessorListFragment;
import com.example.guide_campus_app.ui.RoomSearchFragment;
import com.example.guide_campus_app.ui.SettingsFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Esta classe é a atividade principal da aplicação.
 * Gere a navegação e a apresentação dos diferentes ecrãs.
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    /**
     * Este método é executado quando a atividade arranca.
     * Configura a vista inicial, a base de dados e a navegação.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura a barra de ferramentas no topo do ecrã.
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Carrega os dados iniciais para a base de dados, se esta estiver vazia.
        CampusDatabase db = CampusDatabase.getInstance(this);
        SampleData.populateIfEmpty(db);

        // Configura os botões da barra de navegação inferior.
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(this);

        // Mostra o mapa como ecrã inicial da aplicação.
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CampusMapFragment()).commit();
            bottomNav.setSelectedItemId(R.id.navigation_map);
        }
    }

    /**
     * Cria o menu de opções no canto superior direito (ex: Definições).
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Este método é executado quando se clica num item do menu de opções.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Se o item for 'Definições', muda para o ecrã de definições.
        if (item.getItemId() == R.id.action_settings) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SettingsFragment())
                .addToBackStack(null) // Permite voltar ao ecrã anterior.
                .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Este método é executado quando se clica num botão da navegação inferior.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        // Muda o ecrã conforme o botão selecionado (Mapa, Professores ou Salas).
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_map) {
            selectedFragment = new CampusMapFragment();
        } else if (itemId == R.id.navigation_professors) {
            selectedFragment = new ProfessorListFragment();
        } else if (itemId == R.id.navigation_rooms) {
            selectedFragment = new RoomSearchFragment();
        }

        // Mostra o novo ecrã.
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
        return false;
    }
}
