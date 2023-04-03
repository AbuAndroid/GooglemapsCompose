package com.example.googlemapscompose.ui.mapscreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


/*Google Maps*/
@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen(
        addMarker = {},
        markers = mutableListOf(),
        selectedAddress = "",
        doRecompose = {},
        addTextToButton = {},
        testingText = ""
    )
}

@Composable
fun MapScreen(
    addMarker: (LatLng) -> Unit,
    markers: MutableList<LatLng>,
    selectedAddress: String,
    doRecompose: () -> Unit,
    addTextToButton: (String) -> Unit,
    testingText: String
) {
    Log.e("data1",selectedAddress)
    val context = LocalContext.current

    val cityCenter = LatLng(13.0827, 80.2707)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cityCenter, 14f)
    }

    val count = remember {
        mutableStateOf(0)
    }
    val checked = remember { mutableStateOf(true) }

    Log.e("data12",testingText)
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(80.dp),
                backgroundColor = Color.White,
                contentColor = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "arrow back",
                            modifier = Modifier
                                .background(Color(0xFF013220))
                        )
                    }

                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = testingText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        Log.e("current", selectedAddress)
                        Toast.makeText(context, count.value.toString(), Toast.LENGTH_SHORT).show()

                            Text(
                                text = count.value.toString(),
                               // text = " # 6, The Police Commissioner Office Rd, Police Commisioners Rd, Egmore, Chennai, Tamil Nadu 600008, India",
                                color = Color.Yellow,
                                fontSize = 10.sp
                            )
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.White
            ) {
                Button(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF013220),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Confirm",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) {
        Column() {
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.6f),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(
                    myLocationButtonEnabled = true,
                    mapToolbarEnabled = true
                ),
                properties = MapProperties(
                    isMyLocationEnabled = true,
                    isBuildingEnabled = true,
                    isIndoorEnabled = true
                ),
                onMapClick = { latLng: LatLng ->
                    addMarker(latLng)
                    doRecompose()
                },
                onMyLocationClick = {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }
            ) {
                markers.lastOrNull()?.let { MarkerState(it) }?.let {
                    Marker(
                        state = it,
                        title = "lat",
                        snippet = "location"
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(.4f)
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(.7f)
                    ) {
                        Text(
                            text = "Street name",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "home.work,rest...."
                        )
                    }
                    Column(
                        modifier = Modifier.weight(.3f)
                    ) {
                        Text(
                            text = "House/Office No",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Unit No"
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Spacer(
                        modifier = Modifier.weight(.7f)
                    )
                    Column(
                        modifier = Modifier.weight(.3f)
                    ) {
                        Text(
                            text = "Save the locatiion for later use",
                            fontWeight = FontWeight.Bold,
                        )

                        Button(
                            onClick = {
                                count.value++
                            }
                        ) {
                            Text(text = "Button")
                        }
                        Switch(
                            checked = checked.value,
                            onCheckedChange = {
                                checked.value = it
                            }
                        )
                    }
                }
            }
        }
    }
}
