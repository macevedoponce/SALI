package tecsup.acevedo.sali;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorImagen extends BaseAdapter {

    public List<Integer> mThumbIds; // para guardar los ids que android le otorga a un archivo
    private Context context;

    public AdaptadorImagen(ArrayList<Integer> mImageIds, Context context) {//Object p0, MainActivity mainActivity
        this.mThumbIds = mImageIds;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mThumbIds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return mThumbIds.get(i);
    }

    @Override
    public View getView(int posicion, View imagenAdaptar, ViewGroup parent) {
        ImageView imageView = (ImageView) imagenAdaptar;
        if(imageView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(350,450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        imageView.setImageResource(mThumbIds.get(posicion));
        return imageView;
    }
}
