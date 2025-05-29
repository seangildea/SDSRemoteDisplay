package com.W1SPG.sdsremotedisplay

import android.content.Context
import android.net.Uri
import android.util.Log
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer

class VlcPlayer(
    private val context: Context,
    private val rtspUrl: String
) {
    private var libVlc: LibVLC? = null
    private var mediaPlayer: MediaPlayer? = null
    var isPlaying: Boolean = false
        private set
    var initializationError: String? = null
        private set

    init {
        try {
            requireNotNull(context) { "Context cannot be null" }
            require(rtspUrl.isNotBlank()) { "RTSP URL cannot be empty" }

            // Log device details
            Log.d("VlcPlayer", "Device ABI: ${android.os.Build.SUPPORTED_ABIS.joinToString()}")
            Log.d("VlcPlayer", "Android Version: ${android.os.Build.VERSION.RELEASE}")
            Log.d("VlcPlayer", "Context: ${context.javaClass.name}, RTSP URL: $rtspUrl")

            val options: MutableList<String> = mutableListOf(
                "--network-caching=200",
                "--audio-desync=-100",
                "--live-caching=150",
                "--audio-resampler=soxr")

            Log.d("VlcPlayer", "Initializing LibVLC with options: $options")
            libVlc = LibVLC(context, options)
            mediaPlayer = MediaPlayer(libVlc)
            val uri = Uri.parse(rtspUrl)
            val media = Media(libVlc, uri)
            mediaPlayer?.media = media

            mediaPlayer?.setEventListener { event ->
                when (event.type) {
                    MediaPlayer.Event.Playing -> {
                        isPlaying = true
                        Log.d("VlcPlayer", "Playing RTSP stream: $rtspUrl")
                    }
                    MediaPlayer.Event.Stopped -> {
                        isPlaying = false
                        Log.d("VlcPlayer", "Stopped")
                    }
                    MediaPlayer.Event.EncounteredError -> {
                        isPlaying = false
                        Log.e("VlcPlayer", "Playback error for $rtspUrl")
                    }
                    MediaPlayer.Event.Buffering -> Log.d("VlcPlayer", "Buffering: ${event.buffering}%")
                }
            }
        } catch (e: UnsatisfiedLinkError) {
            initializationError = "UnsatisfiedLinkError: ${e.message}"
            Log.e("VlcPlayer", "Native library loading failed: $initializationError", e)
        } catch (e: UnsupportedOperationException) {
            initializationError = "UnsupportedOperationException: ${e.message}"
            Log.e("VlcPlayer", "LibVLC initialization failed: $initializationError", e)
        } catch (e: Exception) {
            initializationError = "Exception: ${e.message}"
            Log.e("VlcPlayer", "LibVLC initialization failed: $initializationError", e)
        }
    }

    fun play() {
        if (!isPlaying && mediaPlayer != null) {
            try {
                mediaPlayer?.play()
            } catch (e: Exception) {
                Log.e("VlcPlayer", "Failed to play: ${e.message}", e)
            }
        } else {
            Log.w("VlcPlayer", "Cannot play: mediaPlayer is null or already playing")
        }
    }

    fun stop() {
        if (isPlaying && mediaPlayer != null) {
            try {
                mediaPlayer?.stop()
            } catch (e: Exception) {
                Log.e("VlcPlayer", "Failed to stop: ${e.message}", e)
            }
        }
    }

    fun release() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            libVlc?.release()
        } catch (e: Exception) {
            Log.e("VlcPlayer", "Failed to release resources: ${e.message}", e)
        } finally {
            mediaPlayer = null
            libVlc = null
            isPlaying = false
        }
    }
}