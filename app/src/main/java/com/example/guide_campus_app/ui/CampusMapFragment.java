package com.example.guide_campus_app.ui;

import android.os.Bundle;
import android.util.Log;
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
import com.example.guide_campus_app.data.LocationEntity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampusMapFragment extends Fragment
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final String TAG = "CampusMapFragment";
    private GoogleMap mMap;
    private ApiService apiService;
    private AutoCompleteTextView searchEditText;
    private ImageButton searchButton;
    private List<LocationEntity> allLocations = new ArrayList<>();
    private static final LatLng UBI_CENTRAL_POINT = new LatLng(40.2804, -7.5086);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_campus_map, container, false);

        apiService = ApiClient.getClient().create(ApiService.class);

        searchEditText = view.findViewById(R.id.search_text);
        searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(v -> searchLocation());

        fetchLocationsAndSetupUi();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    private void fetchLocationsAndSetupUi() {
        apiService.getLocations().enqueue(new Callback<List<LocationEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<LocationEntity>> call, @NonNull Response<List<LocationEntity>> response) {
                if (response.isSuccessful() && response.body() != null && getActivity() != null) {
                    allLocations = response.body();
                    setupAutoComplete();
                    if (mMap != null) {
                        addMarkersToMap(allLocations);
                    }
                } else {
                    Log.e(TAG, "Failed to get locations from API. Code: " + response.code());
                    Toast.makeText(getContext(), "Failed to load locations", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<LocationEntity>> call, @NonNull Throwable t) {
                Log.e(TAG, "API call failed: " + t.getMessage());
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Network error. Check connection.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupAutoComplete() {
        if (getContext() == null) return;
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

        searchEditText.setOnItemClickListener((parent, view, position, id) -> {
            String selectedName = (String) parent.getItemAtPosition(position);
            for (LocationEntity loc : allLocations) {
                if (loc != null && selectedName.equals(loc.name)) {
                    moveCameraToLocation(loc);
                    showLocationDetails(loc.id);
                    break;
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);

        if (allLocations != null && !allLocations.isEmpty()) {
            addMarkersToMap(allLocations);
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UBI_CENTRAL_POINT, 16f));
        }
    }

    private void addMarkersToMap(@NonNull List<LocationEntity> locations) {
        if (mMap == null || locations.isEmpty()) return;
        mMap.clear();

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LocationEntity location : locations) {
            LatLng latLng = new LatLng(location.latitude, location.longitude);
            float markerColor = getMarkerColor(location);

            Marker marker = mMap.addMarker(
                    new MarkerOptions()
                            .position(latLng)
                            .title(location.name)
                            .snippet(location.shortDescription)
                            .icon(BitmapDescriptorFactory.defaultMarker(markerColor))
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
        String query = searchEditText.getText().toString().trim().toLowerCase();
        if (query.isEmpty()) return;

        List<LocationEntity> results = new ArrayList<>();
        for (LocationEntity loc : allLocations) {
            if (loc.name != null && loc.name.toLowerCase().contains(query)) {
                results.add(loc);
            }
        }

        if (!results.isEmpty()) {
            LocationEntity first = results.get(0);
            moveCameraToLocation(first);
            showLocationDetails(first.id);
        } else {
            Toast.makeText(getContext(), "Sem resultados", Toast.LENGTH_SHORT).show();
        }
    }

    private void showLocationDetails(int locationId) {
        if (getParentFragmentManager() != null) {
            LocationDetailBottomSheet
                    .newInstance(locationId)
                    .show(getParentFragmentManager(), "location_detail");
        }
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Integer locationId = (Integer) marker.getTag();
        if (locationId != null) {
            showLocationDetails(locationId);
        }
        return true;
    }

    private float getMarkerColor(LocationEntity location) {
        if (location.type != null) {
            switch (location.type) {
                case "Residência SASUBI":
                    return BitmapDescriptorFactory.HUE_GREEN;
                case "Serviços":
                case "Serviços centrais":
                    return BitmapDescriptorFactory.HUE_ORANGE;
                case "Biblioteca":
                    return BitmapDescriptorFactory.HUE_YELLOW;
            }
        }

        if (location.campus == null) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        switch (location.campus) {
            case "Polo I":
                return BitmapDescriptorFactory.HUE_AZURE;
            case "Polo II":
                return BitmapDescriptorFactory.HUE_BLUE;
            case "Polo III":
                return BitmapDescriptorFactory.HUE_CYAN;
            case "Polo IV":
                return BitmapDescriptorFactory.HUE_VIOLET;
            default:
                return BitmapDescriptorFactory.HUE_RED;
        }
    }
}
