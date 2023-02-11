package com.example.myapplication.helper

import android.content.Context
import android.os.Environment
import android.util.Base64
import com.example.myapplication.extension.toDateFileName
import java.io.*
import java.text.DecimalFormat
import java.util.*
import kotlin.math.log10
import kotlin.math.pow

object FileImageDirectory {

    @Throws(IOException::class)
    fun createImageFile(context: Context): File {
        val date = Calendar.getInstance().time.toDateFileName()
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            date,
            ".jpg",
            storageDir
        )
    }

    fun getReadableFileSize(size: Long): String {
        if (size <= 0) {
            return "0"
        }
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (log10(size.toDouble()) / log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
    }

    fun convertFileToByteArray(f: File?): String? {
        var byteArray: ByteArray? = null
        try {
            val inputStream: InputStream = FileInputStream(f)
            val bos = ByteArrayOutputStream()
            val b = ByteArray(1024 * 11)
            var bytesRead: Int
            while (inputStream.read(b).also { bytesRead = it } != -1) {
                bos.write(b, 0, bytesRead)
            }
            byteArray = bos.toByteArray()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }
}
