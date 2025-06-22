package com.ralphmarondev.camera.presentation.home

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Cameraswitch
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val controller: LifecycleCameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        }
    }
    val lifeCycleOwner = LocalLifecycleOwner.current

    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA,
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasPermission = granted
    }

    LaunchedEffect(hasPermission) {
        if (hasPermission) {
            controller.bindToLifecycle(lifeCycleOwner)
        } else {
            permissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Camera"
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            controller.cameraSelector = if (
                                controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
                            ) {
                                CameraSelector.DEFAULT_FRONT_CAMERA
                            } else {
                                CameraSelector.DEFAULT_BACK_CAMERA
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Cameraswitch,
                            contentDescription = "Rotate camera"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            val navItems = listOf(
                NavItems(
                    imageVector = Icons.Outlined.PhotoLibrary,
                    label = "Photos",
                    onClick = {}
                ),
                NavItems(
                    imageVector = Icons.Outlined.Camera,
                    label = "Capture",
                    onClick = {
                        if (hasPermission) {
                            val outputDir = context.filesDir
                            val fileName = "IMG_${
                                SimpleDateFormat(
                                    "yyyyMMdd_HHmmss",
                                    Locale.US
                                ).format(System.currentTimeMillis())
                            }.jpg"
                            val photoFile = File(outputDir, fileName)

                            val outputOptions =
                                ImageCapture.OutputFileOptions.Builder(photoFile).build()
                            controller.takePicture(
                                outputOptions,
                                ContextCompat.getMainExecutor(context),
                                object : ImageCapture.OnImageSavedCallback {
                                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                        Toast.makeText(
                                            context,
                                            "Saved to ${photoFile.absolutePath}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    override fun onError(exception: ImageCaptureException) {
                                        Toast.makeText(
                                            context,
                                            "Capture failed: ${exception.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            )
                        }
                    }
                ),
                NavItems(
                    imageVector = Icons.Outlined.Videocam,
                    label = "Video",
                    onClick = {}
                )
            )

            NavigationBar {
                navItems.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = index == 1,
                        onClick = navItem.onClick,
                        icon = {
                            Icon(
                                imageVector = navItem.imageVector,
                                contentDescription = navItem.label
                            )
                        },
                        label = {
                            Text(
                                text = navItem.label
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        if (hasPermission) {
            AndroidView(
                factory = {
                    PreviewView(it).apply {
                        this.controller = controller
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Camera permission is required.",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

private data class NavItems(
    val imageVector: ImageVector,
    val label: String,
    val onClick: () -> Unit
)