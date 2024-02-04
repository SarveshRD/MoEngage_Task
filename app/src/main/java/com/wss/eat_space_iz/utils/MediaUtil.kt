package com.wss.eat_space_iz.utils

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import id.zelory.compressor.Compressor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.*

object MediaUtil {

}

fun Uri.getFileFromContentUri(context: Context, newFileName: String? = null): File {
    // Preparing Temp file name
    val fileExtension = getFileExtension(context, this)
    val fileName =
        (newFileName ?: "temp_file") + if (fileExtension != null) ".$fileExtension" else ""
    // Creating Temp file
    val tempFile = File(context.cacheDir, fileName)
    tempFile.createNewFile()
    // Initialize streams
    var oStream: FileOutputStream? = null
    var inputStream: InputStream? = null

    try {
        oStream = FileOutputStream(tempFile)
        inputStream = context.contentResolver.openInputStream(this)

        inputStream?.let { copy(inputStream, oStream) }
        oStream.flush()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        // Close streams
        inputStream?.close()
        oStream?.close()
    }

    return tempFile
}

private fun getFileExtension(context: Context, uri: Uri): String? {
    val file = File(uri.toString())
    return MimeTypeMap.getFileExtensionFromUrl(file.path)
}

@Throws(IOException::class)
private fun copy(source: InputStream, target: OutputStream) {
    val buf = ByteArray(8192)
    var length: Int
    while (source.read(buf).also { length = it } > 0) {
        target.write(buf, 0, length)
    }
}

suspend fun File.compress(context: Context): File? {
    var compressedImageFile: File? = null
    val job = CoroutineScope(Dispatchers.Main).async {
        val selFile: Deferred<File> = async { Compressor.compress(context, this@compress) }
        compressedImageFile = selFile.await()
    }
    job.await()
    return compressedImageFile
}