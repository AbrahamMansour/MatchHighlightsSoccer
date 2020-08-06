package com.apps.soccerscores.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.apps.soccerscores.data.Favourites;
import com.apps.soccerscores.data.FavouritesRepository;
import com.apps.soccerscores.data.Match;
import com.apps.soccerscores.data.MatchRepository;

public class MatchDetailViewModel extends ViewModel {
    LiveData<Favourites> favourites;
    FavouritesRepository favouritesRepository;
    LiveData<Boolean> isPlanted;
    public LiveData<Match> match;
    String matchId;

    public MatchDetailViewModel(MatchRepository matchRepository,
                                FavouritesRepository favouritesRepository, String matchId){
        this.matchId = matchId;
        this.match = Transformations.map(matchRepository.getMatch(matchId), match -> {
           Match m = match;
           return m;
        });
        this.favouritesRepository = favouritesRepository;
        this.favourites = favouritesRepository.getGardenPlantingForPlant(matchId);
    }

    public void addMatchToFavourites() {
        favouritesRepository.addToFavourites(matchId);
    }

    public void removeFromFavourites() {
        favouritesRepository.removeFromFavourites(matchId);
    }

    public LiveData<Match> getMatch(){
        return match;
    }

//    @Override
//    protected void onCleared() {
//        super.onCleared();
//        viewModelScope.cancel();
//    }
}
