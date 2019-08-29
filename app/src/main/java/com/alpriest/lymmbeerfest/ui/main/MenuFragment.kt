package com.alpriest.lymmbeerfest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.alpriest.lymmbeerfest.R
import com.github.barteksc.pdfviewer.PDFView

class MenuFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.menu, container, false)

        val pdfView = fragment.findViewById<PDFView>(R.id.pdfv)
        pdfView.fromAsset("lymmbeerfest-drinks-2018.pdf").load()

        return fragment
    }
}

