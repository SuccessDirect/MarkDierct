package com.direct.success.markdirect.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.fragments.AboutUsFragment;
import com.direct.success.markdirect.fragments.HistoricalFragment;
import com.direct.success.markdirect.fragments.LogOutFragment;
import com.direct.success.markdirect.fragments.OfertasListFragment;
import com.direct.success.markdirect.fragments.ProximityFragment;
import com.direct.success.markdirect.fragments.SettingsFragment;
import com.direct.success.markdirect.model.Bacon;
import com.direct.success.markdirect.utils.Notifications;
import com.direct.success.markdirect.utils.Utils;

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

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.direct.success.markdirect.utils.Utils.MessageType.DIALOG;
import static com.direct.success.markdirect.utils.Utils.MessageType.TOAST;


public class GeneralActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BeaconConsumer {

    private static final int REQUEST_CODE_ASK_FOR_LOCATION_PERMISSION = 10;
    // private List<Oferta> listOfOfertas = new LinkedList<>();
    // private OfertasAdapter adapter;
    OfertasListFragment ofertasListFragment;
    private boolean principal = false;

    private static final String TAG = "BeaconsEverywhere";
    private static final long MILLISECONDS_IN_A_DAY = 60000;//86400000;
    private BeaconManager beaconManager;
    private Bacon bacon = new Bacon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        permission();

        try{
            Intent intent = getIntent();
            int major = intent.getIntExtra("MAJOR",-1);
            int minor = intent.getIntExtra("MINOR",-1);
            if( major != -1 && minor != -1)
            {
                FragmentManager fragmentManager = getSupportFragmentManager();

                ProximityFragment fragment = new ProximityFragment();
                fragment.setMajor(major);
                fragment.setMinor(minor);

                fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
                principal = false;
            }
            else{
                FragmentManager fragmentManager = getSupportFragmentManager();

                Fragment fragment = new OfertasListFragment();

                fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
                principal = true;
            }
        }catch (Exception e){
            Log.d("Intent","Inicio general, no vengo de una notificación.");
        }

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
        if (principal) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {

            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new OfertasListFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general, fragment).commit();
            principal = true;

        }
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }*/
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.general) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new OfertasListFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();

            principal = true;
        } else if (id == R.id.proximidad) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new ProximityFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
            principal = false;

        } else if (id == R.id.ajustes) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new SettingsFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
            principal = false;
        } else if (id == R.id.about_us) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new AboutUsFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
            principal = false;
        } else if (id == R.id.trazabilidad) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new HistoricalFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
            principal = false;
        }else if (id == R.id.log_out) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = new LogOutFragment();

            fragmentManager.beginTransaction().replace(R.id.content_general,fragment).commit();
            principal = false;
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
                                Notifications.postNotification(getBaseContext(), GeneralActivity.class, "Nueva Oferta", "Oferta disponible",R.mipmap.imagotipo_m, 889988,bacon.getMajor(), bacon.getMinor());
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
                            Notifications.postNotification(getBaseContext(), GeneralActivity.class, "Nueva Oferta", "Oferta disponible" ,R.mipmap.imagotipo_m, 889988, bacon.getMajor(), bacon.getMinor());
                            realm.beginTransaction();
                            realm.copyToRealm(bacon);
                            realm.commitTransaction();
                            Log.d(TAG, "Sí no esta en la base de datos el minor");
                        }

                    }else{//sí la base de datos no tiene nada
                        Notifications.postNotification(getBaseContext(), GeneralActivity.class, "Nueva Oferta", "Oferta disponible", R.mipmap.imagotipo_m, 889988, bacon.getMajor(), bacon.getMinor());
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
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_FOR_LOCATION_PERMISSION) {
            if (ContextCompat.checkSelfPermission(this,
                    ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                String msg = getString(R.string.permission_granted);
                Utils.showMessage(this, msg, TOAST, null);
            }
            else {
                String title = getString(R.string.permission_denied_location_title);
                String msg = getString(R.string.permission_denied_location_msg);
                Utils.showMessage(this, msg, DIALOG, title);
            }
        }
    }
    void permission(){
        int isGPSTrackingEnabled = ActivityCompat.checkSelfPermission(getBaseContext(), ACCESS_FINE_LOCATION);
        int assistedGPSEnabled = ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        if (isGPSTrackingEnabled != PackageManager.PERMISSION_GRANTED && assistedGPSEnabled != PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            }
            // If the app has not been granted permission yet, ask the user to grant it
            // (if he already rejected this in the past, show him a short explanation first)
            else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)) {
                    String title = getString(R.string.permission_location_rationale_title);
                    String msg = getString(R.string.permission_location_rationale_msg);
                    Utils.showAcceptDialog(this, title, msg, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(GeneralActivity.this,
                                    new String[]{ACCESS_FINE_LOCATION},
                                    REQUEST_CODE_ASK_FOR_LOCATION_PERMISSION
                            );
                        }
                    });
                }
                else {
                    ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},
                            REQUEST_CODE_ASK_FOR_LOCATION_PERMISSION
                    );
                }
            }

            return;
        }
    }
}
