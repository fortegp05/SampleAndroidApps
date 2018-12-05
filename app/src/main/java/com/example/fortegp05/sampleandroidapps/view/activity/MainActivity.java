package com.example.fortegp05.sampleandroidapps.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.fortegp05.sampleandroidapps.R;
import com.example.fortegp05.sampleandroidapps.view.fragment.ConnpassEventListFragment;
import com.example.fortegp05.sampleandroidapps.view.fragment.GithubRepositoryListFragment;
import com.example.fortegp05.sampleandroidapps.view.fragment.MainFragment;
import com.example.fortegp05.sampleandroidapps.view.fragment.SqliteSampleEditFragment;
import com.example.fortegp05.sampleandroidapps.view.fragment.SqliteSampleListFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contailner, new MainFragment(), MainFragment.TAG).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        String tag;

        // メニュー切り替え時の処理
        switch (id) {
            case R.id.menu_top:
                fragment = new MainFragment();
                tag = MainFragment.TAG;
                break;
            case R.id.menu_github_repository_list:
                fragment = new GithubRepositoryListFragment();
                tag = GithubRepositoryListFragment.TAG;
                break;
            case R.id.menu_connpass_event_list:
                fragment = new ConnpassEventListFragment();
                tag = ConnpassEventListFragment.TAG;
                break;
            case R.id.menu_sqlite_sample_list:
                fragment = new SqliteSampleListFragment();
                tag = SqliteSampleListFragment.TAG;
                break;
            case R.id.menu_sqlite_sample_edit:
                fragment = new SqliteSampleEditFragment();
                tag = SqliteSampleEditFragment.TAG;
                break;
            default:
                return false;
        }

        // Fragmentの切り替え
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contailner, fragment, tag).addToBackStack(null).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
