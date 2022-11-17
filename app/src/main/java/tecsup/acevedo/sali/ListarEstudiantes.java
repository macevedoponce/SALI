package tecsup.acevedo.sali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListarEstudiantes extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    RecyclerView reciclerAlumnos;
    ArrayList<Alumno> listaAlumnos;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_estudiantes);
        listaAlumnos = new ArrayList<>();

        reciclerAlumnos = findViewById(R.id.recyEstudiantes);
        reciclerAlumnos.setLayoutManager(new LinearLayoutManager(this));
        reciclerAlumnos.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(this);

        cargarListaAlumnos();
    }

    private void cargarListaAlumnos() {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Buscando Alumnos");
        progreso.show();
        String url = Util.RUTA + "list_alumnos.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("error en Volley",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Alumno alumno = null;
        progreso.hide();
        JSONArray json = response.optJSONArray("alumnos");

        try {
            for(int i=0; i<json.length();i++){
                alumno = new Alumno();

                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                alumno.setId(jsonObject.getInt("id"));
                alumno.setDni(jsonObject.getString("dni"));
                alumno.setNombres(jsonObject.getString("nombres"));
                alumno.setApellidoPaterno(jsonObject.getString("apellidoPaterno"));
                alumno.setApellidoMaterno(jsonObject.getString("apellidoMaterno"));

                listaAlumnos.add(alumno);
            }
            AlumnoAdapter adapter = new AlumnoAdapter(listaAlumnos);
            reciclerAlumnos.setAdapter(adapter);

        }catch (Exception e){ e.printStackTrace();}
    }
}