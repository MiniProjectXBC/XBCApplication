package xbc.miniproject.com.xbcapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import xbc.miniproject.com.xbcapplication.fragment.BiodataFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.open,
                R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("XBC MOBILE APPS");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Bagian Menu Yang Terkoneksi dengan Fragment
        int id = menuItem.getItemId();

        //Menu Biodata
        if (id == R.id.menuBiodata) {
            setActionBarTitle("Biodata");
            BiodataFragment biodataFragment= new BiodataFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_all_menu, biodataFragment,"Biodata");
            fragmentTransaction.commit();
        }
        //Menu Trainer
        else if (id == R.id.menuTrainer) {

        }
        //Menu Technologi
        else if (id == R.id.menuTechnology) {

        }
        //Menu Bootcamp
        else if (id == R.id.menuBatch) {

        } else if (id == R.id.menuClass) {

        }
        //Menu Assestment
        else if (id == R.id.menuFiltering) {

        } else if (id == R.id.menuMiniProject) {

        } else if (id == R.id.menuCustom) {

        }
        //Menu Portal
        else if (id == R.id.menuFeedback) {

        } else if (id == R.id.menuIdleNews) {

        } else if (id == R.id.menuKataIdle) {

        }
        //Menu Idle
        else if (id == R.id.menuMonitoring) {

        } else if (id == R.id.menuAssignment) {

        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //deteksi klik burger icon
        if (id == android.R.id.home){
            //slide navigation drawer
            drawerLayout.openDrawer(Gravity.LEFT);
        }
        return super.onOptionsItemSelected(item);
    }
}

