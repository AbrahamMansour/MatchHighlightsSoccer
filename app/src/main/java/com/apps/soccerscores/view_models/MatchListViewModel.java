package com.apps.soccerscores.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apps.soccerscores.data.Match;
import com.apps.soccerscores.data.MatchRepository;

import java.util.List;

public class MatchListViewModel extends ViewModel {
    private MatchRepository matchRepository;
    private int NO_GROW_ZONE = -1;
    private MutableLiveData<Integer> growZoneNumber = new MutableLiveData<>();
    private MediatorLiveData<List<Match>> plantList = new MediatorLiveData<>();

    MatchListViewModel(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
        growZoneNumber.setValue(NO_GROW_ZONE);

        LiveData<List<Match>> livePlantList = matchRepository.getMatches();

        plantList.addSource(livePlantList, plants -> plantList.setValue(plants));
    }


    public MediatorLiveData<List<Match>> getPlants() {
        return plantList;
    }

    public void setGrowZoneNumber(int no) {
        growZoneNumber.setValue(no);
    }

    public void clearGrowZoneNumber() {
        growZoneNumber.setValue(NO_GROW_ZONE);
    }

    public boolean isFiltered() {
        return growZoneNumber.getValue() != NO_GROW_ZONE;
    }

}
