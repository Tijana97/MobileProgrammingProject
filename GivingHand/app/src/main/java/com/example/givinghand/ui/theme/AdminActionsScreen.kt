package com.example.givinghand.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.givinghand.R
import com.example.givinghand.data.Action
import kotlinx.coroutines.flow.Flow

@Composable
fun AdminActionListItem(action: Action,
                        modifier: Modifier = Modifier,
                        onAddItemButtonClicked: () -> Unit
){
    Card(
        modifier = Modifier.padding(8.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth()){
            Spacer(modifier = Modifier.width(16.dp))
            Image(painter = painterResource(id = R.drawable.sos), contentDescription = null)
            Column(modifier = Modifier.fillMaxWidth()){
                Text(
                    text = action.name
                )
                Text(text = action.address)
                Text(text = action.date)
            }

        }

    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AdminActionList(actions: Flow<List<Action>>,
                    modifier: Modifier = Modifier,
                    onAddItemButtonClicked: () -> Unit
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    val actionListState by actions.collectAsState(initial = emptyList())

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = Modifier
    ) {
        Column() {
            androidx.compose.material3.Button(
                onClick = onAddItemButtonClicked,
                Modifier.widthIn(min = 250.dp).align(CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text("Add Action")
            }

            LazyColumn {

                itemsIndexed(actionListState) { index, action ->
                    AdminActionListItem(
                        action = action,
                        onAddItemButtonClicked = onAddItemButtonClicked,
                        modifier = modifier
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            .animateEnterExit(
                                enter = slideInVertically(
                                    animationSpec = spring(
                                        stiffness = Spring.StiffnessVeryLow,
                                        dampingRatio = Spring.DampingRatioLowBouncy
                                    ),
                                    initialOffsetY = { it * (index + 1) }
                                )
                            )
                    )
                }
            }
        }
    }
}
