package net.ezra.ui.contact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_REGISTER
import net.ezra.ui.contact.ui.theme.ConstrantTheme
import net.ezra.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Joinus(navController: NavHostController){



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF156B53))

    )

        Scaffold (
            topBar = {
                TopAppBar(title = {Text("ONBOARD WITH US",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,

                    )},

                    )

            },
            
        ){contentpadding ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF156B53))
                    .padding(contentpadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Spacer(modifier = Modifier.height(250.dp))

                Row {
                    Spacer(
                        modifier = Modifier
                            .width(20.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Spacer(modifier = Modifier.height(200.dp))
                        Text(
                            text = "GET A WHOLESOME EXPERIENCE",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,

                            )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = { navController.navigate(ROUTE_LOGIN)},
                            modifier = Modifier
                                .width(200.dp)
                                .height(50.dp)

                        ) {
                            Text(
                                text = "Login",
                                fontSize = 20.sp,
                                modifier = Modifier


                            )
                        }


                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Text(text = "Don't have an account?",)
                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)

                            )
                            Text(
                                text = "Register",
                                fontSize = 15.sp,
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(ROUTE_REGISTER)
                                    },

                                )
                        }
                    }
                }
            }
        }




    



}
@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    Joinus(rememberNavController())
}
