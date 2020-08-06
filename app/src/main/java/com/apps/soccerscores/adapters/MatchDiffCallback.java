package com.apps.soccerscores.adapters;

import androidx.recyclerview.widget.DiffUtil;

import com.apps.soccerscores.data.Match;

import java.util.List;

public class MatchDiffCallback extends DiffUtil.Callback {

    private List<Match> oldList;
    private List<Match> newList;

    public MatchDiffCallback(List<Match> oldList, List<Match> newList) {
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
        Match oldMatch = oldList.get(oldItemPosition);
        Match newMatch = newList.get(newItemPosition);
        return oldMatch.getTitle() == newMatch.getTitle();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Match oldMatch = oldList.get(oldItemPosition);
        Match newMatch = newList.get(newItemPosition);
        return oldMatch.equals(newMatch);
    }

}
