package com.example.guide_campus_app.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.guide_campus_app.R;
import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.RoomDao;
import com.example.guide_campus_app.data.RoomEntity;

import java.util.ArrayList;
import java.util.List;

public class RoomSearchFragment extends Fragment {

    private RoomDao roomDao;
    private ListView listView;
    private EditText searchEditText;
    private Spinner campusSpinner;
    private RoomAdapter adapter;
    private List<RoomEntity> fullRoomList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_search, container, false);

        roomDao = CampusDatabase.getInstance(getContext()).roomDao();
        listView = view.findViewById(R.id.rooms_list_view);
        searchEditText = view.findViewById(R.id.search_text);
        campusSpinner = view.findViewById(R.id.campus_spinner);

        fullRoomList = roomDao.getAll();
        setupCampusSpinner();
        updateListView(fullRoomList);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            RoomEntity room = (RoomEntity) parent.getItemAtPosition(position);
            startActivity(RoomDetailActivity.newIntent(getContext(), room.id));
        });

        return view;
    }

    private void setupCampusSpinner() {
        List<String> campuses = roomDao.getUniqueCampuses();
        campuses.add(0, "Todos os Polos");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, campuses);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campusSpinner.setAdapter(spinnerAdapter);

        campusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void filterList() {
        String selectedCampus = campusSpinner.getSelectedItem().toString();
        String searchQuery = searchEditText.getText().toString().trim().toLowerCase();

        List<RoomEntity> filteredList = new ArrayList<>();

        List<RoomEntity> listToSearch = new ArrayList<>();
        if (selectedCampus.equals("Todos os Polos")) {
            listToSearch.addAll(fullRoomList);
        } else {
            for (RoomEntity room : fullRoomList) {
                if (room.campus.equals(selectedCampus)) {
                    listToSearch.add(room);
                }
            }
        }

        if (searchQuery.isEmpty()) {
            filteredList.addAll(listToSearch);
        } else {
            for (RoomEntity room : listToSearch) {
                if (room.name.toLowerCase().contains(searchQuery) || room.code.toLowerCase().contains(searchQuery)) {
                    filteredList.add(room);
                }
            }
        }

        updateListView(filteredList);
    }

    private void updateListView(List<RoomEntity> rooms) {
        adapter = new RoomAdapter(getContext(), rooms);
        listView.setAdapter(adapter);
    }
}