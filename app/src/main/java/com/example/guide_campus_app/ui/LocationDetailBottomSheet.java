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

/**
 * Esta classe serve para mostrar a janela com os detalhes de uma localização.
 */
public class LocationDetailBottomSheet extends BottomSheetDialogFragment {

    private static final String ARG_LOCATION_ID = "location_id";

    /**
     * Este método cria uma nova instância da janela de detalhes.
     */
    public static LocationDetailBottomSheet newInstance(int locationId) {
        LocationDetailBottomSheet fragment = new LocationDetailBottomSheet();
        Bundle args = new Bundle();
        args.putInt(ARG_LOCATION_ID, locationId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Este método cria a vista do ecrã.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_location_detail, container, false);
    }

    /**
     * Este método é executado quando a vista é criada.
     * Carrega os dados da localização e preenche a informação no ecrã.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = view.findViewById(R.id.location_name);
        TextView type = view.findViewById(R.id.location_type);
        TextView shortDescription = view.findViewById(R.id.location_short_description);
        Button detailsButton = view.findViewById(R.id.view_details_button);
        ImageView imageView = view.findViewById(R.id.location_image);

        if (getArguments() != null) {
            int locationId = getArguments().getInt(ARG_LOCATION_ID);
            // Aqui ligo à base de dados para obter os detalhes da localização.
            LocationEntity location = CampusDatabase
                    .getInstance(requireContext())
                    .locationDao()
                    .getById(locationId);

            if (location != null) {
                name.setText(location.name);
                type.setText(location.type);
                shortDescription.setText(location.shortDescription);

                // Carrega a imagem da localização, se existir.
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

                // Se o botão for clicado, muda para o ecrã de detalhes completos.
                detailsButton.setOnClickListener(v -> {
                    startActivity(LocationDetailActivity.newIntent(getContext(), locationId));
                    dismiss();
                });
            }
        }
    }
}
