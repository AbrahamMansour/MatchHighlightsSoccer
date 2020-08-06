package com.apps.soccerscores.utils;

import android.content.Context;

import com.apps.soccerscores.data.AppDatabase;
import com.apps.soccerscores.data.FavouritesRepository;
import com.apps.soccerscores.data.MatchRepository;
import com.apps.soccerscores.view_models.SoccerMatchListViewModelFactory;
import com.apps.soccerscores.view_models.MatchDetailViewModelFactory;
import com.apps.soccerscores.view_models.MatchListViewModelFactory;

public class InjectorUtils {


    private static MatchRepository getMatchRepository(Context context) {
        return MatchRepository.getInstance(AppDatabase.getInstance(context).matchDao());
    }

    private static FavouritesRepository getFavouritesRepository(Context context) {
        return FavouritesRepository.getInstance(AppDatabase.getInstance(context).favouritesDao());
    }

    public static MatchListViewModelFactory provideMatchListViewModelFactory(Context context) {
        MatchRepository repository = getMatchRepository(context);
        MatchListViewModelFactory vmFactory = new MatchListViewModelFactory(repository);
        return vmFactory;
    }

    public static SoccerMatchListViewModelFactory provideSoccerMatchListViewModelFactory(Context context) {
        FavouritesRepository favouritesRepository = getFavouritesRepository(context);
        return new SoccerMatchListViewModelFactory(favouritesRepository);
    }

    public static MatchDetailViewModelFactory provideMatchDetailViewModelFactory(Context context, String plantId) {
        return new MatchDetailViewModelFactory(getMatchRepository(context), getFavouritesRepository(context), plantId);
    }
}
