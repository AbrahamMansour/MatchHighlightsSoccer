package com.apps.soccerscores.workers;

import androidx.annotation.NonNull;

import com.apps.soccerscores.data.Match;
import com.apps.soccerscores.data.model.SoccerMatch;
import com.apps.soccerscores.data.model.MatchVideo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.apps.soccerscores.data.AppDatabase;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.work.Worker;

public class SeedDatabaseWorker extends Worker {
    @NonNull
    @Override
    public Result doWork() {

        try {
            InputStream inputStream = getApplicationContext().getAssets().open("scores.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            //List<Match> matchList = new Gson().fromJson(json, new TypeToken<List<Match>>() {
            //}.getType());

            List<SoccerMatch> soccerMatches = new Gson().fromJson(json, new TypeToken<List<SoccerMatch>>() {}.getType());
            List<Match> matchList = convertMatchesToPojo(soccerMatches);

            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
            appDatabase.matchDao().insertAll(matchList);
            return Result.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.FAILURE;
        }
    }

    List<Match> convertMatchesToPojo(List<SoccerMatch> soccerMatches) {
        List<Match> matches = new ArrayList<>();

        for(SoccerMatch soccerMatch : soccerMatches) {
            Match p = new Match();
            p.setTitle(soccerMatch.getTitle());
            p.setCompetition(soccerMatch.getCompetition().getName());
            p.setThumbnail(soccerMatch.getThumbnail());
            p.setSide1(soccerMatch.getSide1().getName());
            p.setSide2(soccerMatch.getSide2().getName());
            p.setDate(soccerMatch.getDate());
            p.setHighlights(getHighlights(soccerMatch));

            matches.add(p);
        }

        return matches;
    }

    private String getHighlights(SoccerMatch soccerMatch) {
        String highlights = "";

        List<MatchVideo> videos = soccerMatch.getVideos();

        if(videos != null) {
            for(MatchVideo v : videos) {
                if("Highlights".equals(v.getTitle())) {
                    return v.getEmbed();
                }
            }
        }

        return highlights;
    }
}
