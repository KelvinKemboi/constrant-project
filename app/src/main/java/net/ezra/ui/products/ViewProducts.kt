package net.ezra.ui.products

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import net.ezra.navigation.ROUTE_HOME

data class Product(
    var id: String = "",
    val name: String = "",
    val quantity: String = "",
    val description: String ="",
    val price: Double = 0.0,
    var imageUrl: String = ""
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavController, products: List<Product>) {
    var isLoading by remember { mutableStateOf(true) }
    var productList by remember { mutableStateOf(emptyList<Product>()) }
    var displayedProductCount by remember { mutableStateOf(6) }
    var progress by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        fetchProducts(
            onSuccess = { fetchedProducts ->
                productList = fetchedProducts
                isLoading = false
            },
            onError = {
                isLoading = false
                // Handle the error, e.g., show a toast or a message
            }
        )

        // Simulate progress for loading state
        while (isLoading) {
            delay(100)
            progress = (progress + 1) % 101
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Product List") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(ROUTE_HOME) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xff0FB06A),
                    titleContentColor = Color.White,
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff9AEDC9))
        ) {
            if (isLoading) {
                // Progress indicator
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(progress = progress / 100f)
                    Text(text = "Loading... $progress%", fontSize = 20.sp)
                }
            } else {
                if (productList.isEmpty()) {
                    // No products found
                    Text(text = "No products found", modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    // Products list
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(productList.take(displayedProductCount)) { product ->
                            ProductListItem(product) {
                                navController.navigate("productDetail/${product.id}")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    // Load More Button
                    if (displayedProductCount < productList.size) {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff0FB06A)),
                            onClick = { displayedProductCount += 1 },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Load More")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductListItem(product: Product, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clickable { onItemClick(product.id) },
        elevation = 4.dp,

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {




            // Product Image
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Product Details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(text = product.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                    )
                Text(text = product.quantity)
                Text(text = "Price: ${product.price}")
            }

        }
    }
}

@Suppress("DEPRECATION")
suspend fun fetchProducts(onSuccess: (List<Product>) -> Unit, onError: () -> Unit) {
    val firestore = Firebase.firestore
    try {
        val snapshot = firestore.collection("events").get().await()
        val productList = snapshot.documents.mapNotNull { doc ->
            val product = doc.toObject<Product>()
            product?.id = doc.id
            product
        }
        onSuccess(productList)
    } catch (e: Exception) {
        onError()
    }
}

suspend fun fetchProduct(productId: String, onSuccess: (Product?) -> Unit) {
    val firestore = Firebase.firestore
    val docRef = firestore.collection("events").document(productId)
    val snapshot = docRef.get().await()
    val product = snapshot.toObject<Product>()
    onSuccess(product)
}
