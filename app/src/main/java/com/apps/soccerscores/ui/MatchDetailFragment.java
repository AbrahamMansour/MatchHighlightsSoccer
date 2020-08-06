package com.apps.soccerscores.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.apps.soccerscores.R;
import com.apps.soccerscores.data.Match;
import com.apps.soccerscores.databinding.FragmentMatchDetailsBinding;
import com.apps.soccerscores.utils.InjectorUtils;
import com.apps.soccerscores.view_models.MatchDetailViewModel;
import com.apps.soccerscores.view_models.MatchDetailViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchDetailFragment extends Fragment {

    private static final String TAG = "PlantDetailFragment";
    private static final String ARG_ITEM_ITEM = "item_id";

    String shareText;
    MatchDetailViewModel mViewModel;

    public static MatchDetailFragment newInstance(String plantId) {
        Bundle args = new Bundle();
        args.putString(ARG_ITEM_ITEM, plantId);
        MatchDetailFragment fragment = new MatchDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMatchDetailsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_match_details, container, false);

        String plantId = MatchDetailFragmentArgs.fromBundle(getArguments()).getPlantId();
        setupViewModel(plantId);

        binding.webView.getSettings().setJavaScriptEnabled(true);

        binding.webButton.setText("View Highlights");
        binding.webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.webView.loadData(mViewModel.match.getValue().getHighlights(),"text/html", "utf-8");
            }
        });

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.removeFromFavourites();
                Toast.makeText(getContext(),"Removed match from Favourites", Toast.LENGTH_LONG).show();
            }
        });

        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.addMatchToFavourites();
                Snackbar.make(view, R.string.added_match_to_favourites, Snackbar.LENGTH_LONG).show();

                //Todo: Hide button after adding plant to garden.
            }
        });

        setHasOptionsMenu(false);
        return binding.getRoot();
    }

    private void setupViewModel(String plantId) {
        MatchDetailViewModelFactory factory = InjectorUtils
                .provideMatchDetailViewModelFactory(getActivity(), plantId);
        mViewModel = ViewModelProviders.of(this, factory).get(MatchDetailViewModel.class);
        mViewModel.match.observe(this, new Observer<Match>() {
            @Override
            public void onChanged(@Nullable Match match) {
                Log.e(TAG, "onChanged: "+ match.getTitle() );
                shareText = match == null ? "" :
                        String.format(getString(R.string.share_text_plant), match.getTitle());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_match_detail, menu);
    }
}
