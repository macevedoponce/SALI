package tecsup.acevedo.sali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegistrarAsistencia extends AppCompatActivity {

    ArrayList<Alumno> listaalumnos;
    RecyclerView recyclerAlumnos;
    TextView txtFecha, txtNumAlumnos, txtNumTemp, txtNumTar, txtNumFal;
    Button btnCalendario;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    //fecha actual
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    Date date = new Date();
    String fecha_actual = dateFormat.format(date);
    //fin fecha actual

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_asistencia);
        listaalumnos = new ArrayList<>();
        btnCalendario = findViewById(R.id.btnCalendario);
        txtFecha = findViewById(R.id.txtFecha);
        txtNumAlumnos = findViewById(R.id.txtNumAlumnos);
        txtNumTemp = findViewById(R.id.txtNumTemp);
        txtNumTar = findViewById(R.id.txtNumTar);
        txtNumFal = findViewById(R.id.txtNumFal);
        recyclerAlumnos = findViewById(R.id.RecyclerAlumnos);
        recyclerAlumnos.setLayoutManager(new LinearLayoutManager(this));
        recyclerAlumnos.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(this);
        cargarListaAlumnos();
        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCalendario();
            }
        });

        txtFecha.setText(fecha_actual);

        Integer id_recep = getIntent().getIntExtra("state",0);

        if(id_recep != 0){
            registrarAsistencia();
        }


    }

    public void abrirCalendario() {
        DatePickerDialog dpd = new DatePickerDialog(RegistrarAsistencia.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String mes_let = "";
                month = month +1;
                String fechaCalendar = year + "/" + month + "/" + dayOfMonth;
                fecha_actual = fechaCalendar;
                txtFecha.setText(fechaCalendar);
                //select * from alumnos inner join asistencia where asistencia.fecha=
            }
        },year,month,day);
        dpd.show();
    }

    private void cargarListaAlumnos() {
        String url = Util.RUTA + "list_alumnos.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Alumno alumno = null;

                //JSONArray json = response.optJSONArray("datos");
                JSONArray json = response.optJSONArray("alumnos");

                try {
                    Integer numTotal = json.length();
                    txtNumAlumnos.setText(numTotal.toString());
                    for (int i = 0; i < json.length(); i++) {
                        alumno = new Alumno();
                        JSONObject jsonObject = null;
                        jsonObject = json.getJSONObject(i);
                        //alumno.setId(jsonObject.getInt("id"));
                        alumno.setId(jsonObject.getInt("id"));
                        //alumno.setId_alumno(jsonObject.getInt("alumno_id"));
                        alumno.setNombres(jsonObject.getString("nombres"));
                        alumno.setApellidoMaterno(jsonObject.getString("apellidoMaterno"));
                        alumno.setApellidoPaterno(jsonObject.getString("apellidoPaterno"));
//                        alumno.setEstado(jsonObject.getInt("estado"));
//                        alumno.setFecha(jsonObject.getString("fecha"));
                        listaalumnos.add(alumno);

                    }


                    AdapterAsistencia adapter = new AdapterAsistencia(listaalumnos);
                    recyclerAlumnos.setAdapter(adapter);


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Alumnos no encontradas", Toast.LENGTH_SHORT).show();
                Log.i("error", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private void registrarAsistencia() {
        Integer id = getIntent().getIntExtra("alumno_id",0);
        Integer estado = getIntent().getIntExtra("state",0);
        String fecha_reg = fecha_actual;
        String url = Util.RUTA +
                "add_asistencia.php?alumno_id="+id+
                "&fecha=" + fecha_reg +
                "&estado=" + estado;

        url = url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity.this, "Error de inserción", Toast.LENGTH_SHORT).show();
                Log.i("error",error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}