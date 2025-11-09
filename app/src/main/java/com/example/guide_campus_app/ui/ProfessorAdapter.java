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
import com.example.guide_campus_app.data.ProfessorEntity;

import java.util.List;

public class ProfessorAdapter extends ArrayAdapter<ProfessorEntity> {

    public ProfessorAdapter(@NonNull Context context, @NonNull List<ProfessorEntity> professors) {
        super(context, 0, professors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_professor, parent, false);
        }

        ProfessorEntity currentProfessor = getItem(position);

        TextView nameView = listItemView.findViewById(R.id.professor_list_name);
        TextView departmentView = listItemView.findViewById(R.id.professor_list_department);

        if (currentProfessor != null) {
            nameView.setText(currentProfessor.name);
            departmentView.setText(currentProfessor.department);
        }

        return listItemView;
    }
}
