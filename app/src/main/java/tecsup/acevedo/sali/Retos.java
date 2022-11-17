package tecsup.acevedo.sali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Retos extends AppCompatActivity {

    CardView cardFoto,cardDibujo,cardCuento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retos);

        cardFoto = findViewById(R.id.cardFoto);
        cardDibujo = findViewById(R.id.cardDibujo);
        cardCuento = findViewById(R.id.cardCuento);

        cardFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Retos.this,RetoFoto.class);
                startActivity(i);
            }
        });

        cardDibujo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Retos.this,RetoDibujo.class);
                startActivity(i);
            }
        });

        cardCuento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Retos.this,RetoCuento.class);
                startActivity(i);
            }
        });
    }
}