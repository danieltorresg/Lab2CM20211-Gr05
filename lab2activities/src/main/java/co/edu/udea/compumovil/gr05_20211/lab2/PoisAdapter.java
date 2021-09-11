package co.edu.udea.compumovil.gr05_20211.lab2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PoisAdapter extends RecyclerView.Adapter<PoisAdapter.PoisHolder>{

    private List<PoiEntity> data;
    private Context ctx;

    public PoisAdapter(List<PoiEntity> data, Context ctx) {

        this.data = data;
        this.ctx=ctx;
    }

    @Override
    public PoisHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PoisHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.poi_card, parent, false));
    }

    @Override
    public void onBindViewHolder(PoisHolder holder, int position) {
        PoiEntity poiEntity = data.get(position);
        PoisHolder view = (PoisHolder) holder;
        if(!poiEntity.getPicture().isEmpty()){
            Glide.with(ctx).load(poiEntity.getPicture()).into(view.imagen);
        };
        view.nombre.setText(poiEntity.getName());
        view.des.setText(poiEntity.getDescription());
        Intent intent = new Intent(view.context, PoiDetails.class);
        intent.putExtra("description",poiEntity.description);
        intent.putExtra("temperature",poiEntity.temperature);
        intent.putExtra("rating",poiEntity.rating);
        intent.putExtra("name",poiEntity.name);
        view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.context.startActivity(intent);
            }
        });
        view.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.context.startActivity(intent);
            }
        });
        view.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.context.startActivity(intent);
            }
        });
        view.des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PoisHolder extends RecyclerView.ViewHolder{
        private Context context;
        ImageView imagen;
        TextView nombre;
        TextView des;

        public PoisHolder(View itemView) {
            super(itemView);
            context= itemView.getContext();
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            des = (TextView) itemView.findViewById(R.id.des);
        }
    }
}
