package liam.example.com.weatherreport.utils;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import liam.example.com.weatherreport.home.MainContract;

/**
 * Created by lduf0001 on 31/01/2017.
 */

public class LocationProvider implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static final String TAG = LocationProvider.class.getSimpleName();
    public static final int ONE_SECOND = 1000;
    public static final int LOCATION_SETTING_REQUEST_CODE = 22;
    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
    private static final int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationCallback locationCallback;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Activity activity;

    public LocationProvider(MainContract.MainView context) {

        // Create the LocationRequest object
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(ONE_SECOND)        // 10 seconds, in milliseconds
                .setFastestInterval(100); // 1 second, in milliseconds

        activity = (Activity) context;
    }

    public void connect(LocationCallback callback) {
        locationCallback = callback;
        googleApiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    public void disconnect() {
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
        locationCallback = null;
        activity = null;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected.");
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION }, 1);
        } else {
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (null == location) {
                requestLocationSettingsChange();
            } else {
                locationCallback.handleNewLocation(location);
            }
        }

    }

    private void requestLocationSettingsChange() {

        LocationSettingsRequest.Builder locationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        PendingResult<LocationSettingsResult> locationSettingsResult = LocationServices.SettingsApi.checkLocationSettings(googleApiClient,
                locationSettingsRequest.build());

        locationSettingsResult.setResultCallback(result -> {
            Status status = result.getStatus();
            if (LocationSettingsStatusCodes.SUCCESS == status.getStatusCode()) {
                requestLocationUpdate();
            } else if (LocationSettingsStatusCodes.RESOLUTION_REQUIRED == status.getStatusCode()) {
                launchChangeSettingsRequestDialog(status);
            } else if (LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE == status.getStatusCode()) {
                locationCallback.handleLocationError(new Throwable("location settings change error"));
            }
        });
    }

    private void launchChangeSettingsRequestDialog(Status status) {
        try {
            status.startResolutionForResult(activity, LOCATION_SETTING_REQUEST_CODE);
        } catch (IntentSender.SendIntentException e) {
            locationCallback.handleLocationError(e);
        }
    }

    private void requestLocationUpdate() {
        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended: ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
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
                locationCallback.handleLocationError(e);
            }
        } else {
            locationCallback.handleLocationError(new Throwable("Location services connection failed with code " + connectionResult.getErrorCode()));
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: ");
        locationCallback.handleNewLocation(location);
    }

    public interface LocationCallback {
        void handleNewLocation(Location location);

        void handleLocationError(Throwable error);
    }
}