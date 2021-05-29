package com.example.datingapp.assignment5;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datingapp.R;

import org.jetbrains.annotations.NotNull;

public class MatchCardViewHolder extends RecyclerView.ViewHolder {
    private TextView matchNameView;
    private ImageView matchImageView;
    private ImageButton likeButton;
    public MatchCardViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        matchNameView = itemView.findViewById(R.id.match_name);
        matchImageView = itemView.findViewById(R.id.match_image);
        likeButton = itemView.findViewById(R.id.likeButton);
    }

    public TextView getMatchNameView() {
        return matchNameView;
    }

    public ImageView getMatchImageView() {
        return matchImageView;
    }

    public ImageButton getLikeButton() {
        return likeButton;
    }
}
