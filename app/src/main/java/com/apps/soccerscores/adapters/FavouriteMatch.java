package com.apps.soccerscores.adapters;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.soccerscores.R;
import com.apps.soccerscores.data.FavouriteMatches;
import com.apps.soccerscores.databinding.ListItemSoccerMatchBinding;
import com.apps.soccerscores.ui.MatchListFragmentDirections;
import com.apps.soccerscores.ui.SoccerFragmentDirections;
import com.apps.soccerscores.view_models.FavouriteMatchViewModel;

import java.util.List;

public class FavouriteMatch extends RecyclerView.Adapter<FavouriteMatch.ViewHolder> {

    List<FavouriteMatches> favouriteMatches;

    public FavouriteMatch(List<FavouriteMatches> favouriteMatches) {
        this.favouriteMatches = favouriteMatches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        ListItemSoccerMatchBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.list_item_soccer_match, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        FavouriteMatches favouriteMatch = favouriteMatches.get(position);
        viewHolder.bind(favouriteMatch, createClickListener(favouriteMatch.getMatch().getTitle()));
    }

    private View.OnClickListener createClickListener(String plantId) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoccerFragmentDirections.ActionSoccerFragmentToPlantDetailFragment direction =
                        SoccerFragmentDirections.actionSoccerFragmentToPlantDetailFragment(plantId);
                Navigation.findNavController(view).navigate(direction);

            }
        };
    }

    @Override
    public int getItemCount() {
        return favouriteMatches.size();
    }

    public void updateList(List<FavouriteMatches> favourites) {
        FavouriteMatchDiffCallback diffCallback = new FavouriteMatchDiffCallback(this.favouriteMatches, favourites);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.favouriteMatches.clear();
        this.favouriteMatches.addAll(favourites);
        diffResult.dispatchUpdatesTo(this);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemSoccerMatchBinding binding;

        ViewHolder(@NonNull ListItemSoccerMatchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(FavouriteMatches favouriteMatches, View.OnClickListener clickListener) {
            binding.setViewModel(new FavouriteMatchViewModel(
                    binding.getRoot().getContext(), favouriteMatches));
            binding.setClickListener(clickListener);
            binding.executePendingBindings();
        }
    }
}

