package com.example.guide_campus_app.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.guide_campus_app.R;
import com.example.guide_campus_app.data.RoomEntity;

import java.util.List;

public class RoomAdapter extends ArrayAdapter<RoomEntity> {

    public RoomAdapter(@NonNull Context context, @NonNull List<RoomEntity> rooms) {
        super(context, 0, rooms);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_room, parent, false);
        }

        RoomEntity currentRoom = getItem(position);

        ImageView photoView = listItemView.findViewById(R.id.room_list_photo);
        TextView nameView = listItemView.findViewById(R.id.room_list_name);
        TextView detailsView = listItemView.findViewById(R.id.room_list_details);

        if (currentRoom != null) {
            // Nome + código
            nameView.setText(currentRoom.name + " (" + currentRoom.code + ")");

            // Detalhes: Polo, edifício, piso
            String details = "Polo " + currentRoom.campus + " | " + currentRoom.building;
            if (currentRoom.floor != null && !currentRoom.floor.isEmpty()) {
                details += " | Piso " + currentRoom.floor;
            }
            detailsView.setText(details);

            // Foto (se existir imagem para esta sala)
            if (photoView != null) {
                int photoResId = getPhotoResForRoom(currentRoom);
                if (photoResId != 0) {
                    photoView.setImageResource(photoResId);
                } else {
                    // Placeholder genérico do Android
                    photoView.setImageResource(android.R.drawable.sym_def_app_icon);
                }
            }
        }

        return listItemView;
    }

    private int getPhotoResForRoom(@NonNull RoomEntity room) {
        Context context = getContext();
        if (context == null) return 0;

        // Convenção: drawable/room_<id>.png (ex.: room_1.png, room_2.png)
        String resourceName = "room_" + room.id;

        return context.getResources().getIdentifier(
                resourceName,
                "drawable",
                context.getPackageName()
        );
    }
}
