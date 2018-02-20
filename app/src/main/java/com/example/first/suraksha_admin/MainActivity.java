package com.example.first.suraksha_admin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.lay_sendmessagedialogbox);
                txt = (TextView) dialog.findViewById(R.id.dialogBoxTextView);


                Button btn = (Button) dialog.findViewById(R.id.dialogBoxSendBtn);
                dialog.setTitle("Send Message");
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(txt.length()!=0){
                            new AdminToUser().execute(txt.getText().toString(),""+Calendar.getInstance().getTime());
                            dialog.dismiss();
                            new SentMessageDataBaseOpenHelper(MainActivity.this, "db_problemDataBase", null, 1).addProblem(txt.getText().toString(), "" + Calendar.getInstance().getTime());
                        }
                    }
                });
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_allusers) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AllUserDetails_Fragment() ).addToBackStack(null).commit();
        }
        if (id == R.id.nav_receivedProblem) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ReceivedMessagesFromUsers() ).addToBackStack(null).commit();
        } else if (id == R.id.nav_sentMessage) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SentMessagesToUsers()).addToBackStack(null).commit();
        }
        else if(id== R.id.chatAdmin){
            startActivity(new Intent(MainActivity.this,Login.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}