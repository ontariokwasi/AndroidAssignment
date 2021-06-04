package com.example.androidapp_development.assignment6;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class MatchesViewModel extends ViewModel {
    private final MutableLiveData<List<Match>> matchesLiveData = new MutableLiveData<>();


    public MatchesViewModel() {

        matchesLiveData.setValue(FirestoreUtil.REMOTE_MATCHES_DATA);
    }

    public LiveData<List<Match>> getMatches() {
        float radius = Float.parseFloat(SettingsRepository.getSettings().getSearchDistance());
        matchesLiveData.setValue(getProximityMatches(radius));
        return matchesLiveData;
    }

    public void updateLike(int matchPosition) {
        Match match = matchesLiveData.getValue().get(matchPosition);
        // make firebase call to update db
        match.setLiked(!match.isLiked());
        FirestoreUtil.updateLike(match);
    }

    public void triggerSettingsChanged(){
      float radius = Float.parseFloat(SettingsRepository.getSettings().getSearchDistance());
      matchesLiveData.setValue(getProximityMatches(radius));
    }

    private List<Match> getProximityMatches(float radius){
        return FirestoreUtil.REMOTE_MATCHES_DATA.stream()
                .filter(match -> isWithinRadius(match,radius))
                .collect(Collectors.toList());
    }

    private boolean isWithinRadius(Match match, float radius){
        Location matchLocation = new Location(LocationService.CURRENT_LOCATION);
        matchLocation.setLatitude(Double.parseDouble(match.getLat()));
        matchLocation.setLongitude(Double.parseDouble(match.getLongitude()));
        float distance =  LocationService.CURRENT_LOCATION.distanceTo(matchLocation)/1609; // miles
        return distance <= radius;
    }
}
