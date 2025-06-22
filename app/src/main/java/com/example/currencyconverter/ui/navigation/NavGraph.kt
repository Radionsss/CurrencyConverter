package com.example.currencyconverter.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.currencyconverter.ui.currency.CurrencyListScreen
import com.example.currencyconverter.ui.exchangeScreen.ExchangeScreen
import com.example.currencyconverter.ui.transaction.TransactionsScreen

@Composable
fun Navigation(
    startDestination: Route,
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<Route.CurrencyScreen> {
            CurrencyListScreen { fromCode, toCode, amountToBuy, rate ->
                Log.d("CurrencyListScreen", "Navigating to ExchangeScreen with: from=$fromCode, to=$toCode, amount=$amountToBuy, rate=$rate")

                navController.navigate(
                    Route.ExchangeScreen(fromCode, toCode, amountToBuy, rate)
                )
            }
        }

        composable<Route.ExchangeScreen> { backStackEntry ->


            val fromCode = backStackEntry.arguments?.getString("fromCode") ?: run {
                Log.e("ExchangeScreen", "Missing 'from'")
                return@composable
            }
//Navigating to ExchangeScreen with: from=RUB, to=EUR, amount=20.0, rate=1.0
            val toCode = backStackEntry.arguments?.getString("toCode") ?: run {
                Log.e("ExchangeScreen", "Missing 'to'")
                return@composable
            }

            val amountToBuy = backStackEntry.arguments?.getDouble("amountToBuy") ?: run {
                Log.e("ExchangeScreen", "Invalid or missing 'amount'")
                return@composable
            }

            val rate = backStackEntry.arguments?.getDouble("rate")?: run {
                Log.e("ExchangeScreen", "Invalid or missing 'rate'")
                return@composable
            }
            ExchangeScreen(
                fromCode = fromCode,
                toCode = toCode,
                amountToBuy = amountToBuy,
                rate = rate,
                onBuyClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable<Route.TransactionsScreen> {
            TransactionsScreen()
        }
    }
}

