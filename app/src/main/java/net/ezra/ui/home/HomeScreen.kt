package net.ezra.ui.home

import android.graphics.fonts.FontStyle
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.ezra.R
import net.ezra.navigation.ROUTE_ABOUT
import net.ezra.navigation.ROUTE_CONTACT
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_INSERT_PRODUCT
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_REGISTER
import net.ezra.navigation.ROUTE_SHOP
import net.ezra.navigation.ROUTE_VIEW_PRODUCTS
import net.ezra.ui.dashboard.PageIndicator

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    var scope = rememberCoroutineScope()
    var images = listOf(
        R.drawable.p1,
        R.drawable.p2,
        R.drawable.p3,
        R.drawable.p4,
        R.drawable.p5,
        R.drawable.p6,
        R.drawable.p7,
        R.drawable.p8
    )
    val pagerState = rememberPagerState (pageCount = { images.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }
Scaffold(
    topBar = {
        TopAppBar(title = {Text("HOME",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,)},
            navigationIcon = {
                IconButton(onClick = {

                }) { Icon(imageVector = Icons.Filled.Menu, contentDescription = "null")

                }

            },

        )

    },


    floatingActionButton = {
        FloatingActionButton(onClick ={ navController.navigate(ROUTE_SHOP) }) {
            Image(imageVector = Icons.Filled.Add,
                contentDescription = "null",
                modifier = Modifier
                    .clickable { navController.navigate(ROUTE_SHOP)  }

            )
        }

    },
) {contentpadding ->

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF156B46))
            .padding(contentpadding),
    ){
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .wrapContentHeight()
                        .padding(top = 10.dp),



                    ){
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Construct with Constrant",
                        fontSize = 25.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(thickness = 1.dp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(30.dp))

                    Row{
                        Spacer(modifier = Modifier.width(20.dp))
                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .width(100.dp)
                                .height(100.dp),

                            elevation = CardDefaults.cardElevation(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)

                        ){
                            Text(text = "1.",
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 10.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Create profile",
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 7.dp)

                            )

                        }

                        Spacer(modifier = Modifier.width(15.dp))
                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .width(100.dp)
                                .height(100.dp),

                            elevation = CardDefaults.cardElevation(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)

                        ){
                            Text(text = "2.",
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 10.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Browse",
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 7.dp)

                            )

                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .width(100.dp)
                                .height(100.dp),

                            elevation = CardDefaults.cardElevation(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)

                        ){
                            Text(text = "3.",
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 10.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                                Text(text = "Shop",
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 7.dp)

                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Ready to serve you diligently by enabling you to:",
                        fontSize = 13.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )


                    Spacer(modifier = Modifier.height(40.dp))

                    LazyRow() {
                        item {
                            Row {
                                Spacer(modifier = Modifier.width(15.dp))
                                Card(
                                    modifier = Modifier
                                        .height(170.dp)
                                        .width(170.dp),
                                    elevation = CardDefaults.cardElevation(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column {
                                        Image(
                                            painter = painterResource(id = R.drawable.s1),
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .fillMaxWidth(),
                                            contentDescription = "null"
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Explore",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            textDecoration = TextDecoration.Underline,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Discover cheap electronic materials at shockingly low prices",
                                            fontSize = 10.sp,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )

                                    }

                                }

                                Spacer(modifier = Modifier.width(15.dp))
                                Card(
                                    modifier = Modifier
                                        .height(170.dp)
                                        .width(170.dp),
                                    elevation = CardDefaults.cardElevation(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column {
                                        Image(
                                            painter = painterResource(id = R.drawable.s1),
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .fillMaxWidth(),
                                            contentDescription = "null"
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Save",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            textDecoration = TextDecoration.Underline,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Discover cheap electronic materials at shockingly low prices",
                                            fontSize = 10.sp,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )

                                    }

                                }

                                Spacer(modifier = Modifier.width(15.dp))
                                Card(
                                    modifier = Modifier
                                        .height(170.dp)
                                        .width(170.dp),
                                    elevation = CardDefaults.cardElevation(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column {
                                        Image(
                                            painter = painterResource(id = R.drawable.s1),
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .fillMaxWidth(),
                                            contentDescription = "null"
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Revolutionize",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            textDecoration = TextDecoration.Underline,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Discover cheap electronic materials at shockingly low prices",
                                            fontSize = 10.sp,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )

                                    }

                                }

                                Spacer(modifier = Modifier.width(15.dp))
                                Card(
                                    modifier = Modifier
                                        .height(170.dp)
                                        .width(170.dp),
                                    elevation = CardDefaults.cardElevation(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column {
                                        Image(
                                            painter = painterResource(id = R.drawable.s1),
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .fillMaxWidth(),
                                            contentDescription = "null"
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Build",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            textDecoration = TextDecoration.Underline,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Discover cheap electronic materials at shockingly low prices",
                                            fontSize = 10.sp,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )

                                    }

                                }

                                Spacer(modifier = Modifier.width(15.dp))
                                Card(
                                    modifier = Modifier
                                        .height(170.dp)
                                        .width(170.dp),
                                    elevation = CardDefaults.cardElevation(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column {
                                        Image(
                                            painter = painterResource(id = R.drawable.s1),
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .fillMaxWidth(),
                                            contentDescription = "null"
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Strengthen",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            textDecoration = TextDecoration.Underline,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Discover cheap electronic materials at shockingly low prices",
                                            fontSize = 10.sp,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )

                                    }

                                }

                                Spacer(modifier = Modifier.width(15.dp))
                                Card(
                                    modifier = Modifier
                                        .height(170.dp)
                                        .width(170.dp),
                                    elevation = CardDefaults.cardElevation(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column {
                                        Image(
                                            painter = painterResource(id = R.drawable.s1),
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .fillMaxWidth(),
                                            contentDescription = "null"
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Effect",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            textDecoration = TextDecoration.Underline,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Discover cheap electronic materials at shockingly low prices",
                                            fontSize = 10.sp,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )

                                    }

                                }

                                Spacer(modifier = Modifier.width(15.dp))
                                Card(
                                    modifier = Modifier
                                        .height(170.dp)
                                        .width(170.dp),
                                    elevation = CardDefaults.cardElevation(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column {
                                        Image(
                                            painter = painterResource(id = R.drawable.s1),
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .fillMaxWidth(),
                                            contentDescription = "null"
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Easen",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            textDecoration = TextDecoration.Underline,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Discover cheap electronic materials at shockingly low prices",
                                            fontSize = 10.sp,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )

                                    }

                                }

                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Purchase cheap but durable",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Spacer(modifier = Modifier.width(35.dp))
                        Text(
                            text = "Metallic sheets",
                            fontSize = 15.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,

                            )
                        Spacer(modifier = Modifier.width(147.dp))
                        Image(painter = painterResource(id = R.drawable.items_arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_SHOP)
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Row {
                        Spacer(modifier = Modifier.width(35.dp))
                        Text(
                            text = "Building stones",
                            fontSize = 15.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,

                            )
                        Spacer(modifier = Modifier.width(147.dp))
                        Image(painter = painterResource(id = R.drawable.items_arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_SHOP)
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Row {
                        Spacer(modifier = Modifier.width(35.dp))
                        Text(
                            text = "Heavy machinary",
                            fontSize = 15.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,

                            )
                        Spacer(modifier = Modifier.width(135.dp))
                        Image(painter = painterResource(id = R.drawable.items_arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_SHOP)
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Row {
                        Spacer(modifier = Modifier.width(35.dp))
                        Text(
                            text = "Roofing materials",
                            fontSize = 15.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,

                            )
                        Spacer(modifier = Modifier.width(133.dp))
                        Image(painter = painterResource(id = R.drawable.items_arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_SHOP)
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Column (
                        modifier = Modifier
                            .width(400.dp)
                            .align(Alignment.CenterHorizontally)
                            .height(400.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ){
                        Box (
                            modifier = Modifier.wrapContentSize()
                        ){
                            HorizontalPager(state = pagerState,
                                modifier = Modifier
                                    .wrapContentSize()
                            ) {currentPage ->

                                Card(
                                    modifier = Modifier
                                        .wrapContentSize()

                                        .padding(26.dp),
                                    elevation = CardDefaults.cardElevation(20.dp)
                                ) {
                                    Image(painter = painterResource(id =images[currentPage] ), contentDescription = "null",
                                        modifier = Modifier
                                            .width(300.dp)
                                            .height(300.dp)
                                    )

                                }


                            }

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

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(text = "Explore from",
                        fontSize = 15.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                        )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(thickness = 1.dp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        Spacer(modifier = Modifier.width(15.dp))
                        Card(
                            modifier = Modifier
                                .height(170.dp)
                                .width(170.dp),
                            elevation = CardDefaults.cardElevation(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.s1),
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .fillMaxWidth(),
                                    contentDescription = "null"
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Electronics",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Discover cheap electronic materials at shockingly low prices",
                                    fontSize = 10.sp,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )

                            }

                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Card(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp),
                            elevation = CardDefaults.cardElevation(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.s1),
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .fillMaxWidth(),
                                    contentDescription = "null"
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Electronics",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Discover cheap electronic materials at shockingly low prices",
                                    fontSize = 10.sp,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )

                            }

                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row {
                        Spacer(modifier = Modifier.width(15.dp))
                        Card(
                            modifier = Modifier
                                .height(170.dp)
                                .width(170.dp),
                            elevation = CardDefaults.cardElevation(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.s1),
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .fillMaxWidth(),
                                    contentDescription = "null"
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Electronics",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Discover cheap electronic materials at shockingly low prices",
                                    fontSize = 10.sp,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )

                            }

                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Card(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp),
                            elevation = CardDefaults.cardElevation(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.s1),
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .fillMaxWidth(),
                                    contentDescription = "null"
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Electronics",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Discover cheap electronic materials at shockingly low prices",
                                    fontSize = 10.sp,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )

                            }

                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(thickness = 1.dp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = "Gain full and exclusive access",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                        )
                    Spacer(modifier = Modifier.height(10.dp))


                    Button(onClick = {  navController.navigate(ROUTE_LOGIN) },
                        modifier = Modifier
                            .width(300.dp)
                            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(10.dp))
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        elevation = ButtonDefaults.elevatedButtonElevation(20.dp)

                        ) {
                        Text(text = "Proceed",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        }
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




@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    HomeScreen(rememberNavController())
}

