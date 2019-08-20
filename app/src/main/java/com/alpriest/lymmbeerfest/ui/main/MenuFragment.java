package com.alpriest.lymmbeerfest.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alpriest.lymmbeerfest.R;
import com.github.barteksc.pdfviewer.PDFView;

public class MenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.menu, container, false);

        PDFView pdfView = fragment.findViewById(R.id.pdfv);
        pdfView.fromAsset("lymmbeerfest-drinks-2018.pdf").load();

        return fragment;
    }
}
