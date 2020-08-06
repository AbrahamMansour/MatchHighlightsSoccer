package com.apps.soccerscores.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.apps.soccerscores.R;
import com.apps.soccerscores.databinding.ActivitySoccerBinding;
import com.google.android.material.navigation.NavigationView;

public class SoccerActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySoccerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_soccer);
        mDrawerLayout = binding.drawerLayout;

        setSupportActionBar(binding.toolbar);

        mNavController = Navigation.findNavController(this, R.id.soccer_nav_fragment);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mDrawerLayout);
        NavigationUI.setupWithNavController(binding.navigationView, mNavController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, mDrawerLayout);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_match_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.about) {
            Toast.makeText(this, "Soccer Match Highlights created by Abraham Mansour", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
