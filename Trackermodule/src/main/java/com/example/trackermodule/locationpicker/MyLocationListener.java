package com.example.trackermodule.locationpicker;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MyLocationListener implements LocationListener {
    Context context;
    LocationFetchCallback locationFetchCallback;

    public MyLocationListener(Context context, LocationFetchCallback locationFetchCallback) {
        this.context = context;
        this.locationFetchCallback = locationFetchCallback;
    }

    @Override
    public void onLocationChanged(Location loc) {
        Toast.makeText(
                context,
                "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                        + loc.getLongitude(), Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " + loc.getLongitude();
        String latitude = "Latitude: " + loc.getLatitude();

        /*------- To get city name from coordinates -------- */
        String cityName = null;
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(loc.getLatitude(),
                    loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
                + cityName;

        AddresData addresData = new AddresData();
        addresData.lattitude = latitude;
        addresData.longitude = longitude;
        addresData.Locality = cityName;
        locationFetchCallback.OnLocationFetched(addresData);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(context, "onProviderDisabled", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(context, "onProviderEnabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(context, "onStatusChanged", Toast.LENGTH_SHORT).show();
    }

}
