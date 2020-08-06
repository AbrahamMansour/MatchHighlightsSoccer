package com.apps.soccerscores.view_models;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.apps.soccerscores.data.MatchRepository;

public class MatchListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private MatchRepository matchRepository;

    public MatchListViewModelFactory(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MatchListViewModel matchListViewModel = new MatchListViewModel(matchRepository);
        return (T) matchListViewModel;
    }
}
