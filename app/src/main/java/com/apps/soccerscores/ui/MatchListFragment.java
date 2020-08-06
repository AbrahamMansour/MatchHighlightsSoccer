package com.apps.soccerscores.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.apps.soccerscores.R;
import com.apps.soccerscores.adapters.MatchAdapter;
import com.apps.soccerscores.data.Match;
import com.apps.soccerscores.utils.InjectorUtils;
import com.apps.soccerscores.view_models.MatchListViewModel;
import com.apps.soccerscores.view_models.MatchListViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MatchListFragment extends Fragment {

    private List<Match> plantsList = new ArrayList<>();
    private MatchListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_list, container, false);

        MatchListViewModelFactory factory = InjectorUtils.provideMatchListViewModelFactory(getActivity());
        viewModel = ViewModelProviders.of(this, factory).get(MatchListViewModel.class);

        MatchAdapter adapter = new MatchAdapter(plantsList);
        RecyclerView recyclerView = view.findViewById(R.id.plant_list);
        recyclerView.setAdapter(adapter);
        subscribeUI(adapter);

        setHasOptionsMenu(false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_match_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void subscribeUI(MatchAdapter adapter) {
        viewModel.getPlants().observe(getViewLifecycleOwner(), new Observer<List<Match>>() {
            @Override
            public void onChanged(@Nullable List<Match> matches) {
                adapter.updateItems(matches);
            }
        });
    }

}
