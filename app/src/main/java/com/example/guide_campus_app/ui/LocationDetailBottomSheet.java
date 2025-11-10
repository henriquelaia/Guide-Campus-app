package com.example.guide_campus_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.guide_campus_app.R;
import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.LocationEntity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class LocationDetailBottomSheet extends BottomSheetDialogFragment {

    private static final String ARG_LOCATION_ID = "location_id";

    public static LocationDetailBottomSheet newInstance(int locationId) {
        LocationDetailBottomSheet fragment = new LocationDetailBottomSheet();
        Bundle args = new Bundle();
        args.putInt(ARG_LOCATION_ID, locationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_location_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = view.findViewById(R.id.location_name);
        TextView type = view.findViewById(R.id.location_type);
        TextView shortDescription = view.findViewById(R.id.location_short_description);
        Button detailsButton = view.findViewById(R.id.view_details_button);
        ImageView imageView = view.findViewById(R.id.location_image); // opcional (se existir no XML)

        if (getArguments() != null) {
            int locationId = getArguments().getInt(ARG_LOCATION_ID);
            LocationEntity location = CampusDatabase
                    .getInstance(requireContext())
                    .locationDao()
                    .getById(locationId);

            if (location != null) {
                name.setText(location.name);
                type.setText(location.type);
                shortDescription.setText(location.shortDescription);

                // Imagem (mesma lógica da Activity de detalhes)
                if (imageView != null && location.imageName != null && !location.imageName.isEmpty()) {
                    int imageResId = getResources().getIdentifier(
                            location.imageName,
                            "drawable",
                            requireContext().getPackageName()
                    );
                    if (imageResId != 0) {
                        imageView.setImageResource(imageResId);
                    }
                }

                detailsButton.setOnClickListener(v -> {
                    startActivity(LocationDetailActivity.newIntent(getContext(), locationId));
                    dismiss();
                });
            }
        }
    }
}
