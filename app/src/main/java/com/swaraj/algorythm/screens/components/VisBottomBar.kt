package com.swaraj.algorythm.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swaraj.algorythm.R

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onPlayPauseClick: () -> Unit,
    onNextStepClick: () -> Unit,
    onBackStepClick: () -> Unit,
    onSpeedUpClick: () -> Unit,
    onSlowDownClick: () -> Unit,
    isPlaying: Boolean = false,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
) {

    BottomAppBar(
        //ContainerColor = backgroundColor,
        modifier = Modifier.height(145.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {

            IconButton(onClick = { onSlowDownClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(16.dp)
                )
            }

            IconButton(onClick = {
                onPlayPauseClick()
            }) {
                Icon(
                    painter = if (!isPlaying) painterResource(id = R.drawable.ic_play).also {
                    } else painterResource(
                        id = R.drawable.ic_pause
                    ),
                    contentDescription = null,
                    tint = Color.White,
                )
            }

            IconButton(onClick = { onSpeedUpClick() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(onClick = { onBackStepClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_leftarow),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(19.dp)
                )
            }

            Button(
                onClick = { onNextStepClick() },
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier.shadow(3.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.fillMaxHeight(0.5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Next Step",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.surface,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_rightarrow),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.size(19.dp)
                    )

                }
            }
        }
    }

}