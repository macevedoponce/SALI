package tecsup.acevedo.sali;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import yuku.ambilwarna.AmbilWarnaDialog;

public class RetoDibujo extends AppCompatActivity {

    int defaultColor;
    SignatureView signatureView;
    ImageButton imgEraser, imgColor, imgSave, imgUpload;
    SeekBar seekBar;
    TextView txtPenSize;
    ImageView imagen;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    StringRequest stringRequest;

    private static String fileName;
    File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures");
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reto_dibujo);

        requestQueue = Volley.newRequestQueue(this);
        signatureView = findViewById(R.id.signature_view);
        imgEraser = findViewById(R.id.btnEraser);
        imgColor = findViewById(R.id.btnColor);
        imgSave = findViewById(R.id.btnSave);
        imgUpload = findViewById(R.id.btnUpload);
        seekBar = findViewById(R.id.penSize);
        txtPenSize = findViewById(R.id.txtPenSize);

        askPermission();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        date = format.format(new Date());
        fileName = path + "/" + date + ".png";
        defaultColor = ContextCompat.getColor(RetoDibujo.this,R.color.black);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtPenSize.setText(i + "dp");
                signatureView.setPenSize(i);
                seekBar.setMax(50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        imgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });

        imgEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signatureView.clearCanvas();
            }
        });

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!signatureView.isBitmapEmpty()){
                   saveImage();
                }
            }
        });

        imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent,"Seleccione APP"),10);

            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

            Uri path = data.getData();
            imagen.setImageURI(path);
            //signatureView.setBitmap();
        }
    }

    private void saveImage(){

        File file = new File(fileName);

        Bitmap bitmap = signatureView.getSignatureBitmap();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.PNG,0,bos);
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
        byte[] bitmapData = bos.toByteArray();



        //subir a BD
        String imagenString = Base64.encodeToString(bitmapData,Base64.DEFAULT);
        String url = Util.RUTA+"add_image_paint.php";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RetoDibujo.this, "Imagen Registrada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RetoDibujo.this,Retos.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RetoDibujo.this, "error BD", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String nombreImagen=date;
                String imagen = imagenString;

                Map<String, String> parametros = new HashMap<>();
                parametros.put("nombreImagen",nombreImagen);
                parametros.put("imagen",imagen);
                return parametros;
            }
        };
        requestQueue.add(stringRequest);

        //fin de subir a BD
    }

    private void openColorPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                signatureView.setPenColor(color);
            }
        });
        ambilWarnaDialog.show();
    }

    private void askPermission() {
        Dexter.withContext(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener(){
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                Toast.makeText(RetoDibujo.this, "Verificado", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();

            }
        }).check();
    }
}