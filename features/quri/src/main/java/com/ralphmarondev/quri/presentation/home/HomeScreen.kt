package com.ralphmarondev.quri.presentation.home

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var code by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasCameraPermission = granted }
    )

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.CAMERA)
    }
    LaunchedEffect(code) {
        if (code.isNotBlank()) {
            snackbarHostState.showSnackbar("QR Code: $code")
        }
        code = ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Quri") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedVisibility(visible = hasCameraPermission) {
                Box(modifier = Modifier.weight(1f)) {
                    AndroidView(
                        factory = { context ->
                            val previewView = PreviewView(context)
                            val preview = Preview.Builder().build()
                            val selector = CameraSelector.DEFAULT_BACK_CAMERA
                            preview.surfaceProvider = previewView.surfaceProvider

                            @Suppress("DEPRECATION")
                            val imageAnalysis = ImageAnalysis.Builder()
                                .setTargetResolution(Size(1280, 720))
                                .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                                .build()

                            imageAnalysis.setAnalyzer(
                                ContextCompat.getMainExecutor(context),
                                QrCodeAnalyzer { result -> code = result }
                            )

                            try {
                                cameraProviderFuture.get().bindToLifecycle(
                                    lifeCycleOwner,
                                    selector,
                                    preview,
                                    imageAnalysis
                                )
                            } catch (e: Exception) {
                                Log.e("Quri", "Error binding camera: ${e.message}")
                            }

                            previewView
                        },
                        modifier = Modifier.fillMaxSize()
                    )

                    // Overlay: Scan Box in Center
                    ScanBoxOverlay(size = 250.dp, stroke = 4.dp)
                }
            }
        }
    }
}

@Composable
fun ScanBoxOverlay(size: Dp, stroke: Dp) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        val borderWidthPx = with(LocalDensity.current) { stroke.toPx() }

        Canvas(
            modifier = Modifier
                .size(size)
        ) {
            val cornerLength = 30.dp.toPx()
            val w = size.toPx()
            val h = size.toPx()

            // Top-left corner
            drawLine(
                color = Color.Magenta,
                start = androidx.compose.ui.geometry.Offset(0f, 0f),
                end = androidx.compose.ui.geometry.Offset(cornerLength, 0f),
                strokeWidth = borderWidthPx
            )
            drawLine(
                color = Color.Magenta,
                start = androidx.compose.ui.geometry.Offset(0f, 0f),
                end = androidx.compose.ui.geometry.Offset(0f, cornerLength),
                strokeWidth = borderWidthPx
            )

            // Top-right
            drawLine(
                color = Color.Magenta,
                start = androidx.compose.ui.geometry.Offset(w, 0f),
                end = androidx.compose.ui.geometry.Offset(w - cornerLength, 0f),
                strokeWidth = borderWidthPx
            )
            drawLine(
                color = Color.Magenta,
                start = androidx.compose.ui.geometry.Offset(w, 0f),
                end = androidx.compose.ui.geometry.Offset(w, cornerLength),
                strokeWidth = borderWidthPx
            )

            // Bottom-left
            drawLine(
                color = Color.Magenta,
                start = androidx.compose.ui.geometry.Offset(0f, h),
                end = androidx.compose.ui.geometry.Offset(0f, h - cornerLength),
                strokeWidth = borderWidthPx
            )
            drawLine(
                color = Color.Magenta,
                start = androidx.compose.ui.geometry.Offset(0f, h),
                end = androidx.compose.ui.geometry.Offset(cornerLength, h),
                strokeWidth = borderWidthPx
            )

            // Bottom-right
            drawLine(
                color = Color.Magenta,
                start = androidx.compose.ui.geometry.Offset(w, h),
                end = androidx.compose.ui.geometry.Offset(w - cornerLength, h),
                strokeWidth = borderWidthPx
            )
            drawLine(
                color = Color.Magenta,
                start = androidx.compose.ui.geometry.Offset(w, h),
                end = androidx.compose.ui.geometry.Offset(w, h - cornerLength),
                strokeWidth = borderWidthPx
            )
        }
    }
}
