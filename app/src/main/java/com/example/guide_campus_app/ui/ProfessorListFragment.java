package com.example.guide_campus_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.guide_campus_app.R;
import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.ProfessorDao;
import com.example.guide_campus_app.data.ProfessorEntity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ProfessorListFragment extends Fragment {

    private ProfessorDao professorDao;
    private ListView listView;
    private TextInputEditText searchEditText;
    private MaterialButton searchButton;
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
        searchButton = view.findViewById(R.id.search_button);
        departmentSpinner = view.findViewById(R.id.department_spinner);

        // Carregar todos os professores para a lista principal
        fullProfessorList = professorDao.getAll();
        setupDepartmentSpinner();
        updateListView(fullProfessorList);

        searchButton.setOnClickListener(v -> filterList());
        searchEditText.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filterList();
                return true;
            }
            return false;
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            ProfessorEntity professor = (ProfessorEntity) parent.getItemAtPosition(position);
            startActivity(ProfessorDetailActivity.newIntent(getContext(), professor.id));
        });

        return view;
    }

    private void setupDepartmentSpinner() {
        List<String> departments = professorDao.getUniqueDepartments();
        departments.add(0, "Todos os Departamentos"); // Adicionar opção para mostrar todos

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, departments);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(spinnerAdapter);

        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterList(); // Filtrar a lista sempre que um departamento for selecionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void filterList() {
        String selectedDepartment = departmentSpinner.getSelectedItem().toString();
        String searchQuery = searchEditText.getText().toString().trim().toLowerCase();

        List<ProfessorEntity> filteredList = new ArrayList<>();

        // 1. Filtrar por departamento
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

        // 2. Filtrar por texto de pesquisa
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
            Toast.makeText(getContext(), "Sem resultados", Toast.LENGTH_SHORT).show();
        }

        updateListView(filteredList);
    }

    private void updateListView(List<ProfessorEntity> professors) {
        adapter = new ProfessorAdapter(getContext(), professors);
        listView.setAdapter(adapter);
    }
}