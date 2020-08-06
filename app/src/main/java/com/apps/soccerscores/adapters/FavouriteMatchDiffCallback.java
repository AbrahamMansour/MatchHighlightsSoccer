package com.apps.soccerscores.adapters;

import androidx.recyclerview.widget.DiffUtil;

import com.apps.soccerscores.data.FavouriteMatches;

import java.util.List;

public class FavouriteMatchDiffCallback extends DiffUtil.Callback {

    private List<FavouriteMatches> oldList;
    private List<FavouriteMatches> newList;

    public FavouriteMatchDiffCallback(List<FavouriteMatches> oldList, List<FavouriteMatches> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }


    @Override
    public int getOldListSize() {
        return this.oldList.size();
    }

    @Override
    public int getNewListSize() {
        return this.newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        FavouriteMatches oldPlant = oldList.get(oldItemPosition);
        FavouriteMatches newPlant = newList.get(newItemPosition);
        return oldPlant.getMatch().getTitle() == newPlant.getMatch().getTitle();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        FavouriteMatches oldPlant = oldList.get(oldItemPosition);
        FavouriteMatches newPlant = newList.get(newItemPosition);
        return oldPlant.equals(newPlant);
    }

}
