package net.ezra.ui.products

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import net.ezra.R
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_SHOP
import net.ezra.navigation.ROUTE_VIEW_PRODUCTS
import net.ezra.ui.dashboard.DashboardScreen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavController, productId: String) {

    var product by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(Unit) {
        fetchProduct(productId) { fetchedProduct ->
            product = fetchedProduct
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    // Display the product name if available
                    Text(
                        text = product?.name ?:"Details",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_VIEW_PRODUCTS)
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "backIcon",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xff0FB06A),
                    titleContentColor = Color.White,
                )
            )
        },
        content = {
       LazyColumn {
           item {
               Column(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(Color(0xFF156B46))
                       .padding(it),){
                   Column(
                       modifier = Modifier
                           .fillMaxSize()
                           .background(Color(0xff9AEDC9)),
                   ) {
                       product?.let {
                           Column(
                               modifier = Modifier.padding(16.dp),
                               horizontalAlignment = Alignment.CenterHorizontally,
                               verticalArrangement = Arrangement.Center
                           ) {
                               Image(
                                   painter = rememberAsyncImagePainter(it.imageUrl),
                                   contentDescription = null,
                                   modifier = Modifier
                                       .fillMaxWidth()
                                       .height(300.dp)
                               )
                               Text(text = it.name,
                                   fontSize = 30.sp,
                                   fontWeight = FontWeight.Bold,

                                   )
                               Spacer(modifier = Modifier.height(8.dp))

                               Text(text = "Price: ${it.price}",
                               )

                               Spacer(modifier = Modifier.height(8.dp))

                               Text(text = "Quantity: ${it.quantity}",)

                               Spacer(modifier = Modifier.height(8.dp))

                               Text(text = it.description,
                                   fontSize = 16.sp,
                                   fontWeight = FontWeight.SemiBold,
                               )
                               Spacer(modifier = Modifier.height(8.dp))


                           }
                       }
                   }
                   Spacer(modifier = Modifier.height(20.dp))

                   Button(onClick = {  navController.navigate(ROUTE_LOGIN) },
                       modifier = Modifier
                           .width(300.dp)
                           .align(Alignment.CenterHorizontally)
                           .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(10.dp)),
                       colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                       elevation = ButtonDefaults.elevatedButtonElevation(20.dp)

                   ) {
                       androidx.compose.material3.Text(text = "Purchase",
                           fontSize = 15.sp,
                           fontWeight = FontWeight.Bold,
                       )
                   }

                   Spacer(modifier = Modifier.height(30.dp))

                   Box(
                       modifier = Modifier.fillMaxWidth()
                   ){
                       Row {
                           Spacer(modifier = Modifier.width(5.dp))

                           androidx.compose.material3.Text(text = "Similar products",
                               fontSize = 20.sp,
                               color = Color.White,
                               fontWeight = FontWeight.Bold,
                           )

                           Spacer(modifier = Modifier.width(130.dp))

                           androidx.compose.material3.Text(
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

                                       androidx.compose.material3.Text(text = "Conduit",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Protective tubing for electrical wiring.",
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
                                       Image(painter = painterResource(id = R.drawable.d1),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Fittings",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Connectors, elbows, and adapters for plumbing systems.",
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
                                       Image(painter = painterResource(id = R.drawable.d3),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Water Heaters",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Tank or tank-less options for hot water supply.",
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
                                       Image(painter = painterResource(id = R.drawable.d4),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Shotcrete",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Sprayed concrete used for repairs and new construction.",
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
                                       Image(painter = painterResource(id = R.drawable.d5),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Steel Bracing",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Adds stiffness and strength to structures.",
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
                                       Image(painter = painterResource(id = R.drawable.d6),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Hoarding",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Barriers to shield construction activities from public view.",
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
                                       Image(painter = painterResource(id = R.drawable.d7),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Outdoor Lighting",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = " Illuminates pathways, gardens, and landscapes",
                                           fontSize = 6.sp,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )

                                   }

                               }
                           }

                       }
                   }

                   Spacer(modifier = Modifier.height(30.dp))

                   Box(
                       modifier = Modifier.fillMaxWidth()
                   ){
                       Row {
                           Spacer(modifier = Modifier.width(5.dp))

                           androidx.compose.material3.Text(text = "HOT SALE",
                               fontSize = 20.sp,
                               color = Color.White,
                               fontWeight = FontWeight.Bold,
                           )

                           Spacer(modifier = Modifier.width(160.dp))

                           androidx.compose.material3.Text(
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
                                       Image(painter = painterResource(id = R.drawable.d11),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Conduit",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Protective tubing for electrical wiring.",
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
                                       Image(painter = painterResource(id = R.drawable.d13),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Fittings",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Connectors, elbows, and adapters for plumbing systems.",
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
                                       Image(painter = painterResource(id = R.drawable.d14),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Water Heaters",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Tank or tank-less options for hot water supply.",
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
                                       Image(painter = painterResource(id = R.drawable.d9),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Shotcrete",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Sprayed concrete used for repairs and new construction.",
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
                                       Image(painter = painterResource(id = R.drawable.d19),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Steel Bracing",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Adds stiffness and strength to structures.",
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
                                       Image(painter = painterResource(id = R.drawable.s2),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Hoarding",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Barriers to shield construction activities from public view.",
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
                                       Image(painter = painterResource(id = R.drawable.d16),
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                               .fillMaxWidth(),
                                           contentDescription = "null")

                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = "Outdoor Lighting",
                                           fontSize = 10.sp,
                                           fontWeight = FontWeight.Bold,
                                           textDecoration = TextDecoration.Underline,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )
                                       Spacer(modifier = Modifier.height(10.dp))

                                       androidx.compose.material3.Text(text = " Illuminates pathways, gardens, and landscapes",
                                           fontSize = 6.sp,
                                           modifier = Modifier
                                               .align(Alignment.CenterHorizontally)
                                       )

                                   }

                               }
                           }

                       }
                   }
               }
           }
       }
        }
    )
}

