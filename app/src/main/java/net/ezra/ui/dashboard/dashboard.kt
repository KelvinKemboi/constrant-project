package net.ezra.ui.dashboard

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DrawerValue
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FloatingActionButton
//noinspection UsingMaterialAndMaterial3Libraries,UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.play.integrity.internal.w
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.ezra.R
import net.ezra.navigation.ROUTE_SHOP
import net.ezra.navigation.ROUTE_VIEW_PRODUCTS

data class NavItemState(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasBadge: Boolean ,
    val badgeNumber: Int,

)
data class DrawerItems(
val icon: ImageVector,
val text: String,
val hasBadge: Boolean,
val badgeNumber: Int,

)


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(navController: NavHostController){
val item = listOf(
    NavItemState(
        title = "Cart",
        selectedIcon = Icons.Filled.ShoppingCart,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        hasBadge = true,
        badgeNumber = 10
    ),
    NavItemState(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasBadge = false,
        badgeNumber = 0
    ),
    NavItemState(
        title = "Email",
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email,
        hasBadge = true,
        badgeNumber = 0
    ),

)

val drawerItem = listOf(
    DrawerItems(
        icon = Icons.Filled.Face,
        text = "Profile",
        hasBadge = false,
        badgeNumber = 0
    ),
    DrawerItems(
        icon = Icons.Filled.Settings,
        text = "Settings",
        hasBadge = false,
        badgeNumber = 0
    ),
    DrawerItems(
        icon = Icons.Filled.Favorite,
        text = "Favourites",
        hasBadge = true,
        badgeNumber = 10
    ),
    DrawerItems(
        icon = Icons.Filled.Share,
        text = "Share",
        hasBadge = false,
        badgeNumber = 0
    ),
    DrawerItems(
        icon = Icons.AutoMirrored.Filled.ExitToApp,
        text = "Logout",
        hasBadge = false,
        badgeNumber = 0
    ),

)


    var bottomNavState by rememberSaveable {
        mutableStateOf(0) }

var selectedItem by remember {
    mutableStateOf(drawerItem[0])
}

var search by remember { mutableStateOf("") }
val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
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


 ModalNavigationDrawer(drawerContent = {
ModalDrawerSheet {
    Column(
modifier = Modifier
    .fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
     Box (
modifier = Modifier
    .fillMaxWidth()
    .height(200.dp)
    .background(Color(0xFF156B46))

     ){
    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
Image(
    painter = painterResource(id = R.drawable.clogo),
    contentDescription = "null",
modifier = Modifier
    .size(100.dp)
    .clip(CircleShape)
    )
Text(text = "Mr.Kelvin",
   modifier = Modifier
       .fillMaxWidth()
       .padding(top = 10.dp),
    textAlign = TextAlign.Center,
    )
    }
Divider(
    modifier = Modifier
        .align(Alignment.BottomCenter),
    color = Color.Gray,
    thickness = 1.dp
)

drawerItem.forEach {
    NavigationDrawerItem(label = { Text(text = it.text) },
        selected = it == selectedItem,
        onClick = {
            selectedItem = it
                  scope.launch {
                      drawerState.close()
                  }
                  },
        modifier = Modifier
            .padding(horizontal = 16.dp),
        icon = {
            Icon(imageVector = it.icon, contentDescription = it.text)
        },
        badge = {
            if (it.hasBadge) Badge {
                Text(text = it.badgeNumber.toString(), fontSize = 16.sp)
            }
        })
}

     }
    }
}

 }) {
Scaffold(
    topBar = {
        TopAppBar(title = {Text("DASHBOARD",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,)},
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) { Icon(imageVector = Icons.Filled.Menu, contentDescription = "null")}

            }

        )

    }
) {paddingValues ->
  Box(modifier = Modifier
      .padding(paddingValues)
  )

}

 }

    Scaffold(
        topBar = {
            TopAppBar(title = {Text("DASHBOARD",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,)},
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) { Icon(imageVector = Icons.Filled.Menu, contentDescription = "null")}

                }

            )

        },
bottomBar = {
       NavigationBar {
           item.forEachIndexed { index, item ->

               NavigationBarItem(
                   selected = bottomNavState == index,
                   onClick = { bottomNavState = index },
                   icon = {
                       BadgedBox(badge = {
                           if (item.hasBadge)Badge {}
                           if (item.badgeNumber != 0)Badge {
                               Text(text = item.badgeNumber.toString())
                           }


                           }){
                        Icon(imageVector = if (bottomNavState == index) item.selectedIcon
                                           else item.unselectedIcon,
                            contentDescription = item.title)
               }
                   }
               )

           }


       }



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


 ) {
     contentpadding ->


     LazyColumn(
         modifier = Modifier
             .fillMaxSize()
             .background(Color(0xFF156B46))
             .padding(contentpadding)){
         item {
             Column {

                 Spacer(modifier = Modifier.height(15.dp))
                 TextField(
                     value = search,
                     onValueChange = { search = it },
                     label = {
                       Text(text = "Search",
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
                Spacer(modifier = Modifier.height(10.dp))
                 Divider(thickness = 1.dp, color = Color.Gray)
                 Spacer(modifier = Modifier.height(10.dp))

Row{
    Spacer(modifier = Modifier.width(10.dp))
    Text(text = "User",
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
    )
}
     Spacer(modifier = Modifier.height(10.dp))
  Row{
      Spacer(modifier = Modifier.width(40.dp))

Image(painter = painterResource(id = R.drawable.clogo),
    modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .clip(CircleShape),
    contentDescription = "null")

Spacer(modifier = Modifier.width(120.dp))
      Column {
          Text(text = "Ordered",
              fontSize = 15.sp,
              modifier = Modifier
                  .clickable {  }
          )

          Text(text = "Marked",
              fontSize = 15.sp,
              modifier = Modifier
                  .clickable {  }
          )

          Text(text = "Prepaid",
              fontSize = 15.sp,
              modifier = Modifier
                  .clickable {  }
          )

          Text(text = "Delivered",
              fontSize = 15.sp,
              modifier = Modifier
                  .clickable {  }
          )
      }
  }
                 Spacer(modifier = Modifier.height(10.dp))
                 Divider(thickness = 1.dp, color = Color.Gray)
                 Spacer(modifier = Modifier.height(10.dp))


                 Box(modifier = Modifier
                     .fillMaxWidth()

                 ) {

                         Text(
                             text = "Recently viewed",
                             fontSize = 20.sp,
                             color = Color.White,
                             fontWeight = FontWeight.Bold,
                             modifier = Modifier
                                 .padding(start = 10.dp)
                             )
                 }
                 Spacer(modifier = Modifier.height(10.dp))
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

                                     Text(text = "Gravel",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Used for drainage layers and foundation base",
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

                                     Text(text = "Fly Ash",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Used to improve concrete durability",
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

                                     Text(text = "Slag cement",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Enhances Concrete strength and reduces permeability",
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

                                     Text(text = "Copper",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "For plumbing pipes and electrical wiring",
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

                                     Text(text = "Plywood",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Versatile sheet material for sheathing and flooring",
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

                                     Text(text = "Lumber",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Used in framing, formwork and finishing",
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

                                     Text(text = "Clay tiles",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Traditional and aesthetically pleasing roofing material",
                                         fontSize = 6.sp,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )

                                 }

                             }
                         }

                     }
                 }


                 Spacer(modifier = Modifier.height(20.dp))


                 Card(
                     modifier = Modifier
                         .width(400.dp)
                         .padding(25.dp),



                     ){
                     Row {
                         Spacer(modifier = Modifier.width(20.dp))

                         Column {

                             Spacer(modifier = Modifier.height(15.dp))

                             Image(
                                 painter = painterResource(id =R.drawable.clogo),
                                 contentDescription = "null",
                                 modifier = Modifier
                                     .width(100.dp)
                                     .height(100.dp)
                                     .clip(shape = RoundedCornerShape(200.dp))

                             )

                         }

                         Spacer(modifier = Modifier.width(40.dp))


                         Column {

                             Spacer(modifier = Modifier.height(20.dp))
                             Text(
                                 text = "Browse through at: ",
                                 fontWeight = FontWeight.Bold,
                                 fontSize = 15.sp

                             )
                             Spacer(modifier = Modifier.height(10.dp))

                             Button(
                                 onClick = { navController.navigate(ROUTE_SHOP) },
                                 modifier = Modifier
                                     .padding(16.dp)
                                     .width(200.dp)
                             ) {
                                 Text(text = "Shop")

                             }
                         }

                     }

                 }
                 Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                 Row {
                     Spacer(modifier = Modifier.width(5.dp))

                     Text(text = "Popular",
                         fontSize = 20.sp,
                         color = Color.White,
                         fontWeight = FontWeight.Bold,
                     )

                     Spacer(modifier = Modifier.width(180.dp))

                    Text(
                        text = "See all",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(ROUTE_SHOP)
                            }
                        )
                 }
                }

                 Spacer(modifier = Modifier.height(10.dp))
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

                                     Text(text = "Waterproof Coatings",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = " Applied to roofs, basements, and concrete surfaces.",
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

                                     Text(text = "Flooring Materials",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = " Includes tiles, hardwood, laminate, and vinyl.",
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

                                     Text(text = "Light Fixtures",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Includes ceiling lights, wall sconces, and outdoor fixtures.",
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

                                     Text(text = "Mortar",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Binding agent for masonry units",
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

                                     Text(text = "Dry wall",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Interior wall material for finishing",
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

                                     Text(text = "Paint",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Protective and decorative coating for walls and surfaces",
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

                                     Text(text = "Waterstops",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Used in concrete structures to prevent water ingress",
                                         fontSize = 6.sp,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )

                                 }

                             }
                         }

                     }
                 }


                 Spacer(modifier = Modifier.height(20.dp))

                 Box(
                     modifier = Modifier.fillMaxWidth()
                 ){
                     Row {
                         Spacer(modifier = Modifier.width(5.dp))

                         Text(text = "Recently Added",
                             fontSize = 20.sp,
                             color = Color.White,
                             fontWeight = FontWeight.Bold,
                         )

                         Spacer(modifier = Modifier.width(130.dp))

                         Text(
                             text = "See all",
                             fontSize = 15.sp,
                             fontWeight = FontWeight.Bold,
                             textDecoration = TextDecoration.Underline,
                             modifier = Modifier
                                 .clickable {
                                     navController.navigate(ROUTE_SHOP)
                                 }
                         )
                     }
                 }

                 Spacer(modifier = Modifier.height(10.dp))

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


                 Spacer(modifier = Modifier.height(20.dp))

                 Box(
                     modifier = Modifier.fillMaxWidth()
                 ){
                     Row {
                         Spacer(modifier = Modifier.width(5.dp))

                         Text(text = "Offers",
                             fontSize = 20.sp,
                             color = Color.White,
                             fontWeight = FontWeight.Bold,
                         )

                         Spacer(modifier = Modifier.width(220.dp))

                         Text(
                             text = "See all",
                             fontSize = 15.sp,
                             fontWeight = FontWeight.Bold,
                             textDecoration = TextDecoration.Underline,
                             modifier = Modifier
                                 .clickable {
                                     navController.navigate(ROUTE_SHOP)
                                 }
                         )
                     }
                 }

                 Spacer(modifier = Modifier.height(10.dp))

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

                                     Text(text = "Geotextiles",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Improves soil stability and drainage.",
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

                                     Text(text = "IoT Sensors",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Collects data for building performance and occupant comfort.",
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

                                     Text(text = "Base Isolators",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Bearings to decouple building from ground movements.",
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

                                     Text(text = "Seismic Dampers",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Absorbs and dissipates seismic energy.",
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

                                     Text(text = "Intumescent Paint",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Coating that swells when exposed to heat to protect structural elements.",
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

                                     Text(text = "FRP",
                                         fontSize = 10.sp,
                                         fontWeight = FontWeight.Bold,
                                         textDecoration = TextDecoration.Underline,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))

                                     Text(text = "Lightweight alternative to steel reinforcement.",
                                         fontSize = 6.sp,
                                         modifier = Modifier
                                             .align(Alignment.CenterHorizontally)
                                     )

                                 }

                             }
                         }

                     }
                 }


                 Spacer(modifier = Modifier.height(20.dp))
                 Card(
                     modifier = Modifier
                         .width(400.dp)
                         .padding(25.dp),



                     ){
                     Row {
                         Spacer(modifier = Modifier.width(20.dp))

                         Column {

                             Spacer(modifier = Modifier.height(15.dp))

                             Image(
                                 painter = painterResource(id =R.drawable.clogo),
                                 contentDescription = "null",
                                 modifier = Modifier
                                     .width(100.dp)
                                     .height(100.dp)
                                     .clip(shape = RoundedCornerShape(200.dp))

                             )

                         }

                         Spacer(modifier = Modifier.width(40.dp))


                         Column {

                             Spacer(modifier = Modifier.height(20.dp))
                             Text(
                                 text = "Complete purchase: ",
                                 fontWeight = FontWeight.Bold,
                                 fontSize = 15.sp

                             )
                             Spacer(modifier = Modifier.height(10.dp))

                             Button(
                                 onClick = { navController.navigate(ROUTE_VIEW_PRODUCTS) },
                                 modifier = Modifier
                                     .padding(16.dp)
                                     .width(200.dp)
                             ) {
                                 Text(text = "Basket")

                             }
                         }

                     }

                 }

                 Spacer(modifier = Modifier.height(20.dp))
                 Card(
                     modifier = Modifier
                         .width(400.dp)
                         .padding(25.dp),



                     ){
                     Row {
                         Spacer(modifier = Modifier.width(20.dp))

                         Column {

                             Spacer(modifier = Modifier.height(15.dp))

                             Image(
                                 painter = painterResource(id =R.drawable.clogo),
                                 contentDescription = "null",
                                 modifier = Modifier
                                     .width(100.dp)
                                     .height(100.dp)
                                     .clip(shape = RoundedCornerShape(200.dp))

                             )

                         }

                         Spacer(modifier = Modifier.width(40.dp))


                         Column {

                             Spacer(modifier = Modifier.height(20.dp))
                             Text(
                                 text = "Delv deeper: ",
                                 fontWeight = FontWeight.Bold,
                                 fontSize = 15.sp

                             )
                             Spacer(modifier = Modifier.height(10.dp))

                             Button(
                                 onClick = { navController.navigate(ROUTE_VIEW_PRODUCTS) },
                                 modifier = Modifier
                                     .padding(16.dp)
                                     .width(200.dp)
                             ) {
                                 Text(text = "Details")

                             }
                         }

                     }

                 }

             }

         }

     }

 }





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

@Preview(showBackground = true)
@Composable
fun PreviewLight() {
   DashboardScreen(rememberNavController())
}



 