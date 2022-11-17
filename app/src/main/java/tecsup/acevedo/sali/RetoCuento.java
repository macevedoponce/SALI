package tecsup.acevedo.sali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

public class RetoCuento extends AppCompatActivity {

    ArrayList<Integer> mImagesIds = new ArrayList< >(Arrays.asList(
            R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4,
            R.drawable.f5, R.drawable.f6, R.drawable.f7, R.drawable.f8,
            R.drawable.f9, R.drawable.f10, R.drawable.f11, R.drawable.f12,
            R.drawable.f13, R.drawable.f14, R.drawable.f15, R.drawable.f16,
            R.drawable.f17, R.drawable.f18, R.drawable.f19, R.drawable.f20,
            R.drawable.f21, R.drawable.f22, R.drawable.f23, R.drawable.f24,
            R.drawable.f25, R.drawable.f26, R.drawable.f27
    ));

    ArrayList<String> mDescripcion = new ArrayList<>(Arrays.asList(
            "img1", "img2", "img3", "img4","img5", "img6", "img7", "img8","img9", "img10",
            "img11", "img12", "img13", "img14","img15", "img16", "img17", "img18","img19", "img20",
            "img21", "img22", "img23", "img24","img25", "img26", "img27"

    ));


    GridView grdMuestra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reto_cuento);
        grdMuestra = findViewById(R.id.Mygrid);
        grdMuestra.setAdapter(new AdaptadorImagen(mImagesIds, this));
        grdMuestra.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                int item_pos = mImagesIds.get(posicion);
                String item_desc_pos = mDescripcion.get(posicion);



                Intent intent = new Intent(RetoCuento.this,RetoCuentoItem.class);
                intent.putExtra("img_id",item_pos);
                startActivity(intent);

//                ShowDialogBox(item_pos);


            }
        });
    }
}