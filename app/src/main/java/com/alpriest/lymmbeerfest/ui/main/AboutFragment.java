package com.alpriest.lymmbeerfest.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
                launchUrl("https://www.lymmbeerfest.co.uk");
            }
        });

        fragment.findViewById(R.id.email_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "beerfestival@lymmroundtable.co.uk", null));
                startActivity(Intent.createChooser(intent, "Choose an Email client:"));
            }
        });

        fragment.findViewById(R.id.roundtable_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchUrl("https://www.lymmroundtable.co.uk");
            }
        });

        fragment.findViewById(R.id.facebook_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchUrl(getFacebookPageURL(getContext()));
            }
        });

        return fragment;
    }

    private void launchUrl(String url) {
        Intent openURL = new Intent(Intent.ACTION_VIEW);
        openURL.setData(Uri.parse(url));
        startActivity(openURL);
    }

    private String getFacebookPageURL(Context context) {
        String FACEBOOK_URL = "https://www.facebook.com/LymmBeerFest";
        String FACEBOOK_PAGE_ID = "478362802243571";

        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }
}
