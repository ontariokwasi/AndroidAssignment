package com.example.androidapp_development.assignment5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp_development.R;
import com.example.androidapp_development.assignment6.Match;
import com.example.androidapp_development.assignment6.MatchesViewModel;

import java.util.List;

public class MatchesFragment extends Fragment {

    public MatchesFragment() {
        // Required empty public constructor
    }

    public static MatchesFragment newInstance() {
        MatchesFragment fragment = new MatchesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycle_matches_asst5, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        MatchesViewModel matchesViewModel = new ViewModelProvider(requireActivity()).get(MatchesViewModel.class);
        MatchRecyclerViewAdapter adapter = new MatchRecyclerViewAdapter(matchesViewModel);
        recyclerView.setAdapter(adapter);
        matchesViewModel.getMatches().observe(getViewLifecycleOwner(), matches -> {
            adapter.setMatches(matches);
            recyclerView.setAdapter(null);
            recyclerView.setLayoutManager(null);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            adapter.notifyDataSetChanged();
        });
        return view;
    }
}