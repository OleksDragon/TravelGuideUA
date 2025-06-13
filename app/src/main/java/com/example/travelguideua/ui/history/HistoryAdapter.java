package com.example.travelguideua.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelguideua.R;
import com.example.travelguideua.data.local.HistoryPlace;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<HistoryPlace> historyPlaces = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(HistoryPlace historyPlace);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setHistoryPlaces(List<HistoryPlace> list) {
        historyPlaces.clear();
        if (list != null) {
            historyPlaces.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.bind(historyPlaces.get(position));
    }

    @Override
    public int getItemCount() {
        return historyPlaces.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView timestampTextView;

        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvHistoryPlaceName);
            timestampTextView = itemView.findViewById(R.id.tvHistoryTimestamp);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(historyPlaces.get(position));
                }
            });
        }

        void bind(HistoryPlace historyPlace) {
            nameTextView.setText(historyPlace.getName());
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
            timestampTextView.setText(sdf.format(historyPlace.getTimestamp()));
        }
    }
}

