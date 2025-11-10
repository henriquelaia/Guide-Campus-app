package com.example.guide_campus_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

public class CampusMapFragment extends Fragment
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private LocationDao locationDao;
    private AutoCompleteTextView searchEditText;
    private ImageButton searchButton;

    private List<LocationEntity> allLocations = new ArrayList<>();

    // Ponto base (UBI / Covilhã) para centrar o mapa quando não há dados
    private static final LatLng UBI_CENTRAL_POINT = new LatLng(40.2804, -7.5086);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_campus_map, container, false);

        locationDao = CampusDatabase.getInstance(requireContext()).locationDao();

        searchEditText = view.findViewById(R.id.search_text);
        searchButton = view.findViewById(R.id.search_button);

        setupAutoComplete();

        searchButton.setOnClickListener(v -> searchLocation());

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    private void setupAutoComplete() {
        allLocations = locationDao.getAll();

        List<String> locationNames = new ArrayList<>();
        for (LocationEntity loc : allLocations) {
            if (loc != null && loc.name != null) {
                locationNames.add(loc.name);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                locationNames
        );

        searchEditText.setAdapter(adapter);
        searchEditText.setThreshold(1);

        // Quando o utilizador escolhe uma sugestão no dropdown
        searchEditText.setOnItemClickListener((parent, view, position, id) -> {
            String selectedName = (String) parent.getItemAtPosition(position);
            LocationEntity selected = null;
            for (LocationEntity loc : allLocations) {
                if (loc != null && selectedName.equals(loc.name)) {
                    selected = loc;
                    break;
                }
            }
            if (selected != null) {
                moveCameraToLocation(selected);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);

        List<LocationEntity> locations = locationDao.getAll();
        allLocations = locations;

        if (locations == null || locations.isEmpty()) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UBI_CENTRAL_POINT, 16f));
            return;
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LocationEntity location : locations) {
            LatLng latLng = new LatLng(location.latitude, location.longitude);
            Marker marker = mMap.addMarker(
                    new MarkerOptions()
                            .position(latLng)
                            .title(location.name)
                            .snippet(location.shortDescription)
            );
            if (marker != null) {
                marker.setTag(location.id);
            }
            builder.include(latLng);
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100));
    }

    private void moveCameraToLocation(@NonNull LocationEntity location) {
        if (mMap == null) return;
        LatLng latLng = new LatLng(location.latitude, location.longitude);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f));
    }

    private void searchLocation() {
        String query = searchEditText.getText().toString().trim();
        if (query.isEmpty()) return;

        List<LocationEntity> results = locationDao.searchByName("%" + query + "%");

        if (!results.isEmpty()) {
            moveCameraToLocation(results.get(0));
        } else {
            Toast.makeText(getContext(), "Sem resultados", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Integer locationId = (Integer) marker.getTag();
        if (locationId != null) {
            LocationDetailBottomSheet
                    .newInstance(locationId)
                    .show(getParentFragmentManager(), null);
        }
        // Devolvemos true para indicar que já tratámos o click (não mostrar InfoWindow default)
        return true;
    }
}
