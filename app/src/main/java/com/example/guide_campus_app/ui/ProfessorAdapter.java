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
import com.example.guide_campus_app.data.ProfessorEntity;

import java.util.List;

/**
 * Esta classe serve para adaptar os dados dos professores a uma lista visível no ecrã.
 */
public class ProfessorAdapter extends ArrayAdapter<ProfessorEntity> {

    /**
     * Este método é o construtor da classe.
     * Prepara o adaptador com a lista de professores.
     */
    public ProfessorAdapter(@NonNull Context context, @NonNull List<ProfessorEntity> professors) {
        super(context, 0, professors);
    }

    /**
     * Este método cria e preenche cada item da lista de professores.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_professor, parent, false);
        }

        ProfessorEntity currentProfessor = getItem(position);

        ImageView photoView = listItemView.findViewById(R.id.professor_list_photo);
        TextView nameView = listItemView.findViewById(R.id.professor_list_name);
        TextView departmentView = listItemView.findViewById(R.id.professor_list_department);

        if (currentProfessor != null) {
            nameView.setText(currentProfessor.name);
            departmentView.setText(currentProfessor.department);

            // Aqui carrego a fotografia do professor.
            if (photoView != null) {
                int photoResId = getPhotoResForProfessor(currentProfessor);
                if (photoResId != 0) {
                    photoView.setImageResource(photoResId);
                } else {
                    photoView.setImageResource(android.R.drawable.sym_def_app_icon);
                }
            }
        }

        return listItemView;
    }

    /**
     * Este método descobre o nome do ficheiro da fotografia do professor.
     */
    private int getPhotoResForProfessor(@NonNull ProfessorEntity professor) {
        Context context = getContext();
        if (context == null) return 0;

        String resourceName = "prof_" + professor.id;

        return context.getResources().getIdentifier(
                resourceName,
                "drawable",
                context.getPackageName()
        );
    }
}
