package com.example.openeducationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    NavigationView navigationView;

    boolean[] filterDialogSelection = {true, true};
    String[] filterDialogItems = {"Aufgaben aus der Vergangenheit anzeigen", "Erledigte Aufgaben anzeigen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        Log.i("MainActivity", "onCreate: setting toolbar");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header = LayoutInflater.from(this).inflate(R.layout.nav_header, null);
        ((TextView) nav_header.findViewById(R.id.student_name)).setText(Student.getStudent().getFullName());
        ((TextView) nav_header.findViewById(R.id.class_name)).setText(Student.getStudent().getClassName());
        navigationView.removeHeaderView(navigationView.getHeaderView(0));
        navigationView.addHeaderView(nav_header);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        updatePreferences();
        updateUI();
    }

    protected void updateUI() {
        if (navigationView.getCheckedItem() == null) {
            navigationView.setCheckedItem(R.id.nav_tasks);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                MainFragment.newInstance(navigationView.getCheckedItem().getItemId())).commit();
    }

    protected void updatePreferences() {
        SharedPreferences preferences = getSharedPreferences("init", MODE_PRIVATE);
        for (int i = 0; i < filterDialogSelection.length; i++) {
            filterDialogSelection[i] = preferences.getBoolean(String.format(Locale.ENGLISH,"filterSelection%d", i), true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatePreferences();
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_tasks:
            case R.id.nav_classroom:
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        MainFragment.newInstance(item.getItemId())).commit();
                break;
            case R.id.nav_settings:
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case R.id.nav_logout:
                getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply();
                Toast.makeText(this, R.string.logout, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_exit:
                Intent leaveIntent = new Intent(Intent.ACTION_MAIN);
                leaveIntent.addCategory(Intent.CATEGORY_HOME);
                leaveIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(leaveIntent);
                finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.filter_icon) {
            AlertDialog dialog = makeFilterAlert();
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected AlertDialog makeFilterAlert() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setMultiChoiceItems(filterDialogItems, filterDialogSelection, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        SharedPreferences preferences = getSharedPreferences("init", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        try {
                            filterDialogSelection[i] = b;
                        } catch (IndexOutOfBoundsException be) {
                            be.printStackTrace();
                        }
                        editor.putBoolean(String.format(Locale.ENGLISH,"filterSelection%d",i),b);
                        editor.apply();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateUI();
                    }
                });
        return mBuilder.create();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        drawer.openDrawer(GravityCompat.START);
        //super.onBackPressed();
    }
}
