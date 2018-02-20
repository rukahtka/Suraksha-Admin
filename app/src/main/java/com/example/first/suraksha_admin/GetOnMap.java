package com.example.first.suraksha_admin;

import android.*;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by hp on 15/10/17.
 */

public class GetOnMap extends Fragment {

    MapView mMap;
    GoogleMap mGoogleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lay_getonmap, null);
        mMap = (MapView) view.findViewById(R.id.map);
        mMap.onCreate(savedInstanceState);
        mMap.onResume();


        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;

                Bundle bundle = getArguments();
                Message places = (Message) bundle.getSerializable("data");
                mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(places.getLat(), places.getLng())).title(places.getName()).flat(true));
                if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mGoogleMap.setMyLocationEnabled(true);
                CameraPosition.Builder builder =
                        CameraPosition.builder();

                CameraPosition cameraPosition =
                        builder.target(new LatLng(places.getLat(),places.getLng()))
                                .zoom(16)
                                .build();

                CameraUpdate cameraUpdate =
                        CameraUpdateFactory.newCameraPosition( cameraPosition );

                mGoogleMap.animateCamera(
                        cameraUpdate, 5000, null
                );


            }
        });
        return view;
    }
}
