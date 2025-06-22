package com.example.currencyconverter.ui.currency

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R
import com.example.currencyconverter.ui.common.extensions.font.mainFont
import com.example.currencyconverter.ui.currency.data.CurrencyUiModel

@Composable
fun CurrencyListItem(
    currency: CurrencyUiModel,
    isSelected: Boolean,
    isEditable: Boolean,
    amountText: String,
    onClick: () -> Unit,
    onAmountChange: (String) -> Unit,
    onAmountReset: () -> Unit
) {
    val background =
        if (isSelected) colorResource(R.color.primary_grey) else colorResource(R.color.primary_grey)
    //  val background = if (isSelected) Color(0xFFE0F7FA) else colorResource(R.color.primary_grey)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(background)
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1f)) {

            Image(
                painter = painterResource(id = currency.flagUrl),
                contentDescription = currency.code,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(82.dp)
                    .height(71.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {

                Text(
                    text = currency.code,
                    fontFamily = mainFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = currency.name,
                    fontFamily = mainFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Black
                )
                if (currency.balance != null) {
                    Text(
                        text = "Balance: ${currency.balance}",
                        fontFamily = mainFont,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        fontSize = 12.sp

                    )
                }
            }

        }
        if (isEditable) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = amountText,
                    onValueChange = onAmountChange,
                    modifier = Modifier.width(100.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = TextStyle(
                        fontFamily = mainFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "✕",
                    modifier = Modifier
                        .clickable { onAmountReset() }
                        .padding(4.dp)
                )

            }
        } else {
            Text(
                text = currency.formattedRate,
                fontFamily = mainFont,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}