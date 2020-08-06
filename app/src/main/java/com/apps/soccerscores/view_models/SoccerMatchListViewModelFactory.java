package com.apps.soccerscores.view_models;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.apps.soccerscores.data.FavouritesRepository;

public class SoccerMatchListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    FavouritesRepository favouritesRepository;

    public SoccerMatchListViewModelFactory(FavouritesRepository repository) {
        this.favouritesRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SoccerMatchListViewModel(favouritesRepository);
    }
}
