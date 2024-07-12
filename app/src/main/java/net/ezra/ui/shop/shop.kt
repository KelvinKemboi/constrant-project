package net.ezra.ui.shop

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_INSERT_PRODUCT
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_VIEW_PRODUCTS
import net.ezra.ui.dashboard.PageIndicator

data class Item(
    val title: String,
    @DrawableRes val image: Int,
    val description: String,

    )

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ShopScreen(navController: NavHostController){

        val items = listOf(
            Item(
                title = "Electronics",
                image = R.drawable.s1,
                description = "Details"
            ),
            Item(
                title = "Wood Products",
                image = R.drawable.s2,
                description = "Details"
            ),
            Item(
                title = "Metals",
                image = R.drawable.d13,
                description = "Details"
            ),
            Item(
                title = "Roofing Materials",
                image = R.drawable.d12,
                description = "Details"
            ),
            Item(
                title = "Doors and Window",
                image = R.drawable.d14,
                description = "Details"
            ),
            Item(
                title = "Bricks",
                image = R.drawable.d15,
                description = "Details"
            ),
            Item(
                title = "Flooring",
                image = R.drawable.d5,
                description = "Details"
            ),
            Item(
                title = "Paints",
                image = R.drawable.d6,
                description = "Details"
            ),
            Item(
                title = "Plumbing",
                image = R.drawable.d4,
                description = "Details"
            ),
            Item(
                title = "Insulation Material",
                image = R.drawable.d1,
                description = "Details"
            ),

        )



    var search by remember { mutableStateOf("") }
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
    var scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {Text("SHOP",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,)},
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(ROUTE_INSERT_PRODUCT)}
                    ) { Icon(imageVector = Icons.Filled.Menu, contentDescription = "null")}

                }

            )

        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .background(Color(0xFF156B46))
                    .clip(RoundedCornerShape(20.dp))
                    .padding(10.dp),
            ){
                Row(
                    modifier = Modifier
                        .background(Color(0xFFF8FFFC))
                        .clip(RoundedCornerShape(20.dp))
                        .padding(10.dp)
                        .fillMaxWidth(),
                ){
                    Spacer(modifier = Modifier.width(50.dp))
                    Icon(imageVector =Icons.Outlined.ShoppingCart , contentDescription ="null",
                        modifier = Modifier
                            .clickable { navController.navigate(ROUTE_VIEW_PRODUCTS) }
                    )

                    Spacer(modifier = Modifier.width(80.dp))

                    Icon(imageVector =Icons.Outlined.Home , contentDescription ="null",
                        modifier = Modifier
                            .clickable { navController.navigate(ROUTE_HOME) }
                    )

                    Spacer(modifier = Modifier.width(80.dp))

                    Icon(imageVector =Icons.Outlined.Email , contentDescription ="null",
                        modifier = Modifier
                            .clickable { navController.navigate(ROUTE_DASHBOARD) }
                    )
                }
            }



        },


        ) {
            contentpadding ->


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF156B46))
                .padding(contentpadding)){
            items(1) {
                Column {

                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        value = search,
                        onValueChange = { search = it },
                        label = {
                            Text(
                                text = "Search",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                            )
                        },
                        modifier = Modifier
                            .width(300.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(thickness = 1.dp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .width(400.dp)
                            .align(Alignment.CenterHorizontally)
                            .height(400.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "CATEGORIES",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(15.dp))
//                        Divider(thickness = 1.dp, color = Color.Gray)
//                        Spacer(modifier = Modifier.height(20.dp))

                        LazyVerticalGrid(columns = GridCells.Fixed(2,),
                            modifier = Modifier
                                .padding(10.dp),
                            contentPadding = PaddingValues(vertical = 16.dp,),
                            ) {

                       itemsIndexed(items){ index, item ->
                           GridItem(item = item)
                           Spacer(modifier = Modifier.height(20.dp))
                       }

                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(thickness = 1.dp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(text = "Recently added",
                            fontSize = 20.sp,
                            color=Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )

                        LazyRow() {
                            item {
                                Row {
                                    Card(
                                        modifier = Modifier
                                            .height(170.dp)
                                            .width(170.dp),
                                        elevation = CardDefaults.cardElevation(20.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Conduit",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Protective tubing for electrical wiring.",
                                                fontSize = 5.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Fittings",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Connectors, elbows, and adapters for plumbing systems.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Water Heaters",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Tank or tank-less options for hot water supply.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Shotcrete",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Sprayed concrete used for repairs and new construction.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Steel Bracing",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Adds stiffness and strength to structures.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Hoarding",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Barriers to shield construction activities from public view.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Outdoor Lighting",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = " Illuminates pathways, gardens, and landscapes",
                                                fontSize = 6.sp,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )

                                        }

                                    }
                                }

                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(text = "Hot Specials",
                            fontSize = 20.sp,
                            color=Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )

                        LazyRow() {
                            item {
                                Row {
                                    Card(
                                        modifier = Modifier
                                            .height(170.dp)
                                            .width(170.dp),
                                        elevation = CardDefaults.cardElevation(20.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Conduit",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Protective tubing for electrical wiring.",
                                                fontSize = 5.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Fittings",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Connectors, elbows, and adapters for plumbing systems.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Water Heaters",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Tank or tank-less options for hot water supply.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Shotcrete",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Sprayed concrete used for repairs and new construction.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Steel Bracing",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Adds stiffness and strength to structures.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Hoarding",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Barriers to shield construction activities from public view.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Outdoor Lighting",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = " Illuminates pathways, gardens, and landscapes",
                                                fontSize = 6.sp,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )

                                        }

                                    }
                                }

                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(text = "In season",
                            fontSize = 20.sp,
                            color=Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )

                        LazyRow() {
                            item {
                                Row {
                                    Card(
                                        modifier = Modifier
                                            .height(170.dp)
                                            .width(170.dp),
                                        elevation = CardDefaults.cardElevation(20.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Conduit",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Protective tubing for electrical wiring.",
                                                fontSize = 5.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Fittings",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Connectors, elbows, and adapters for plumbing systems.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Water Heaters",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Tank or tank-less options for hot water supply.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Shotcrete",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Sprayed concrete used for repairs and new construction.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Steel Bracing",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Adds stiffness and strength to structures.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Hoarding",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Barriers to shield construction activities from public view.",
                                                fontSize = 6.sp,
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
                                    ){
                                        Column {
                                            Image(painter = painterResource(id = R.drawable.s1),
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .fillMaxWidth(),
                                                contentDescription = "null")

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = "Outdoor Lighting",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(text = " Illuminates pathways, gardens, and landscapes",
                                                fontSize = 6.sp,
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            )

                                        }

                                    }
                                }

                            }
                        }

                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .fillMaxWidth()

                        ) {
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier
                                    .wrapContentSize()
                            ) { currentPage ->

                                Card(
                                    modifier = Modifier
                                        .wrapContentSize()

                                        .padding(26.dp),
                                    elevation = CardDefaults.cardElevation(20.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = images[currentPage]),
                                        contentDescription = "null",
                                        modifier = Modifier
                                            .width(300.dp)
                                            .height(300.dp)
                                    )

                                }


                            }

                            IconButton(
                                onClick = {
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

                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = "null",
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    tint = Color.LightGray
                                )

                            }

                            IconButton(
                                onClick = {
                                    val previousPage = pagerState.currentPage - 1
                                    if (previousPage >= 0) {
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

                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
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

            }

        }

    }

}


@Composable
fun GridItem(item: Item){
    Card(
        modifier = Modifier
            .height(200.dp)
            .padding(15.dp)
            .width(150.dp),
        elevation = CardDefaults.cardElevation(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = item.image),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                contentDescription = "null"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))

            androidx.compose.material3.Button(
                onClick = {  },
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                elevation = ButtonDefaults.elevatedButtonElevation(20.dp)

            ) {
                Text(
                    text = item.description,
                    fontSize = 10.sp,
                    color = Color.Black,

                    )
            }
            Spacer(modifier = Modifier.height(10.dp))

        }

    }
}

@Preview(showBackground = true)
    @Composable
    fun PreviewLight() {
      ShopScreen(rememberNavController())
    }

