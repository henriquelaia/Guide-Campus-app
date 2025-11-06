package com.example.guide_campus_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.guide_campus_app.R;
import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.LocationDao;
import com.example.guide_campus_app.data.LocationEntity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class CampusMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private LocationDao locationDao;
    private TextInputEditText searchEditText;
    private MaterialButton searchButton;

    // Coordenadas do Polo I da UBI para centrar o mapa
    private final LatLng UBI_CENTRAL_POINT = new LatLng(40.2804, -7.5086);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campus_map, container, false);

        locationDao = CampusDatabase.getInstance(getContext()).locationDao();

        searchEditText = view.findViewById(R.id.search_text);
        searchButton = view.findViewById(R.id.search_button);

        searchEditText.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchLocation();
                return true;
            }
            return false;
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        searchButton.setOnClickListener(v -> searchLocation());

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);

        // Centrar imediatamente o mapa na UBI com um zoom razoável
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UBI_CENTRAL_POINT, 15f));

        loadLocationsOnMap();
    }

    private void loadLocationsOnMap() {
        if (mMap == null) {
            return;
        }

        List<LocationEntity> locations = locationDao.getAll();
        if (locations == null || locations.isEmpty()) return;

        mMap.clear();

        if (locations.size() == 1) {
            LocationEntity location = locations.get(0);
            LatLng singlePoint = new LatLng(location.latitude, location.longitude);
            Marker marker = mMap.addMarker(new MarkerOptions().position(singlePoint).title(location.name).snippet(location.shortDescription));
            if (marker != null) {
                marker.setTag(location.id);
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(singlePoint, 17f));
            return;
        }

        // Este código irá ajustar o zoom para mostrar todos os pontos depois de carregar
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LocationEntity location : locations) {
            LatLng latLng = new LatLng(location.latitude, location.longitude);
            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(location.name).snippet(location.shortDescription));
            if (marker != null) {
                marker.setTag(location.id);
            }
            builder.include(latLng);
        }

        // Anima a câmara para mostrar todos os marcadores com um padding
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100));
    }

    private void searchLocation() {
        if (mMap == null) {
            return;
        }

        String query = searchEditText.getText().toString().trim();
        if (query.isEmpty()) return;

        List<LocationEntity> results = locationDao.searchByName("%" + query + "%");

        if (!results.isEmpty()) {
            LocationEntity firstResult = results.get(0);
            LatLng latLng = new LatLng(firstResult.latitude, firstResult.longitude);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        } else {
            Toast.makeText(getContext(), "Sem resultados", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Integer locationId = (Integer) marker.getTag();
        if (locationId != null) {
            LocationDetailBottomSheet.newInstance(locationId).show(getParentFragmentManager(), null);
        }
        return true; // Consume o evento para não mostrar a info window default
    }
}