package tecsup.acevedo.sali;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class RetoCuentoItem extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    ImageView imagen;
    EditText txtTextoDetectado;
    ImageButton imgBtnMic;
    Button btn_guardar;

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    ConversorSpeech conversor;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reto_cuento_item);
        requestQueue = Volley.newRequestQueue(this);

        imagen = findViewById(R.id.img_dialodo_retodos);
        txtTextoDetectado = findViewById(R.id.txtTextoDetectado);
        imgBtnMic = findViewById(R.id.btnConvertirTexto);
        btn_guardar = findViewById(R.id.btn_save_historia);

        conversor = new ConversorSpeech();
        conversor.init(this);

        int img_id = getIntent().getExtras().getInt("img_id");
        imagen.setImageResource(img_id);

        imgBtnMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarDeteccion();
            }
        });
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guadarArchivo();

                Intent intent = new Intent(RetoCuentoItem.this, RetoCuento.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onDestroy() {
        conversor.apagar();
        super.onDestroy();
    }

    private void guadarArchivo() {
        String estado = Environment.getExternalStorageState();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

        String texto = txtTextoDetectado.getText().toString();

        progreso = new ProgressDialog(this);
        progreso.setMessage("Insertando");
        progreso.show();

        String url = Util.RUTA + "insertar_texto.php?texto=" + texto;
        url = url.replace(" ", "%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }


    private void iniciarDeteccion() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hable ahora");
        try {
            //startActivityForResult(i, REQ_CODE_SPEECH_INPUT);
            startActivityForResult(i,REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Error al reconocer", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT:
                if(resultCode==RESULT_OK && null!=data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtTextoDetectado.setText(result.get(0));
                }
                break;
        }
    }
    //    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case REQ_CODE_SPEECH_INPUT: {
//                if (requestCode == RESULT_OK && data != null) {
//                    ArrayList<String> resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                    txtTextoDetectado.setText(resultado.get(0));
//                }
//            }
//        }
//    }


    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(this, "Muy bien!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(RetoCuentoItem.this,RetoCuento.class);
        startActivity(i);
        finish();
    }

    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "Error de insercion", Toast.LENGTH_SHORT).show();

        Log.i("error", error.toString());
    }
}