package com.example.datingapp.assignment6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MatchesViewModel extends ViewModel {
    private final MutableLiveData<List<Match>> matchesLiveData = new MutableLiveData<>();


    public MatchesViewModel() {

        matchesLiveData.setValue(FirestoreUtil.LOCAL_MATCHES_DATA);
    }

    public LiveData<List<Match>> getMatches() {
        if(matchesLiveData.getValue().isEmpty()){
            matchesLiveData.setValue(FirestoreUtil.LOCAL_MATCHES_DATA);
        }
        return matchesLiveData;
    }

    public void updateLike(int matchPosition) {
        Match match = matchesLiveData.getValue().get(matchPosition);
        // make firebase call to update db
        match.setLiked(!match.isLiked());
        FirestoreUtil.updateLike(match);
    }
}
