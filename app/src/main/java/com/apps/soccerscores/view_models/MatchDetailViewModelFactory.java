package com.apps.soccerscores.view_models;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.apps.soccerscores.data.FavouritesRepository;
import com.apps.soccerscores.data.MatchRepository;

public class MatchDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private MatchRepository matchRepository;
    private FavouritesRepository favouritesRepository;
    private String plantId;

    public MatchDetailViewModelFactory(MatchRepository matchRepository,
                                       FavouritesRepository favouritesRepository, String plantId) {
        this.matchRepository = matchRepository;
        this.favouritesRepository = favouritesRepository;
        this.plantId = plantId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MatchDetailViewModel(matchRepository, favouritesRepository, plantId);
    }
}
