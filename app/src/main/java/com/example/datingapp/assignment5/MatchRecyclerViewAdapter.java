package com.example.datingapp.assignment5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datingapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchCardViewHolder> {
    private List<MatchesModel> matches;

    public MatchRecyclerViewAdapter(List<MatchesModel> matches) {
        this.matches = matches;
    }

    @NonNull
    @NotNull
    @Override
    public MatchCardViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_card, parent, false);
        return new MatchCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MatchCardViewHolder holder, int position) {
        holder.getMatchNameView().setText(matches.get(position).getName());
        holder.getMatchImageView().setImageDrawable(matches.get(position).getImage());
        holder.getLikeButton().setOnClickListener(v->{
            Toast.makeText(v.getContext(), "You liked "+matches.get(position).getName(),Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}
