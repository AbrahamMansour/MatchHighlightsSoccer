package com.apps.soccerscores.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MatchRepository {
    private MatchDao matchDao;
    private static volatile MatchRepository instance;

    MatchRepository(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    public LiveData<List<Match>> getMatches() {
        return matchDao.getMatches();
    }

    public LiveData<Match> getMatch(String id) {
        return matchDao.getMatches(id);
    }

    public static MatchRepository getInstance(MatchDao matchDao) {
        if (instance == null) {
            synchronized(MatchRepository.class) {
                if (instance == null)
                    instance = new MatchRepository(matchDao);
            }
        }
        return instance;
    }
}
