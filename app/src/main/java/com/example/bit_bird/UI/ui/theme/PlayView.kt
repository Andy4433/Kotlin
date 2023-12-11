package com.example.bit_bird.UI.ui.theme

import android.content.Context
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.bit_bird.thread.PlayThread

class PlayView(context: Context?) : SurfaceView(context), SurfaceHolder.Callback {

    private val TAG = "PlayView"
    private var playThread: PlayThread? = null

    init {
        val holder = holder
        holder.addCallback(this)
        isFocusable = true
        playThread = PlayThread(holder, resources)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (!playThread!!.isRunning) {
            playThread = PlayThread(holder, resources)
        } else {
            playThread!!.start()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Implemente conforme necessário
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        if (playThread != null && playThread!!.isRunning) {
            playThread!!.isRunning = false
            var isCheck = true
            while (isCheck) {
                try {
                    playThread!!.join()
                    isCheck = false
                } catch (e: InterruptedException) {
                    // Lidar com exceção, se necessário
                }
            }
        }
    }
}
