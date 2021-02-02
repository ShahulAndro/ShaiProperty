package com.sha.iproperty.data.utils

import android.content.Context
import android.util.Log
import java.io.IOException
import java.io.InputStream

object Utils {
    fun jsonFromAssets(context: Context, fileName: String): String? {
        val jsonString: String
        jsonString = try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            Log.d(Utils.javaClass.name, Log.getStackTraceString(e))
            return null
        }
        return jsonString
    }
}