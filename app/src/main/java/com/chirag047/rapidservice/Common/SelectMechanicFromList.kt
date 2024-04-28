package com.chirag047.rapidservice.Common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.R

@Composable
fun SelectMechanicFromList(mechanicList: List<MechanicModel>): Int {

    var selectedIndex = remember { mutableStateOf(-1) }

    val scroll = rememberScrollState()

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scroll)
    ) {
        mechanicList.forEachIndexed { index, mechanic ->


            Row(
                Modifier
                    .padding(15.dp, 7.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable {
                        selectedIndex.value = index
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .padding(15.dp, 0.dp, 7.dp, 0.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(5.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(MaterialTheme.colorScheme.secondary),
                    ) {
                        if (mechanic.userImage.equals("")) {
                            Image(
                                painter = painterResource(id = R.drawable.profile_filled_icon),
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(Color.White),
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(8.dp)
                            )
                        } else {
                            Image(
                                painter = rememberImagePainter(mechanic.userImage),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(50.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                }

                Column(Modifier.weight(1f)) {

                    poppinsBoldText(
                        contentText = mechanic.userName,
                        size = 14.sp,
                        modifier = Modifier
                            .padding(10.dp, 0.dp)
                    )

                    poppinsText(
                        contentText = mechanic.mechanicId,
                        size = 12.sp,
                        modifier = Modifier
                            .padding(10.dp, 0.dp)
                    )
                }

                Icon(
                    painterResource(id = if (index.equals(selectedIndex.value)) R.drawable.check else R.drawable.oval),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )


                Spacer(modifier = Modifier.padding(5.dp))

            }
        }
    }

    return selectedIndex.value

}