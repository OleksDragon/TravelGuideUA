package com.example.travelguideua.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelguideua.R;
import com.example.travelguideua.data.model.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Place place);
    }

    private final List<Place> places = new ArrayList<>();
    private final OnItemClickListener listener;

    public PlaceAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setPlaces(List<Place> list) {
        places.clear();
        if (list != null) {
            places.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        holder.bind(places.get(position));
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView name, region;
        ImageView image;

        PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            region = itemView.findViewById(R.id.tvRegion);
            image = itemView.findViewById(R.id.ivPlace);
        }

        void bind(Place place) {
            name.setText(place.name);
            region.setText(place.region);
            Glide.with(itemView.getContext())
                    .load(place.imageUrl)
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(image);
            itemView.setOnClickListener(v -> listener.onItemClick(place));
        }
    }
}
