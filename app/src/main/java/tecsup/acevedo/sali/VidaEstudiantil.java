package tecsup.acevedo.sali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VidaEstudiantil extends AppCompatActivity {
    CardView cardAsistencia, cardNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vida_estudiantil);
        cardAsistencia = findViewById(R.id.cardAsistencia);
        cardNotas = findViewById(R.id.cardNota);

        cardAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VidaEstudiantil.this,RegistrarAsistencia.class);
                startActivity(i);
            }
        });

        cardNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VidaEstudiantil.this,ListarEstudiantes.class);
                startActivity(i);
            }
        });
    }
}