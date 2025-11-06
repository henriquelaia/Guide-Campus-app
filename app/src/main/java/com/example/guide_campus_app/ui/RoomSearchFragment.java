package com.example.guide_campus_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.guide_campus_app.R;
import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.RoomDao;
import com.example.guide_campus_app.data.RoomEntity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class RoomSearchFragment extends Fragment {

    private RoomDao roomDao;
    private ListView listView;
    private TextInputEditText searchEditText;
    private MaterialButton searchButton;
    private RoomAdapter adapter; // Usar o novo RoomAdapter
    private List<RoomEntity> roomList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_search, container, false);

        roomDao = CampusDatabase.getInstance(getContext()).roomDao();
        listView = view.findViewById(R.id.rooms_list_view);
        searchEditText = view.findViewById(R.id.search_text);
        searchButton = view.findViewById(R.id.search_button);

        loadAllRooms();

        searchButton.setOnClickListener(v -> searchRooms());
        searchEditText.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchRooms();
                return true;
            }
            return false;
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            RoomEntity room = roomList.get(position);
            startActivity(RoomDetailActivity.newIntent(getContext(), room.id));
        });

        return view;
    }

    private void loadAllRooms() {
        roomList = roomDao.getAll();
        updateListView(roomList);
    }

    private void searchRooms() {
        String query = searchEditText.getText().toString().trim();
        if (query.isEmpty()) {
            loadAllRooms();
            return;
        }

        roomList = roomDao.search("%" + query + "%");
        if (roomList.isEmpty()) {
            Toast.makeText(getContext(), "Sem resultados", Toast.LENGTH_SHORT).show();
        }
        updateListView(roomList);
    }

    private void updateListView(List<RoomEntity> rooms) {
        // Usar o novo RoomAdapter
        adapter = new RoomAdapter(getContext(), rooms);
        listView.setAdapter(adapter);
    }
}