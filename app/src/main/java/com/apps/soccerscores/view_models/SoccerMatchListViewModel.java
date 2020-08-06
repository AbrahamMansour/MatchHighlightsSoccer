package com.apps.soccerscores.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.apps.soccerscores.data.FavouriteMatches;
import com.apps.soccerscores.data.Favourites;
import com.apps.soccerscores.data.FavouritesRepository;

import java.util.ArrayList;
import java.util.List;

public class SoccerMatchListViewModel extends ViewModel {

    public LiveData<List<FavouriteMatches>> favouriteMatches;
    public LiveData<List<Favourites>> gardenPlantings;

    public SoccerMatchListViewModel(FavouritesRepository favouritesRepository) {
        gardenPlantings = favouritesRepository.getFavourites();
        favouriteMatches =
                Transformations.map(favouritesRepository.getPlantAndGardenPlantings(), plantings -> {
                    List<FavouriteMatches> plantingsListNew = new ArrayList<>();
                    for (int i = 0; i < plantings.size(); i++) {
                        if (plantings.get(i).getFavourites() != null && !plantings.get(i).getFavourites().isEmpty()) {
                            plantingsListNew.add(plantings.get(i));
                        }
                    }
                    return plantingsListNew;
                });
    }
}
