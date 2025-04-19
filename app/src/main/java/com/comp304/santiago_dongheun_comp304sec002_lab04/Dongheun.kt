package com.comp304.santiago_dongheun_comp304sec002_lab04

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.comp304.santiago_dongheun_comp304sec002_lab04.data.Place
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.compose.widgets.ScaleBar

@Composable
fun DongheunScreen(
    place: Place,
    onBackButtonPressed: () -> Unit
) {
    val locToronto = LatLng(place.latitude, place.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(locToronto, 15f)
    }
    val uiSettings by remember { mutableStateOf(MapUiSettings(
        zoomControlsEnabled = true,
        rotationGesturesEnabled = true,
        compassEnabled = false,
        myLocationButtonEnabled = true
    )) }
    val properties by remember { mutableStateOf(MapProperties(
        isMyLocationEnabled = true
    )) }

    RequestForegroundLocationPermission(
        onPermissionGranted = {},
        onPermissionDenied = {}
    )

    Scaffold(
        topBar =
        {
            TopBar(
                title = place.name,
                showBackButton = true,
                onBackButtonPressed = onBackButtonPressed
            )
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize()) {
            GoogleMap(
                uiSettings = uiSettings,
                properties = properties,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                cameraPositionState = cameraPositionState,
            ) {
                Marker(
                    state = rememberMarkerState(position = locToronto),
                    title = place.name
                )
            }
            ScaleBar(
                modifier = Modifier
                    .padding(top = 5.dp, end = 15.dp)
                    .align(Alignment.TopEnd),
                cameraPositionState = cameraPositionState
            )
        }
    }
}

@Composable
fun RequestForegroundLocationPermission(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val context = LocalContext.current

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }

    // Check if permission is already granted
    if (ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        onPermissionGranted()
    } else {
        // Request the permission
        LaunchedEffect(Unit) {
            permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}