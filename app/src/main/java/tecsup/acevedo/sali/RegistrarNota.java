package tecsup.acevedo.sali;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegistrarNota extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    TextView txtDni, TxtNombre , txtpMaterno, txtApPaterno;
    EditText edte1bim1, edte2bim1,edte3bim1,edtpcbim1;
    Button btnRegistarNota;


    ProgressDialog progreso;
    RequestQueue requestQueue; //cola de requerimiento tiene que saber esperar, gestiona el hecho que espere las respuestas del servidor
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_nota);
        txtDni = findViewById(R.id.txtDniNota);
        TxtNombre = findViewById(R.id.txtNombrenota);
        txtpMaterno = findViewById(R.id.txtapeMaternonota);
        txtApPaterno = findViewById(R.id.txtapPaternonota);
        edte1bim1 = findViewById(R.id.edtnotae1bim1);
        edte2bim1 = findViewById(R.id.edtnotae2bim1);
        edte3bim1 = findViewById(R.id.edtnotae3bim1);
        edtpcbim1 = findViewById(R.id.edtnotapcbim1);
        btnRegistarNota = findViewById(R.id.btnGuardarNotas);

        //llamamiento a libreria volley
        requestQueue = Volley.newRequestQueue(this);

        String idalu = getIntent().getExtras().getString("keyDatodni");
        String nombres = getIntent().getExtras().getString("keynombre");
        String apMaterno = getIntent().getExtras().getString("keyapmaterno");
        String apPaterno = getIntent().getExtras().getString("keyappaterno");

        txtDni.setText(idalu);
        TxtNombre.setText(nombres);
        txtpMaterno.setText(apMaterno);
        txtApPaterno.setText(apPaterno);

        btnRegistarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarNotas();
            }
        });

    }

    private void registrarNotas() {

        progreso = new ProgressDialog(this);
        progreso.setMessage("insertando");
        progreso.show();

        String nota1bim1 = edte1bim1.getText().toString();
        String nota2bim1 = edte2bim1.getText().toString();
        String nota3bim1 = edte3bim1.getText().toString();
        String participacionbim1 = edtpcbim1.getText().toString();
//        String idalumno = txtDni.getText().toString();
        String url = Util.RUTA + "registrarnotas.php?primeranota=" + nota1bim1 + "&segundanota="+nota2bim1+"&terceranota=" + nota3bim1 +
                "&participacion="+participacionbim1+"&idalumno=156";

        url = url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "Error de Inserción", Toast.LENGTH_SHORT).show();
        Log.i("error",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(this, "Notas Registradas Exitosamente", Toast.LENGTH_SHORT).show();
//        edte1bim1.setText("");
//        edte2bim1.setText("");
//        edte3bim1.setText("");
//        edtpcbim1.setText("");
        Intent i = new Intent(RegistrarNota.this, ListarEstudiantes.class);
        startActivity(i);
    }
}