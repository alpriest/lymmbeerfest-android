package com.alpriest.lymmbeerfest.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private ArrayList<Brew> data = readData();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragment = inflater.inflate(R.layout.beer_selector, container, false);

        this.wheel = fragment.findViewById(R.id.lwv);
        wheel.addWheelItems(wheelItems());

        wheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget(int target) {
                TextView textView = fragment.findViewById(R.id.answer);
                textView.setText(data.get(target).name);
            }
        });

        fragment.findViewById(R.id.spin_wheel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wheel.rotateWheelTo(random.nextInt(data.size()));
            }
        });

        return fragment;
    }

    private ArrayList<Brew> readData() {
        ArrayList<Brew> items = new ArrayList<>();

        items.add(new Brew("1", "text 4"));
        items.add(new Brew("1", "text 3"));
        items.add(new Brew("1", "text 2"));
        items.add(new Brew("1", "text 1"));

        return items;
    }

    private ArrayList<WheelItem> wheelItems() {
        ArrayList<WheelItem> result = new ArrayList<>();

        for (Brew brew: data) {
            result.add(new WheelItem(Color.GRAY, brew.name));
        }

        return result;
    }
}

class Brew {
    String number;
    String name;

    Brew(String number, String name) {
        this.number = number;
        this.name = name;
    }
}