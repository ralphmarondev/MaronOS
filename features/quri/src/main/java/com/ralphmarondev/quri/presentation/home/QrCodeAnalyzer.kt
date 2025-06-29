package com.ralphmarondev.quri.presentation.home

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer

class QrCodeAnalyzer(
    private val onQrCodeScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val reader = MultiFormatReader().apply {
        setHints(
            mapOf(
                DecodeHintType.POSSIBLE_FORMATS to listOf(BarcodeFormat.QR_CODE)
            )
        )
    }

    override fun analyze(image: ImageProxy) {
        try {
            val buffer = image.planes[0].buffer
            val bytes = ByteArray(buffer.remaining()).also { buffer.get(it) }

            val width = image.width
            val height = image.height

            val source = PlanarYUVLuminanceSource(
                bytes, width, height,
                0, 0, width, height, false
            )
            val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
            val result = reader.decodeWithState(binaryBitmap)

            Log.d("Quri", "QR Code detected: ${result.text}")
            onQrCodeScanned(result.text)
        } catch (e: Exception) {
            // Only log if needed; avoid spamming
            // Log.d("Quri", "No QR code found in this frame.")
        } finally {
            image.close()
        }
    }
}
