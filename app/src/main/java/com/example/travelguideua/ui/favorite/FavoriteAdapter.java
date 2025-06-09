package com.example.travelguideua.ui.favorite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelguideua.R;
import com.example.travelguideua.data.local.FavoritePlace;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private final List<FavoritePlace> favoritePlaces = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(FavoritePlace favoritePlace);
        void onDeleteClick(FavoritePlace favoritePlace);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setFavoritePlaces(List<FavoritePlace> list) {
        favoritePlaces.clear();
        if (list != null) {
            favoritePlaces.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.bind(favoritePlaces.get(position));
    }

    @Override
    public int getItemCount() {
        return favoritePlaces.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView cityTextView;
        ImageView imageView;
        ImageView deleteButton;

        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvFavoriteName);
            cityTextView = itemView.findViewById(R.id.tvFavoriteCity);
            imageView = itemView.findViewById(R.id.ivFavoritePlace);
            deleteButton = itemView.findViewById(R.id.ivDeleteFavorite);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(favoritePlaces.get(position));
                }
            });

            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(favoritePlaces.get(position));
                }
            });
        }

        void bind(FavoritePlace favoritePlace) {
            nameTextView.setText(favoritePlace.getName());
            cityTextView.setText(favoritePlace.getCity());
            Glide.with(itemView.getContext())
                    .load(favoritePlace.getImageUrl())
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(imageView);
        }
    }
}
