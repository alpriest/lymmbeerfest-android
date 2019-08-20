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

public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.about, container, false);

        fragment.findViewById(R.id.web_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                openURL.setData(Uri.parse("https://www.lymmbeerfest.co.uk"));
                startActivity(openURL);
            }
        });

        fragment.findViewById(R.id.email_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","beerfestival@lymmroundtable.co.uk", null));
                startActivity(Intent.createChooser(intent, "Choose an Email client:"));
            }
        });

        fragment.findViewById(R.id.roundtable_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                openURL.setData(Uri.parse("https://www.lymmroundtable.co.uk"));
                startActivity(openURL);
            }
        });

        fragment.findViewById(R.id.facebook_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                openURL.setData(Uri.parse("https://www.facebook.com/lymmbeerfest"));
                startActivity(openURL);
            }
        });

        return fragment;
    }

}
