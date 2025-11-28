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
import com.example.guide_campus_app.data.ProfessorDao;
import com.example.guide_campus_app.data.ProfessorEntity;

import java.util.ArrayList;
import java.util.List;

public class ProfessorListFragment extends Fragment {

    private ProfessorDao professorDao;
    private ListView listView;
    private EditText searchEditText;
    private Spinner departmentSpinner;
    private ProfessorAdapter adapter;
    private List<ProfessorEntity> fullProfessorList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_professor_list, container, false);

        professorDao = CampusDatabase.getInstance(getContext()).professorDao();
        listView = view.findViewById(R.id.professors_list_view);
        searchEditText = view.findViewById(R.id.search_text);
        departmentSpinner = view.findViewById(R.id.department_spinner);

        fullProfessorList = professorDao.getAll();
        setupDepartmentSpinner();
        updateListView(fullProfessorList);

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
            ProfessorEntity professor = (ProfessorEntity) parent.getItemAtPosition(position);
            startActivity(ProfessorDetailActivity.newIntent(getContext(), professor.id));
        });

        return view;
    }

    private void setupDepartmentSpinner() {
        List<String> departments = professorDao.getUniqueDepartments();
        departments.add(0, "Todos os Departamentos");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, departments);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(spinnerAdapter);

        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void filterList() {
        String selectedDepartment = departmentSpinner.getSelectedItem().toString();
        String searchQuery = searchEditText.getText().toString().trim().toLowerCase();

        List<ProfessorEntity> filteredList = new ArrayList<>();

        List<ProfessorEntity> listToSearch = new ArrayList<>();
        if (selectedDepartment.equals("Todos os Departamentos")) {
            listToSearch.addAll(fullProfessorList);
        } else {
            for (ProfessorEntity prof : fullProfessorList) {
                if (prof.department.equals(selectedDepartment)) {
                    listToSearch.add(prof);
                }
            }
        }

        if (searchQuery.isEmpty()) {
            filteredList.addAll(listToSearch);
        } else {
            for (ProfessorEntity prof : listToSearch) {
                if (prof.name.toLowerCase().contains(searchQuery)) {
                    filteredList.add(prof);
                }
            }
        }
        
        if (filteredList.isEmpty()) {
            // Opcional: mostrar mensagem de "sem resultados", mas pode ser incomodativo com autocomplete
            // Toast.makeText(getContext(), "Sem resultados", Toast.LENGTH_SHORT).show();
        }

        updateListView(filteredList);
    }

    private void updateListView(List<ProfessorEntity> professors) {
        adapter = new ProfessorAdapter(getContext(), professors);
        listView.setAdapter(adapter);
    }
}