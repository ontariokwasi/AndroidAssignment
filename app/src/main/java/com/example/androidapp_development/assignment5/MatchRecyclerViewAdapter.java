package com.example.androidapp_development.assignment5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp_development.R;
import com.example.androidapp_development.assignment6.Match;
import com.example.androidapp_development.assignment6.MatchesViewModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchCardViewHolder> {
    private List<Match> matches;
    private MatchesViewModel matchesViewModel;

    public MatchRecyclerViewAdapter(MatchesViewModel matchesViewModel) {
        this.matchesViewModel = matchesViewModel;
        this.matches = matchesViewModel.getMatches().getValue();
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
        Match match = matches.get(position);
        holder.getMatchNameView().setText(match.getName());
        Picasso.get().load(match.getImageUrl()).into(holder.getMatchImageView());
        if(!match.isLiked()){
            holder.getLikeButton().setImageDrawable(AppCompatResources.getDrawable(holder.getLikeButton().getContext(), R.drawable.like));
        }
        holder.getLikeButton().setOnClickListener(v-> {
            if(match.isLiked()){
                holder.getLikeButton().setImageDrawable(AppCompatResources.getDrawable(holder.getLikeButton().getContext(), R.drawable.like));
                Toast.makeText(v.getContext(), "You unliked " + matches.get(position).getName(), Toast.LENGTH_LONG).show();
            }
            else {
                holder.getLikeButton().setImageDrawable(AppCompatResources.getDrawable(holder.getLikeButton().getContext(), android.R.drawable.star_big_on));
                Toast.makeText(v.getContext(), "You liked " + matches.get(position).getName(), Toast.LENGTH_LONG).show();
            }
            matchesViewModel.updateLike(position);
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
