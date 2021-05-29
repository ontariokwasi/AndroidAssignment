package com.example.datingapp.assignment5;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datingapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatchesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MatchesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MatchesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchesFragment newInstance() {
        MatchesFragment fragment = new MatchesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches_assignment5, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        MatchRecyclerViewAdapter adapter = new MatchRecyclerViewAdapter(generateMatches(view.getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<MatchesModel> generateMatches(Context context){
        return Arrays.asList(
                new MatchesModel("Bright Samson", AppCompatResources.getDrawable(context,R.mipmap.profile_image_foreground)),
                new MatchesModel("Edward Wright", AppCompatResources.getDrawable(context,R.mipmap.profile_image_foreground)),
                new MatchesModel("Albert Einstein", AppCompatResources.getDrawable(context,R.mipmap.profile_image_foreground)),
                new MatchesModel("Nelson Mandela", AppCompatResources.getDrawable(context,R.mipmap.profile_image_foreground)),
                new MatchesModel("Kwame Nkrumah", AppCompatResources.getDrawable(context,R.mipmap.profile_image_foreground)),
                new MatchesModel("Jimmy Carter", AppCompatResources.getDrawable(context,R.mipmap.profile_image_foreground))
        );
    }
}