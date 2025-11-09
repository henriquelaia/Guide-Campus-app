package com.example.guide_campus_app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.guide_campus_app.R;
import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.ProfessorEntity;

public class ProfessorDetailActivity extends AppCompatActivity {

    private static final String EXTRA_PROFESSOR_ID = "extra_professor_id";

    public static Intent newIntent(Context context, int professorId) {
        Intent intent = new Intent(context, ProfessorDetailActivity.class);
        intent.putExtra(EXTRA_PROFESSOR_ID, professorId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_detail);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int professorId = getIntent().getIntExtra(EXTRA_PROFESSOR_ID, -1);
        if (professorId == -1) {
            finish();
            return;
        }

        ProfessorEntity professor = CampusDatabase.getInstance(this).professorDao().getById(professorId);

        TextView department = findViewById(R.id.professor_department);
        TextView courses = findViewById(R.id.professor_courses);
        TextView subjects = findViewById(R.id.professor_subjects);
        TextView email = findViewById(R.id.professor_email);
        TextView phone = findViewById(R.id.professor_phone);
        TextView office = findViewById(R.id.professor_office);
        TextView notes = findViewById(R.id.professor_notes);

        if (professor != null) {
            getSupportActionBar().setTitle(professor.name);
            department.setText(professor.department);
            courses.setText("Cursos: " + professor.courses);
            subjects.setText(professor.subjects);
            email.setText("Email: " + professor.email);
            phone.setText("Telefone: " + professor.phone);
            office.setText(professor.office);
            notes.setText(professor.notes);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}