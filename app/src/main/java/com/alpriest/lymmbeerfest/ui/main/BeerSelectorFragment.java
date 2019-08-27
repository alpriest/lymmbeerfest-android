package com.alpriest.lymmbeerfest.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alpriest.lymmbeerfest.R;
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.LuckyWheel;
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.OnLuckyWheelReachTheTarget;
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.WheelItem;

import java.util.ArrayList;
import java.util.Random;

public class BeerSelectorFragment extends Fragment {
    private Random random = new Random();
    private LuckyWheel wheel;
    private ArrayList<Brew> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragment = inflater.inflate(R.layout.beer_selector, container, false);

        data = readData();

        this.wheel = fragment.findViewById(R.id.lwv);
        wheel.addWheelItems(wheelItems());

        wheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget(int target) {
                TextView textView = fragment.findViewById(R.id.answer);
                Brew brew = data.get(target);
                textView.setText("Number " + brew.number + "\n" + brew.name);
                fragment.findViewById(R.id.answer).setVisibility(View.VISIBLE);
            }
        });

        positionWheelHalfOffScreen();

        fragment.findViewById(R.id.spin_wheel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.findViewById(R.id.answer).setVisibility(View.INVISIBLE);
                wheel.rotateWheelTo(random.nextInt(data.size()));
            }
        });

        return fragment;
    }

    private void positionWheelHalfOffScreen() {
        DisplayMetrics display = getActivity().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams layoutParams = wheel.getLayoutParams();
        layoutParams.width = display.widthPixels * 2;
        layoutParams.height = display.heightPixels * 2;
        wheel.setLayoutParams(layoutParams);
        wheel.setTranslationY((display.heightPixels / 5) * 6);
    }

    private ArrayList<Brew> readData() {
        return new ArrayList<>(new BrewLoader(this.getActivity()).load());
    }

    private ArrayList<WheelItem> wheelItems() {
        ArrayList<WheelItem> result = new ArrayList<>();

        for (Brew brew: data) {
            result.add(new WheelItem(brew.androidColor(), brew.number));
        }

        return result;
    }
}

class Brew {
    String number;
    String name;
    private String colour;

    Brew(String number, String name, String colour) {
        this.number = number;
        this.name = name;
        this.colour = colour;
    }

    int androidColor() {
        return Color.parseColor("#" + colour);
    }
}
