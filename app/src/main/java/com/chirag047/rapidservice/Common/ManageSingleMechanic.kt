package com.chirag047.rapidservice.Common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chirag047.rapidservice.R

@Composable
fun ManageSingleMechanic(name: String, status: String, deleteMechanic: () -> Unit) {

    val showDropMenu = remember {
        mutableStateOf(false)
    }

    Column(
        Modifier
            .padding(15.dp, 7.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable {
                showDropMenu.value = true
            }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier
                    .padding(15.dp, 5.dp, 7.dp, 0.dp)
                    .clip(RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Spacer(modifier = Modifier.padding(4.dp))

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(MaterialTheme.colorScheme.secondary),
                ) {
                    Icon(

                        painterResource(id = R.drawable.plumber),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }

            Column(Modifier.weight(1f)) {

                poppinsBoldText(
                    contentText = name,
                    size = 14.sp,
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                )

                Text(
                    text = "• " + status,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 12.sp,
                    color = if (status.equals("Available")) MaterialTheme.colorScheme.primary else if (status.equals(
                            "Currently on service"
                        )
                    ) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                )
            }

            DropDownMenu(showDropMenu.value) {
                deleteMechanic.invoke()
            }

            Spacer(modifier = Modifier.padding(5.dp))

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp, 5.dp, 15.dp, 15.dp),
        ) {
            Row(
                modifier = Modifier
                    .border(
                        1.dp, MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(25.dp)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = "Send notification",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    modifier = Modifier
                        .padding(15.dp, 8.dp)
                        .weight(1f)
                )
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Row(
                modifier = Modifier
                    .border(
                        1.dp, MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(25.dp)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = "Call now",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    modifier = Modifier
                        .padding(15.dp, 8.dp)
                        .weight(1f)
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}
