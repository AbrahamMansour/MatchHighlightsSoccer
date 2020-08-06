package com.apps.soccerscores.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.soccerscores.R;
import com.apps.soccerscores.data.Match;
import com.apps.soccerscores.databinding.ListItemMatchBinding;
import com.apps.soccerscores.ui.MatchListFragmentDirections;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder>{
    private List<Match> matchList;
    private LayoutInflater layoutInflater;

    public MatchAdapter(List<Match> matches) {
        matchList = matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ListItemMatchBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_match, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matchList.get(position);
        holder.bind(match, createClickListener(match.getTitle()));
    }

    private View.OnClickListener createClickListener(String plantId) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchListFragmentDirections.ActionPlantListFragmentToPlantDetailFragment direction =
                        MatchListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(plantId);
                Navigation.findNavController(view).navigate(direction);

            }
        };
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public void updateItems(List<Match> matches) {
        final MatchDiffCallback diffCallback = new MatchDiffCallback(this.matchList, matches);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        matchList.clear();
        matchList.addAll(matches);
        diffResult.dispatchUpdatesTo(this);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ListItemMatchBinding binding;

        ViewHolder(@NonNull ListItemMatchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Match match, View.OnClickListener clickListener) {
            binding.setClickListener(clickListener);
            binding.setMatch(match);
            binding.executePendingBindings();
        }
    }
}
