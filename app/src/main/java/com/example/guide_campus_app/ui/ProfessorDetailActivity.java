package com.example.guide_campus_app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guide_campus_app.R;
import com.example.guide_campus_app.data.CampusDatabase;
import com.example.guide_campus_app.data.ProfessorEntity;

public class ProfessorDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PROFESSOR_ID = "professor_id";

    // >>> NOVO: método factory para alinhar com o fragment
    public static Intent newIntent(Context context, int professorId) {
        Intent i = new Intent(context, ProfessorDetailActivity.class);
        i.putExtra(EXTRA_PROFESSOR_ID, professorId);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_detail);

        ImageView photo = findViewById(R.id.professor_photo);
        TextView department = findViewById(R.id.professor_department);
        TextView office = findViewById(R.id.professor_office);
        TextView email = findViewById(R.id.professor_email);
        TextView phone = findViewById(R.id.professor_phone);
        TextView courses = findViewById(R.id.professor_courses);
        TextView notes = findViewById(R.id.professor_notes);

        int id = getIntent().getIntExtra(EXTRA_PROFESSOR_ID, -1);
        if (id == -1) {
            finish();
            return;
        }

        ProfessorEntity professor = CampusDatabase
                .getInstance(this)
                .professorDao()
                .getById(id);

        if (professor == null) {
            finish();
            return;
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(professor.name);
        }

        if (department != null) department.setText(professor.department);
        if (office != null) office.setText(professor.office);
        if (email != null) email.setText(professor.email);
        if (phone != null) phone.setText(professor.phone);
        if (courses != null) courses.setText(professor.courses);
        if (notes != null) notes.setText(professor.notes);

        // Foto: prof_<id>.png em res/drawable
        int photoResId = getResources().getIdentifier(
                "prof_" + professor.id, "drawable", getPackageName());
        if (photoResId != 0 && photo != null) {
            photo.setImageResource(photoResId);
        }
    }
}
