package com.example.bit_bird.thread

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import android.view.SurfaceHolder
import com.example.bit_bird.Model.BackgroundImage

class PlayThread(private val holder: SurfaceHolder, private val resources: Resources) : Thread() {

    private val TAG: String = "PlayThread"
    @Volatile
    var isRunning: Boolean = false

    private val FPS: Int = (1000.0 / 60.0).toInt() // FPS do do jogo 60 fps
    private val backgroundImage = BackgroundImage() // objeto da model
    private var startTime: Long = 0
    private var frameTime: Long = 0
    private val velocity = 3

    override fun run() {
        Log.d(TAG, "Thread Started")
        while (isRunning) {
            if (holder == null) return
            startTime = System.nanoTime()
            val canvas = holder.lockCanvas()
            if (canvas != null) {
                try {
                    synchronized(holder) {
                        render(canvas)
                    }
                } finally {
                    holder.unlockCanvasAndPost(canvas)
                }
            }
            frameTime = (System.nanoTime() - startTime) / 1000000
            if (frameTime < FPS) {
                try {
                    sleep(FPS - frameTime)
                } catch (e: InterruptedException) {
                    Log.e("Interrupted", "Thread sleep error")
                }
            }
        }
        Log.d(TAG, "Thread finish")
    }

    // renderizar background
    private fun render(canvas: Canvas) {
        Log.d(TAG, "Render canvas")
        val bitmapImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.run_background)
        backgroundImage.x = backgroundImage.x - velocity
        if (backgroundImage.x < -bitmapImage.width) {
            backgroundImage.x = 0
        }
        canvas.drawBitmap(bitmapImage, backgroundImage.x.toFloat(), backgroundImage.y.toFloat(), null)
    }
}
