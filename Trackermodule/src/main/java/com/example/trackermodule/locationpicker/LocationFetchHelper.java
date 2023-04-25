package com.example.trackermodule.locationpicker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

public class LocationFetchHelper implements LocationFetchCallback {
    LocationManager locationManager;
    LocationListener locationListener;
    public LocationFetchCallback locationFetchCallback;
    public LocationFetchHelper(Context context, LocationFetchCallback locationFetchCallback) {
        locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        this.locationFetchCallback = locationFetchCallback;
         locationListener = new MyLocationListener(context,this);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return ;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
    }

    @Override
    public void OnLocationFetched(AddresData addresData) {
        if(locationManager!=null && locationListener!=null ) {
            locationManager.removeUpdates(locationListener);
        }
        if(this.locationFetchCallback!=null){
            this.locationFetchCallback.OnLocationFetched(addresData);
        }
    }
}
