package com.apps.soccerscores.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.apps.soccerscores.R;
import com.apps.soccerscores.databinding.ActivitySoccerBinding;

public class AboutFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openBrowserIntent("https://www.scorebat.com/video-api/");
    }

    private void openBrowserIntent(String URL) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(browser);
    }
}
