package com.example.currencyconverter.ui.transaction


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
import com.example.currencyconverter.ui.common.extensions.font.mainFont
import com.example.currencyconverter.ui.common.extensions.getFlagResId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TransactionsScreen(viewModel: TransactionsViewModel = hiltViewModel()) {
    val transactions = viewModel.transactions.collectAsState().value

    Column(Modifier.padding(16.dp)) {
        Text("Транзакции", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = colorResource(R.color.primary_grey),
                )
                .padding(vertical = 16.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Дата",
                Modifier.weight(1f),
                fontFamily = mainFont,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Black
            )
            Text(
                "Валюта/Сумма",
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth(),
                fontFamily = mainFont,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(transactions) { transaction ->
                TransactionItem(transaction)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: TransactionDbo) {
    val date = transaction.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yy"))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = colorResource(R.color.primary_grey),
            )
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            date,
            fontFamily = mainFont,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(Modifier.width(16.dp))

        Row(
            modifier = Modifier.weight(3f), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = transaction.from.getFlagResId()),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(8.dp))

            Text(
                text = " ${transaction.from} ${String.format(Locale.US, "-%.2f", transaction.fromAmount)}"  ,
                fontFamily = mainFont,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = Color.Black
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                painter = painterResource(R.drawable.ic_swipe),
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                tint = Color.Black
            )
            Spacer(Modifier.width(8.dp))
            Image(
                painter = painterResource(id = transaction.to.getFlagResId()),
                contentDescription = null,
                modifier = Modifier.size(15.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = " ${transaction.to} ${String.format(Locale.US,"+%.2f", transaction.toAmount)}",
                fontFamily = mainFont,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}