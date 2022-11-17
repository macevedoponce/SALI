package tecsup.acevedo.sali;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterAsistencia extends RecyclerView.Adapter<AdapterAsistencia.AsistenciaHolder> {
    List<Alumno> listaAlumnos;
    private View.OnClickListener listener;
    boolean[] mcheckedStateTemp;
    boolean[] mcheckedStateTar;
    boolean[] mcheckedStateFal;


    Integer state = 0;
    public AdapterAsistencia(ArrayList<Alumno> listaalumnos) {
        this.listaAlumnos = listaalumnos;
        mcheckedStateTemp = new boolean[listaalumnos.size()];
        mcheckedStateTar = new boolean[listaalumnos.size()];
        mcheckedStateFal = new boolean[listaalumnos.size()];
    }

    @NonNull
    @NotNull
    @Override
    public AdapterAsistencia.AsistenciaHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) { //enlaza con el item para mostrar cada resultado
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(formaLayout);
        return new AsistenciaHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterAsistencia.AsistenciaHolder holder, int position) { //comunicación entre adaptador y view alumnoHolder

        Integer id = listaAlumnos.get(position).getId();
       // Integer id_alumno = listaAlumnos.get(position).getId_alumno();
//        String nombre = listaAlumnos.get(position).getNombres();
//        String apM = listaAlumnos.get(position).getApellidoMaterno();
//        String apP = listaAlumnos.get(position).getApellidoPaterno();

        holder.txtNombres.setText(listaAlumnos.get(position).getNombres());
        holder.txtApellidoPaterno.setText(listaAlumnos.get(position).getApellidoPaterno());
        holder.txtApellidoMaterno.setText(listaAlumnos.get(position).getApellidoMaterno());

        //verifica que boton esta presionado
        if(mcheckedStateTemp[position]){
            holder.btnTemp.setChecked(true);
        }else{
            holder.btnTemp.setChecked(false);
        }
        if(mcheckedStateTar[position]){
            holder.btnTar.setChecked(true);
        }else{
            holder.btnTar.setChecked(false);
        }
        if(mcheckedStateFal[position]){
            holder.btnFal.setChecked(true);
        }else{
            holder.btnFal.setChecked(false);
        }
        //fin verificacion de boton preisonado

//        // si el alumno tiene algun estado registrado se pinta el color
//        if(listaAlumnos.get(position).getEstado() == null )
//            holder.btnTemp.setChecked(false);
//        holder.btnTar.setChecked(false);
//        holder.btnFal.setChecked(false);
//
//        if(listaAlumnos.get(position).getEstado() == 1 )
//            holder.btnTemp.setChecked(true);
//
//        if(listaAlumnos.get(position).getEstado() == 2 )
//            holder.btnTar.setChecked(true);
//
//        if(listaAlumnos.get(position).getEstado() == 3 )
//            holder.btnFal.setChecked(true);
//        //fin de meustra alumnos registrados

        holder.btnTemp.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                mcheckedStateTemp[position] = false;
                state=1;
                holder.btnTar.setEnabled(false);
                holder.btnFal.setEnabled(false);
                holder.btnTar.setChecked(false);
                holder.btnFal.setChecked(false);

//                Intent i = new Intent(holder.itemView.getContext(), MainActivity.class);
//                i.putExtra("alumno_id",id);
//                i.putExtra("state",state);
//                holder.itemView.getContext().startActivity(i);

            }else{
                mcheckedStateTemp[position] = true;
                holder.btnTar.setEnabled(true);
                holder.btnFal.setEnabled(true);
            }
        });
//
        holder.btnTar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                mcheckedStateTar[position] = false;
                holder.btnTemp.setEnabled(false);
                holder.btnFal.setEnabled(false);
                holder.btnTemp.setChecked(false);
                holder.btnFal.setChecked(false);
                state=2;
//                Intent i = new Intent(holder.itemView.getContext(), MainActivity.class);
//                i.putExtra("alumno_id",listaAlumnos.get(position).getId());
//                i.putExtra("state",state);
//                holder.itemView.getContext().startActivity(i);
            }else{
                mcheckedStateTar[position] = true;
                holder.btnTemp.setEnabled(true);
                holder.btnFal.setEnabled(true);
            }
        });

        holder.btnFal.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                mcheckedStateFal[position] = false;
                holder.btnTar.setEnabled(false);
                holder.btnTemp.setEnabled(false);
                holder.btnTar.setChecked(false);
                holder.btnTemp.setChecked(false);
                state=3;
//                Intent i = new Intent(holder.itemView.getContext(), MainActivity.class);
//                i.putExtra("alumno_id",listaAlumnos.get(position).getId());
//                i.putExtra("state",state);
//                holder.itemView.getContext().startActivity(i);
            }else{
                mcheckedStateFal[position] = true;
                holder.btnTar.setEnabled(true);
                holder.btnTemp.setEnabled(true);
            }
        });
    }




    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class AsistenciaHolder extends RecyclerView.ViewHolder {
        TextView txtApellidoPaterno, txtApellidoMaterno,txtNombres ;
        CheckBox btnTemp, btnTar, btnFal;
        CardView card_view;

        public AsistenciaHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtApellidoPaterno = itemView.findViewById(R.id.txtApellidoPaterno);
            txtApellidoMaterno = itemView.findViewById(R.id.txtApellidoMaterno);
            txtNombres = itemView.findViewById(R.id.txtNombres);
            btnTar = itemView.findViewById(R.id.btnTarde);
            btnTemp = itemView.findViewById(R.id.btnTemprano);
            btnFal = itemView.findViewById(R.id.btnFalta);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
