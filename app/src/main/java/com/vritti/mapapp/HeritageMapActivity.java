package com.vritti.mapapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeritageMapActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback {

    private static final String GOOGLE_API_KEY = "AIzaSyDhS8m7W-XmHXVo1xPvOjjrYN5PlueQxYM";
    GoogleMap googleMap;
    EditText placeText;
    double latitudemain = 17.949551;
    double longitudemain = 73.883728;
    LatLng latLng;
    Boolean TempleData = false;
    private int PROXIMITY_RADIUS = 50000;
    LocationManager locationManager;
    Location location;
    String bestProvider;
    DatabaseHelper dbHelper;
    PlaceDetailsBean placeDetailsBean;
    ArrayList<PlaceDetailsBean> placedetailsbeanlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_places);

        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            //finish();
        }
        TempleData = false;

        dbHelper = new DatabaseHelper(HeritageMapActivity.this);

        placeText = (EditText) findViewById(R.id.placeText);
        placeText.setVisibility(View.GONE);

        Button btnFind = (Button) findViewById(R.id.btnFind);
        btnFind.setVisibility(View.GONE);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    /*if (TempleData==false) {
                        latLng = new LatLng(latitudemain, longitudemain);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15.5f));
                        Log.e("TempleData", TempleData.toString());
                        setMyMap();
                    }*/
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    /*if (TempleData==false) {
                        latLng = new LatLng(17.949551, 73.883728);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15.5f));
                        Log.e("TempleData", TempleData.toString());
                        setMyMap();
                    }*/
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
    }

    private String getURL(String lat , String lng) {
        String web =null;
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sql = db.getWritableDatabase();

        Cursor c = sql.rawQuery("Select * from " + DatabaseHelper.PLACES_TABLE_NAME +" where "
                +DatabaseHelper.lat+ " ='"+lat+"' and "+
                DatabaseHelper.lng + " = '"+lng+"'", null);
        int count = c.getCount();
        if (count== 0){
            c.close();
            sql.close();
            db.close();
        }else{
            c.moveToFirst();
            web = c.getString( c.getColumnIndex(DatabaseHelper.reference) );
            c.close();
            sql.close();
            db.close();
        }
        return web;
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        /*latitude = location.getLatitude();
        longitude = location.getLongitude();*/

        /*if (TempleData==false) {
        latLng = new LatLng(latitudemain, longitudemain);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
            Log.e("TempleData", TempleData.toString());
            //setMyMap();
        }*/
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        googleMap = mMap;
        LatLng sydney = new LatLng(17.949551, 73.883728);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in wai"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15.5f));
        setMyMap();
    }

    private void setMyMap() {
        getplacesfromDB();
        displaymarkers();
    }


    public void displaymarkers() {
        googleMap.clear();
        for (int i = 0; i < placedetailsbeanlist.size(); i++) {

            MarkerOptions markerOptions = new MarkerOptions();
            PlaceDetailsBean googlePlace = placedetailsbeanlist.get(i);
            double lat = Double.parseDouble(googlePlace.getLat());
            double lng = Double.parseDouble(googlePlace.getLng());
            String placeName = googlePlace.getPlace_name();
            String vicinity = googlePlace.getVicinity();
            String type = googlePlace.getType();
            String info = googlePlace.getInfo();
            LatLng latLng = new LatLng(lat, lng);
            Log.e("Place name", placeName);
            markerOptions.position(latLng);
             if (type.equalsIgnoreCase("Bus Stand")){
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whbus));
            } else if (type.equalsIgnoreCase("Church")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whchurch));
            } else if (type.equalsIgnoreCase("Cowshed")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whcowshade));
            } else if (type.equalsIgnoreCase("Dam")){
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whdam));
            } else if (type.equalsIgnoreCase("Fuel")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whpetrolpump));
            } else if (type.equalsIgnoreCase("Park")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whpark));
            }  else if (type.equalsIgnoreCase("Food")){
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whfood));
            } else if (type.equalsIgnoreCase("Library")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whlibrary));
            } else if (type.equalsIgnoreCase("Mosque")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whchand));
            }  else if (type.equalsIgnoreCase("Naturespoint")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whnaturespoint));
            } else if (type.equalsIgnoreCase("Office")){
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whoffice));
            } else if (type.equalsIgnoreCase("Railway Station")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whtrain));
            } else if (type.equalsIgnoreCase("School")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.wheducation));
            } else if (type.equalsIgnoreCase("Temple")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whtemple));
            } else if (type.equalsIgnoreCase("WaterSports")){
                 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.whboat));
            }

            markerOptions.title(placeName/*+ " : " + vicinity*/);
            googleMap.addMarker(markerOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.5f));

            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    String title = marker.getTitle();

                /*intent1.putExtra("markertitle", title);
                startActivity(intent1);*/
                    LatLng position = marker.getPosition();
                    Intent intent1 = new Intent(HeritageMapActivity.this,InfoActivity.class);
                    //String url = getURL(position.latitude,position.longitude);
                    Bundle extras = new Bundle();
                    extras.putString("markerLat", String.valueOf(position.latitude));
                    extras.putString("markerLng", String.valueOf(position.longitude));
                    intent1.putExtras(extras);
                    startActivity(intent1);
                    /*if (url.contains("WIP") || url == null){
                        Toast.makeText(HeritageMapActivity.this, "Coming Soon... ",Toast.LENGTH_SHORT).show();
                    }else {
                        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                        httpIntent.setData(Uri.parse(url));
                        startActivity(httpIntent);
                    }*/
                }
            });

        }
        TempleData =true ;
        Log.e("TempleData", TempleData.toString());/*
        LatLng latLngmain = new LatLng(latitudemain, longitudemain);
        googleMap.addMarker(new MarkerOptions().position(latLngmain).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngmain, 15f));*/
    }

    public void getplacesfromDB() {
        // TODO Auto-generated method stub
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sql = db.getWritableDatabase();
        placedetailsbeanlist = new ArrayList<PlaceDetailsBean>();
        placedetailsbeanlist.clear();

        Cursor c = sql.rawQuery("Select * from " + DatabaseHelper.PLACES_TABLE_NAME, null);
        int count = c.getCount();
        if (count== 0){
            c.close();
            sql.close();
            db.close();
        }else{
            c.moveToFirst();
            int column = 0;
            do{
                placeDetailsBean = new PlaceDetailsBean();
                placeDetailsBean.setPlace_name(c.getString( c.getColumnIndex(DatabaseHelper.place_name) ));;
                placeDetailsBean.setVicinity(c.getString( c.getColumnIndex(DatabaseHelper.vicinity) ));
                placeDetailsBean.setType(c.getString( c.getColumnIndex(DatabaseHelper.type) ));
                placeDetailsBean.setInfo(c.getString( c.getColumnIndex(DatabaseHelper.info) ));
                placeDetailsBean.setLat(c.getString( c.getColumnIndex(DatabaseHelper.lat) ));
                placeDetailsBean.setLng(c.getString( c.getColumnIndex(DatabaseHelper.lng) ));
                placeDetailsBean.setReference(c.getString( c.getColumnIndex(DatabaseHelper.reference) ));
                placedetailsbeanlist.add(placeDetailsBean);
            }while(c.moveToNext());
            c.close();
            sql.close();
            db.close();
        }
    }
}