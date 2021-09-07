package co.edu.udea.compumovil.gr05_20211.lab2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PoisAdapter extends RecyclerView.Adapter<PoisAdapter.PoisViewHolder>{
    private List<PoiEntity> items;

    public static class PoisViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView nombre;
        public TextView des;
        public TextView tempe;
        public TextView rating;

        public PoisViewHolder(View v){
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            des = (TextView) v.findViewById(R.id.des);
            tempe = (TextView) v.findViewById(R.id.temp);
            rating = (TextView) v.findViewById(R.id.rating);
        }
    }

    public PoisAdapter(List<PoiEntity> items){
        this.items = items;
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    @Override
    public PoisViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.por_card,viewGroup,false);
        return new PoisViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PoisViewHolder viewHolder,int i){
        //viewHolder.imagen.setImageResource(items.get(i).getPicture());
        viewHolder.nombre.setText(items.get(i).getName());
        viewHolder.des.setText(items.get(i).getDescription());
        viewHolder.tempe.setText(items.get(i).getTemperature());
        viewHolder.rating.setText(items.get(i).getRating().toString());
    }
}
