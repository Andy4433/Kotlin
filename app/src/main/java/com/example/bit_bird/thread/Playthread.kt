package com.example.bit_bird.thread

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.wifi.aware.AwareResources
import android.util.Log
import android.view.SurfaceHolder
import com.example.bit_bird.Model.BackgroundImage
import kotlin.concurrent.thread

class Playthread : Thread {

    private val TAG : String = "PlayThread"
    private var holder: SurfaceHolder
    private var resources: Resources
    var isRunning : Boolean = false
        get() = field
        set(value) {
            field = value
        }
    private val FPS : Int = (1000.0/60.0).toInt() // FPS do do jogo 60 fps
    private val backgroundImage = BackgroundImage() // objeto da model
    private var startTime: Long = 0
    private var frameTime: Long = 0
    private val velocity = 3

    constructor(holder: SurfaceHolder, resources: Resources){
        this.holder = holder
        this.resources = resources
        isRunning = true

    }

    override fun run() {
        Log.d(TAG, "Thread Started")
         while (isRunning){
              if (holder== null)return
             startTime = System.nanoTime()
             val canvas = holder.lockCanvas()
             if (canvas != null ){
                 try {
                     synchronized(holder){
                         render(canvas)
                     }
                 }finally {
                     holder.unlockCanvasAndPost(canvas)
                 }
             }
             frameTime = (System.nanoTime() - startTime)/1000000
             if (frameTime< FPS){
                 try {
                     Thread.sleep(FPS - frameTime)
                 }catch (e:InterruptedException){
                     Log.e("Interrupted", "Thread sleep error")
                 }
             }
         }
        Log.d(TAG, "Thread finish")
    }
    // renderizar background
    private fun render(canvas: Canvas?) {
        Log.d(TAG, "Render canvas")
        val bitmapImage : Bitmap = BitmapFactory.decodeResorce(resources, R.drawable.run_background)
        backgroundImage.x = backgroundImage.x - velocity
        if (backgroundImage.x < -bitmapImage.width){
            backgroundImage.x = 0
        }
        canvas!!.drawBitmap(bitmapImage,(backgroundImage.x).toFloat(),(backgroundImage.y).toFloat(), null)
    }
}