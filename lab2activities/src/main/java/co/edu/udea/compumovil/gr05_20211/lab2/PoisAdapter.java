package co.edu.udea.compumovil.gr05_20211.lab2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PoisAdapter extends RecyclerView.Adapter<PoisAdapter.PoisHolder>{

    private List<PoiEntity> data;

    public PoisAdapter(List<PoiEntity> data) {
        this.data = data;
    }

    @Override
    public PoisHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PoisHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.poi_card, parent, false));
    }

    @Override
    public void onBindViewHolder(PoisHolder holder, int position) {
        PoiEntity poiEntity = data.get(position);
        holder.imagen.setImageResource(poiEntity.getPicture());
        holder.nombre.setText(poiEntity.getName());
        holder.des.setText(poiEntity.getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PoisHolder extends RecyclerView.ViewHolder{

        ImageView imagen;
        TextView nombre;
        TextView des;

        public PoisHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            des = (TextView) itemView.findViewById(R.id.des);
        }
    }
}
