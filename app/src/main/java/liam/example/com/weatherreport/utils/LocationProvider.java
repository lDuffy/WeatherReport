package liam.example.com.weatherreport.utils;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import liam.example.com.weatherreport.home.MainActivity;
import liam.example.com.weatherreport.home.MainContract;

/**
 * Created by lduf0001 on 31/01/2017.
 */

public class LocationProvider implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static final String TAG = LocationProvider.class.getSimpleName();
    public static final int TEN_SECOND = 10000;
    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationCallback locationCallback;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Activity activity;

    public LocationProvider(MainContract.MainView context, LocationCallback callback) {
        googleApiClient = new GoogleApiClient.Builder((MainActivity)context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        locationCallback = callback;

        // Create the LocationRequest object
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(TEN_SECOND)        // 10 seconds, in milliseconds
                .setFastestInterval(1000); // 1 second, in milliseconds

        activity = (MainActivity)context;
    }

    public void connect() {
        googleApiClient.connect();
    }

    public void disconnect() {
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
        activity = null;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected.");
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            ActivityCompat.requestPermissions(activity
                    ,
                    new String[] { Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION },
                    1);
        } else {
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (null == location) {
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
            } else {
                locationCallback.handleNewLocation(location);
            }
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            /*
             * Thrown if Google Play services canceled the original
             * PendingIntent
             */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                Log.e(TAG, e.getMessage());
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        locationCallback.handleNewLocation(location);
    }

    @FunctionalInterface
    public interface LocationCallback {
        void handleNewLocation(Location location);
    }
}