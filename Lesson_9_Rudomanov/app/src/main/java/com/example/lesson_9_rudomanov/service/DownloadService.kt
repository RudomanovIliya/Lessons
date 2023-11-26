package com.example.lesson_9_rudomanov.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.lesson_9_rudomanov.R
import com.example.lesson_9_rudomanov.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URL
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class DownloadService : Service() {
    private val scope = CoroutineScope(Dispatchers.IO)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        scope.launch {
            val totalSize = intent?.getStringExtra(KEY_URL)?.let {
                { progress:Long, length:Long ->
                    startForeground(10, createNotification(((progress / length) * 100).toInt()))
                }.download(
                    it,
                    filesDir.path + "/Image"
                )
            }
            val imageName = unzip(filesDir.path + "/Image", filesDir.path + "/")

            val intent = Intent(MainActivity.FILE_RECEIVER).apply {
                putExtra(KEY_FILE, imageName)
            }
            sendBroadcast(intent)
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    private fun unzip(filePath: String, fileUnzipPath: String): String? {
        var fileName: String? = null
        val zipInputStream = ZipInputStream(FileInputStream(filePath))
        var zipEntry: ZipEntry? = null
        while (zipInputStream.nextEntry.also { zipEntry = it } != null) {
            fileName = zipEntry!!.name
            val fileOutputStream = FileOutputStream(fileUnzipPath + zipEntry!!.name)
            var bits = zipInputStream.read()
            while (bits != -1) {
                fileOutputStream.write(bits)
                bits = zipInputStream.read()
            }
            zipInputStream.closeEntry()
            fileOutputStream.close()
        }
        zipInputStream.close()
        return fileName
    }

     private inline fun ((Long, Long) -> Unit)?.download(
         link: String,
         path: String
     ): Long {
        val url = URL(link)
        val connection = url.openConnection()
        connection.connect()
        val length =
            connection.contentLengthLong
        url.openStream().use { input ->
            FileOutputStream(File(path)).use { output ->
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                var bytesRead = input.read(buffer)
                var bytesCopied = 0L
                while (bytesRead >= 0) {
                    output.write(buffer, 0, bytesRead)
                    bytesCopied += bytesRead
                    this?.invoke(bytesCopied, length)
                    bytesRead = input.read(buffer)
                }
                return bytesCopied
            }
        }
    }

    private fun createNotification(progress: Int): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setColor(Color.BLUE)
            .setOnlyAlertOnce(true)
            .setContentTitle("Downloading")
            .setProgress(100, progress, false)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        const val KEY_FILE = "key_fail"
        const val KEY_URL = "key_url"

        fun createStartIntent(context: Context, url: String): Intent {
            return Intent(context, DownloadService::class.java).apply {
                putExtra(KEY_URL, url)
            }
        }
    }
}