package net.ezra.ui


import android.content.res.Configuration
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.ezra.R
import net.ezra.navigation.ROUTE_HOME
import net.ezra.ui.dashboard.PageIndicator


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(navController: NavHostController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    var scope = rememberCoroutineScope()
    var images = listOf(
        R.drawable.sp1,
        R.drawable.sp2,
        R.drawable.sp3,

    )
    val pagerState = rememberPagerState (pageCount = { images.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }
//
//  val splash = listOf(
//      R.drawable.clogo,
//      R.drawable.s1,
//      R.drawable.s2,
//      R.drawable.s3,
//      )
//
////    val titles = listOf(
////       "Welcome to Constrant",
////        "Spend less",
////        "Build more",
////        "Build better",
////    )
////
////    val descriptions = listOf(
////       "Your cheap construction materials gate-away",
////        "No need to empty your pockets",
////        "Your one-stop shop for all your construction needs",
////        "Optimise quality and synchronize it with quantity"
////    )


    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }))
        // Customize the delay time
        delay(20000L)
        navController.navigate(ROUTE_HOME)
    }
Scaffold(
    topBar = {
        TopAppBar(title = {Text("CONSTRANT",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            )},
        )

    }

){contentpadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentpadding)
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box (
            modifier = Modifier.wrapContentSize()
                .fillMaxHeight()
                .align(Alignment.CenterHorizontally)
        ){
            HorizontalPager(state = pagerState,
                modifier = Modifier
                    .wrapContentSize()
            ) {currentPage ->

                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(26.dp),
                    elevation = CardDefaults.cardElevation(20.dp)
                ) {

                    Image(painter = painterResource(id =images[currentPage] ), contentDescription = "null",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .align(Alignment.CenterHorizontally)

                    )

                }


            }

            Spacer(modifier = Modifier.height(10.dp))

            IconButton(onClick = {
                val nextPage = pagerState.currentPage + 1
                if (nextPage < images.size) {
                    scope.launch {
                        pagerState.animateScrollToPage(nextPage)
                    }
                }
            },
                modifier = Modifier
                    .padding(40.dp)
                    .size(40.dp)
                    .align(Alignment.CenterEnd)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.DarkGray
                )

            ) {

                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "null",
                    modifier = Modifier
                        .fillMaxSize(),
                    tint = Color.LightGray
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            IconButton(onClick = {
                val previousPage = pagerState.currentPage - 1
                if (previousPage >=0 ) {
                    scope.launch {
                        pagerState.animateScrollToPage(previousPage)
                    }
                }
            },
                modifier = Modifier
                    .padding(30.dp)
                    .size(40.dp)
                    .align(Alignment.CenterStart)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.DarkGray
                )
            ) {

                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "null",
                    modifier = Modifier
                        .fillMaxSize(),
                    tint = Color.LightGray
                )

            }

        }

        PageIndicator(
            pageCount = images.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
        )

    }
}
    // Image


}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(10.dp)
    ){
        repeat(pageCount) {
            IndicatorDots(isSelected = it == currentPage
            )
        }

    }


}

@Composable
fun IndicatorDots(isSelected: Boolean) {
    val size = animateDpAsState(targetValue = if (isSelected) 12.dp else 8.dp, label = "l")
    Box(
        modifier = Modifier
            .padding(2.dp)
            .size(size.value)
            .clip(CircleShape)
            .background(if (isSelected) Color.White else Color.LightGray)
    ) {

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    SplashScreen(rememberNavController())
}

