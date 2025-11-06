package com.example.guide_campus_app.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_room, parent, false);
        }

        RoomEntity currentRoom = getItem(position);

        TextView codeView = listItemView.findViewById(R.id.room_list_code);
        TextView buildingView = listItemView.findViewById(R.id.room_list_building);

        if (currentRoom != null) {
            codeView.setText(currentRoom.code);
            buildingView.setText(currentRoom.building + " - Piso " + currentRoom.floor);
        }

        return listItemView;
    }
}