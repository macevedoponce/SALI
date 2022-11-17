package tecsup.acevedo.sali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Perfil extends AppCompatActivity {
    CardView cardVerAsistencia, cardVerNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        cardVerAsistencia = findViewById(R.id.cardVerAsistencia);
        cardVerNotas = findViewById(R.id.cardVerNotas);

        cardVerAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Perfil.this,verAsistenciaAlumno.class);
                startActivity(i);
            }
        });

        cardVerNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Perfil.this,verNotaAlumno.class);
                startActivity(i);
            }
        });
    }
}