package tecsup.acevedo.sali;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class RetoFoto extends AppCompatActivity {

    Button btncamara;
    ImageView img;

    TextView txtrutaarchivo;
    String rutaimagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reto_foto);
        btncamara = findViewById(R.id.btnAbrirCamara);
        img = findViewById(R.id.imgfotoCapturada);
        txtrutaarchivo = findViewById(R.id.rutaArchivo);

        btncamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;

        try {
            imagenArchivo= crearimagen();
        }catch (IOException ex){
            Log.e("error",ex.toString());
        }
        if (imagenArchivo != null){
            Uri fotoUri = FileProvider.getUriForFile(this,"tecsup.acevedo.sali.fileprovider",imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
            startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if(requestCode ==1 && resultCode == RESULT_OK){
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaimagen);
            img.setImageBitmap(imgBitmap);
            txtrutaarchivo.setText(rutaimagen);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private File crearimagen() throws IOException {
        String nombreImagen = "foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen,".jpg",directorio);

        rutaimagen = imagen.getAbsolutePath();
        return imagen;
    }
}