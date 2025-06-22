package com.example.currencyconverter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.R
import com.example.currencyconverter.ui.common.extensions.ui.maksShadow
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme
import com.example.currencyconverter.ui.navigation.Navigation
import com.example.currencyconverter.ui.navigation.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val selectedIndex = remember { mutableIntStateOf(0) }
            val navController = rememberNavController()
            CurrencyConverterTheme(statusBarColor = colorResource(R.color.white)) {
                Scaffold(
                    containerColor = colorResource(R.color.white), bottomBar = {
                        Box(modifier = Modifier) {
                            Column(
                                modifier = Modifier
                                    .maksShadow(
                                        color = Color.Black.copy(alpha = 0.3f), blurRadius = 5.dp
                                    )
                                    .background(Color.White)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    val icons = listOf(
                                        R.drawable.ic_list_bb_menu,
                                     //   R.drawable.ic_exchange_bb_menu,
                                        R.drawable.ic_transaction_bb_menu
                                    )

                                    icons.forEachIndexed { index, icon ->
                                        Icon(
                                            painter = painterResource(id = icon),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clickable(
                                                    interactionSource = remember { MutableInteractionSource() },
                                                    indication = null
                                                ) {
                                                    if (selectedIndex.intValue != index) {

                                                        selectedIndex.intValue = index

                                                        when (selectedIndex.intValue) {
                                                            0 -> {
                                                                navController.navigate(Route.CurrencyScreen)
                                                            }

                                                            1 -> {
                                                                navController.navigate(Route.TransactionsScreen)
                                                            }

                                                            else -> {}
                                                        }
                                                    }
                                                },
                                            tint = if (selectedIndex.intValue == index) {
                                                colorResource(R.color.black)
                                            } else {
                                                colorResource(R.color.dark_gray)
                                            }
                                        )
                                    }
                                }
                            }
                        }

                    }) {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .background(color = colorResource(R.color.white))
                            .fillMaxSize()
                    ) {
                        Navigation(Route.CurrencyScreen, navController)
                    }
                }
            }
        }
    }
}
