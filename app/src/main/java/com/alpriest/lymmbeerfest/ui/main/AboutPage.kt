package com.alpriest.lymmbeerfest.ui.main

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alpriest.lymmbeerfest.R
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.GoldButton

class AboutPage {
    @Composable
    fun content(onOpenUrl: (Uri) -> Unit, onSendEmail: (String) -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier.padding(bottom = 48.dp),
                text = stringResource(R.string.history)
            )

            GoldButton(
                modifier = Modifier
                    .padding(bottom = 48.dp),
                text = stringResource(id = R.string.email)
            ) {
                onSendEmail("beerfestival@lymmroundtable.co.uk")
            }

            Image(
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .fillMaxWidth()
                    .clickable { onOpenUrl(Uri.parse("https://www.lymmroundtable.co.uk/")) },
                painter = painterResource(id = R.drawable.rtbi_logo),
                contentDescription = "Lymm & District RoundTable"
            )

            Image(
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .clickable { onOpenUrl(Uri.parse("https://www.facebook.com/LymmBeerFest/")) },
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "Find us on Facebook"
            )

            Image(
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .clickable { onOpenUrl(Uri.parse("https://www.drinkaware.co.uk/")) },
                painter = painterResource(id = R.drawable.drinkaware),
                contentDescription = "Drink Aware"
            )

        }
    }

}