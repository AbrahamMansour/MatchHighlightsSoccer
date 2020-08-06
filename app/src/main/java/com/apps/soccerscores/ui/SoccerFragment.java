package com.apps.soccerscores.ui;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.soccerscores.R;
import com.apps.soccerscores.adapters.FavouriteMatch;
import com.apps.soccerscores.data.Favourites;
import com.apps.soccerscores.data.FavouriteMatches;
import com.apps.soccerscores.utils.InjectorUtils;
import com.apps.soccerscores.view_models.SoccerMatchListViewModel;
import com.apps.soccerscores.view_models.SoccerMatchListViewModelFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoccerFragment extends Fragment {

    List<FavouriteMatches> favouriteMatches = new ArrayList<>();
    RecyclerView mRecyclerView;
    TextView mTextViewEmptyGarden;

    public SoccerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soccer, container, false);
        FavouriteMatch adapter = new FavouriteMatch(favouriteMatches);

        mTextViewEmptyGarden = view.findViewById(R.id.no_favs);
        mRecyclerView = view.findViewById(R.id.favourites_list);
        mRecyclerView.setAdapter(adapter);
        subscribeUI(adapter);
        return view;
    }

    private void subscribeUI(FavouriteMatch adapter) {
        SoccerMatchListViewModelFactory factory = InjectorUtils.provideSoccerMatchListViewModelFactory(getContext());
        SoccerMatchListViewModel viewModel = ViewModelProviders.of(this, factory).get(SoccerMatchListViewModel.class);
        viewModel.gardenPlantings.observe(getViewLifecycleOwner(), new Observer<List<Favourites>>() {
            @Override
            public void onChanged(@Nullable List<Favourites> favourites) {
                if (favourites != null && !favourites.isEmpty()) {
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mTextViewEmptyGarden.setVisibility(View.GONE);
                }else {
                    mRecyclerView.setVisibility(View.GONE);
                    mTextViewEmptyGarden.setVisibility(View.VISIBLE);
                }
            }
        });

        viewModel.favouriteMatches.observe(getViewLifecycleOwner(), new Observer<List<FavouriteMatches>>() {
            @Override
            public void onChanged(@Nullable List<FavouriteMatches> gardenPlantingsList) {
                if (gardenPlantingsList != null && !gardenPlantingsList.isEmpty()) {
                    adapter.updateList(gardenPlantingsList);
                }
            }
        });
    }



}
