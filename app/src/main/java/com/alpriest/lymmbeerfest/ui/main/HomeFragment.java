package com.alpriest.lymmbeerfest.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alpriest.lymmbeerfest.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class HomeFragment extends Fragment {
    private MapView mapView;
    private LatLng villageHall = new LatLng(53.381098, -2.476424);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragment = inflater.inflate(R.layout.home, container, false);

        this.mapView = fragment.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(getContext());

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(villageHall, 17));

                MarkerOptions marker = new MarkerOptions().position(villageHall).title("Lymm Village Hall");
                googleMap.addMarker(marker);
            }
        });

        fragment.findViewById(R.id.directions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = String.format(Locale.ENGLISH,"https://www.google.com/maps/dir/?api=1&destination=%f,%f", villageHall.latitude, villageHall.longitude);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
