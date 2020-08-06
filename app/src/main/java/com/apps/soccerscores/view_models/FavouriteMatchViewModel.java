package com.apps.soccerscores.view_models;

import androidx.lifecycle.ViewModel;
import android.content.Context;
import androidx.databinding.ObservableField;

import com.apps.soccerscores.R;
import com.apps.soccerscores.data.FavouriteMatches;
import com.apps.soccerscores.data.Favourites;
import com.apps.soccerscores.data.Match;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class FavouriteMatchViewModel extends ViewModel {

    private final ObservableField<String> imageUrl;
    private final ObservableField<String> dateAdded;
    private Match match;
    private Favourites favourites;

    public FavouriteMatchViewModel(Context context, FavouriteMatches plantings) {
        this.match = plantings.getMatch();
        this.favourites = plantings.getFavourites().get(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);
        String plantDateStr = dateFormat.format(favourites.addedDate.getTime());

        imageUrl = new ObservableField<String>(match.getThumbnail());
        dateAdded = new ObservableField<String>(
                context.getString(R.string.date_added, match.getTitle(), plantDateStr));
    }


    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }

    public ObservableField<String> getDateAdded() {
        return dateAdded;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Favourites getFavourites() {
        return favourites;
    }

    public void setFavourites(Favourites favourites) {
        this.favourites = favourites;
    }
}
