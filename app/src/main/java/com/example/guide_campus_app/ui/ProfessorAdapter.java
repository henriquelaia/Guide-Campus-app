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
import androidx.core.content.ContextCompat;

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

        ImageView imageView = listItemView.findViewById(R.id.professor_list_image);
        TextView nameView = listItemView.findViewById(R.id.professor_list_name);
        TextView departmentView = listItemView.findViewById(R.id.professor_list_department);
        TextView officeView = listItemView.findViewById(R.id.professor_list_office);
        TextView emailView = listItemView.findViewById(R.id.professor_list_email);

        if (currentProfessor != null) {
            nameView.setText(currentProfessor.name);
            departmentView.setText(currentProfessor.department);
            officeView.setText(currentProfessor.office);
            emailView.setText(currentProfessor.email);

            int imageResId = getContext()
                    .getResources()
                    .getIdentifier(currentProfessor.imageName, "drawable", getContext().getPackageName());
            if (imageResId != 0) {
                imageView.setImageResource(imageResId);
                imageView.setColorFilter(null);
            } else {
                // Imagem padrão caso não encontre
                imageView.setImageResource(R.drawable.ic_people);
                imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.primaryColor));
            }
        }

        return listItemView;
    }
}