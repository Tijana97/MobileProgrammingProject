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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.givinghand.LaunchGivingHandScreen
import com.example.givinghand.R
import com.example.givinghand.data.Action
import kotlinx.coroutines.flow.Flow

@Composable
fun ActionListItem(action: Action, modifier: Modifier = Modifier){
    Card(
        modifier = Modifier.padding(8.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth()){
            Image(painter = painterResource(id = R.drawable.sos), contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
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
fun ActionList(actions: Flow<List<Action>>,
               modifier: Modifier = Modifier,
               onShowActonClicked: (action: Action) -> Unit
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
        LazyColumn {
            itemsIndexed(actionListState) { index, action ->
                ActionListItem(
                    action = action,
                    modifier = modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .clickable(onClick = { onShowActonClicked(action) })
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
