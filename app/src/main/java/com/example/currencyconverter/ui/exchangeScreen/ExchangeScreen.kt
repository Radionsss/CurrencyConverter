package com.example.currencyconverter.ui.exchangeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.ui.common.extensions.font.mainFont
import com.example.currencyconverter.ui.common.extensions.getCurrencyFullName
import com.example.currencyconverter.ui.common.extensions.getCurrencySymbol
import com.example.currencyconverter.ui.common.extensions.getFlagResId
import java.util.Locale

@Composable
fun ExchangeScreen(
    fromCode: String,
    toCode: String,
    amountToBuy: Double,
    rate: Double,
    onBuyClicked: () -> Unit = {}
) {
    val context = LocalContext.current
    val viewModel: ExchangeViewModel = hiltViewModel()
    val accounts = viewModel.accounts.collectAsState().value
    val balanceFrom = accounts.firstOrNull { it.code == fromCode }?.amount

    LaunchedEffect(fromCode, toCode) {
        viewModel.loadRate(fromCode, toCode)
    }
    val displayRate = rate / amountToBuy
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Exchange $fromCode to $toCode",
            fontFamily = mainFont,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black
        )
        Text(
            "1 $toCode = ${String.format(Locale.US, "%.4f", displayRate)} $fromCode",
            fontFamily = mainFont,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(24.dp))

        CurrencyAmountCard(
            code = toCode,
            amount = amountToBuy,
            sign = "+",
        )
        Spacer(Modifier.height(8.dp))
        CurrencyAmountCard(
            code = fromCode,
            amount = (displayRate * amountToBuy).let { (it * 100).toInt() / 100.0 }, sign = "–",
            balance = balanceFrom
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.buy(
                    from = fromCode,
                    to = toCode,
                    amountFrom = displayRate * amountToBuy,
                    amountTo = amountToBuy,
                    onSuccess = { onBuyClicked() }
                )
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.primary_blue),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                fontFamily = mainFont,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                text = "Купить ${toCode.getCurrencyFullName(context)} за ${
                    fromCode.getCurrencyFullName(
                        context
                    )
                }",
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun CurrencyAmountCard(
    code: String,
    amount: Double,
    sign: String,
    balance: Double? = null,
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1f)) {

            Image(
                painter = painterResource(id = code.getFlagResId()),
                contentDescription = code,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(82.dp)
                    .height(60.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = code,
                    fontFamily = mainFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = code.getCurrencyFullName(context),
                    fontFamily = mainFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Black
                )
                if (balance != null) {
                    val formattedBalance = String.format(Locale.US, "%,.2f", balance)
                        .replace(".", ",")

                    Text(
                        text = "Balance: $formattedBalance ${code.getCurrencySymbol()}",
                        fontFamily = mainFont,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        fontSize = 12.sp

                    )
                }
            }
        }
        Text(
            text = "$sign$amount ${code.getCurrencySymbol()}",
            fontFamily = mainFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
    }

}