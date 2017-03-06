package com.direct.success.markdirect.activities;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.fragments.HistoricalFragment;
import com.direct.success.markdirect.fragments.LogOutFragment;
import com.direct.success.markdirect.fragments.OfertasListFragment;
import com.direct.success.markdirect.fragments.ProximityFragment;
import com.direct.success.markdirect.fragments.SettingsFragment;
import com.direct.success.markdirect.model.Bacon;
import com.direct.success.markdirect.utils.Notifications;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;


public class GeneralActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BeaconConsumer {

    // private List<Oferta> listOfOfertas = new LinkedList<>();
    // private OfertasAdapter adapter;
    OfertasListFragment ofertasListFragment;

    private static final String TAG = "BeaconsEverywhere";
    private static final long MILLISECONDS_IN_A_DAY = 60000;//86400000;
    private BeaconManager beaconManager;
    private Bacon bacon = new Bacon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = new OfertasListFragment();

        fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();

        beaconManager = BeaconManager.getInstanceForApplication(this);

        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

        beaconManager.bind(this);





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

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
        //Intent i = new Intent(GeneralActivity.this, GeneralActivity.class);
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = new OfertasListFragment();

        fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }*/
    }
/*ELIMINAR ESTO Y EL MENU GENERAL!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.general) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new OfertasListFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
        } else if (id == R.id.proximidad) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new ProximityFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
        } else if (id == R.id.ajustes) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new SettingsFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
        } else if (id == R.id.about_us) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new OfertasListFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
        } else if (id == R.id.trazabilidad) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new HistoricalFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
        }else if (id == R.id.log_out) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new LogOutFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBeaconServiceConnect() {
        final Region region = new Region("myBeacons", Identifier.parse("699ebc80-e1f3-11e3-9a0f-0cf3ee3bc012"), null, null);

        beaconManager.setMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                try {
                    Log.d(TAG, "didEnterRegion");
                    beaconManager.startRangingBeaconsInRegion(region);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void didExitRegion(Region region) {
                try {
                    Log.d(TAG, "didExitRegion");
                    beaconManager.stopRangingBeaconsInRegion(region);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {

            }
        });

        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                for(final Beacon oneBeacon : beacons) {
                    Log.d(TAG, "distance: " + oneBeacon.getDistance() + " id:" + oneBeacon.getId1() + "/" + oneBeacon.getId2() + "/" + oneBeacon.getId3());
                    bacon = new Bacon();
                    bacon.setDate(new Date());
                    bacon.setUUID(oneBeacon.getId1().toString());
                    bacon.setMajor(oneBeacon.getId2().toInt());
                    bacon.setMinor(oneBeacon.getId3().toInt());

                    boolean isInRealm= false;
                    Date date = null;
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Bacon> listOfBaconInRealm = realm.where(Bacon.class).findAllSorted("minor");
                    if(listOfBaconInRealm.size()>0) {//Si la base de datos tiene algo
                        for (Bacon b : listOfBaconInRealm) {//busco todos los bacon de la base de datos
                            //dentro de este for solo hay que compararlos y poner una variable a true o 1 si son iguales, luego ya se harán
                            //el resto de comprobaciones.
                            if(bacon.getMinor() == b.getMinor()){
                                isInRealm=true;
                                date=b.getDate();
                            }
                        }
                        if (isInRealm) {//Si el bacon ya esta en la base de datos
                            //comprobar fecha
                            Log.d(TAG, "Si minor = minor base de datos ");
                            long time = date.getTime();

                            long millisecondsPassed = new Date().getTime() - time;
                            if (millisecondsPassed > MILLISECONDS_IN_A_DAY) {//Sí ha pasado más de un día desde la última notificación
                                // si la fecha es más vieja de un día mando notificación y guardo la nueva notificación en la BD(la fecha se ha cambiado)
                                Notifications.postNotification(getBaseContext(), GeneralActivity.class, "Nuevo beacon", "Si ha pasado mas de un día" + bacon.getMinor(),R.drawable.ic_menu_camera, 0xFF00FF00, 889988);
                                realm.beginTransaction();
                                realm.copyToRealmOrUpdate(bacon);
                                realm.commitTransaction();
                                Log.d(TAG, "si la fecha es más vieja.");
                            } else {//si no ha pasado más de un día no hay que hacer nada
                                //no hay que hacer nada
                                Log.d(TAG, "No hacer nada");
                            }
                        } else {//Sí el bacon que he visto no esta en la base de datos
                            //mando notificación y añado a realm ese bacon.
                            Notifications.postNotification(getBaseContext(), GeneralActivity.class, "Nuevo beacon", "Si hay algo en la base de datos, pero el bacon no" + bacon.getMinor() ,R.drawable.ic_menu_camera, 0xFF00FF00, 889988);
                            realm.beginTransaction();
                            realm.copyToRealm(bacon);
                            realm.commitTransaction();
                            Log.d(TAG, "Sí no esta en la base de datos el minor");
                        }

                    }else{//sí la base de datos no tiene nada
                        Notifications.postNotification(getBaseContext(), GeneralActivity.class, "Nuevo beacon", "No esta en la base de datos" + bacon.getMinor(), R.drawable.ic_menu_camera, 0xFF00FF00, 889988);
                        realm.beginTransaction();
                        realm.copyToRealm(bacon);
                        realm.commitTransaction();
                        Log.d(TAG, "Sí no hay nada en la base de datos.");
                    }
                }
            }
        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
