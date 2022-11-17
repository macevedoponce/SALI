package tecsup.acevedo.sali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView cardRetos,cardVidaEstudiantil,cardPerfil,cardAyuda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardRetos = findViewById(R.id.cardRetos);
        cardVidaEstudiantil = findViewById(R.id.cardVidaEstudiantil);
        cardPerfil = findViewById(R.id.cardPerfil);
        cardAyuda = findViewById(R.id.cardAyuda);

        cardRetos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Retos.class);
                startActivity(i);
            }
        });

        cardVidaEstudiantil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,VidaEstudiantil.class);
                startActivity(i);
            }
        });

        cardPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Perfil.class);
                startActivity(i);
            }
        });

        cardAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Ayuda.class);
                startActivity(i);
            }
        });
    }
}