package tecsup.acevedo.sali;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoHolder>{
    List<Alumno> listaAlumno;

    public AlumnoAdapter(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumno = listaAlumnos;
    }

    @NonNull
    @NotNull
    @Override
    public AlumnoAdapter.AlumnoHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_estudiantes,parent,false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        vista.setLayoutParams(formaLayout);

//esta linea es para escuchar el evento de seleccion
//        vista.setOnClickListener((View.OnClickListener) this);

        return new AlumnoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AlumnoAdapter.AlumnoHolder holder, int position) {

        holder.txtdni.setText(listaAlumno.get(position).getDni());
        holder.txtNombre.setText(listaAlumno.get(position).getNombres());
        holder.txtapPaterno.setText(listaAlumno.get(position).getApellidoPaterno());
        holder.txtapMaterno.setText(listaAlumno.get(position).getApellidoMaterno());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle enviardatos = new Bundle();
                enviardatos.putString("keyDatodni",holder.txtdni.getText().toString());
                enviardatos.putString("keynombre",holder.txtNombre.getText().toString());
                enviardatos.putString("keyappaterno",holder.txtapPaterno.getText().toString());
                enviardatos.putString("keyapmaterno",holder.txtapMaterno.getText().toString());
                Intent intent = new Intent(view.getContext(), RegistrarNota.class);
                intent.putExtras(enviardatos);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaAlumno.size();
    }

    public class AlumnoHolder extends RecyclerView.ViewHolder {
        TextView txtdni,txtNombre, txtapMaterno,txtapPaterno;
//        ImageView imgPerro;

        public AlumnoHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            txtdni = itemView.findViewById(R.id.txtdni);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtapMaterno = itemView.findViewById(R.id.txtapeMaterno);
            txtapPaterno= itemView.findViewById(R.id.txtapPaterno);
        }

    }
}

