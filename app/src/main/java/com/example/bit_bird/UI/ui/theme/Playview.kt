package com.example.bit_bird.UI.ui.theme

import android.content.Context
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.bit_bird.thread.Playthread

class Playview(context: Context?) : SurfaceView(context), SurfaceHolder.Callback {

    private val TAG = "PlayView"
    private var playthread : Playthread? = null


    init{
        val holder = holder
        holder.addCallback(this)
        isFocusable = true
        playthread = Playthread(holder, resources)
    }
    override fun surfaceCreated(p0: SurfaceHolder) {
        if(!playthread!!.isRunning){
            playthread= p0.let{
                Playthread(it!!, resources)
            }
            }else{
                playthread!!.start()
            }
        }
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        if (playthread!!.isRunning){
            playthread.isRunning = false
            var isCheck: Boolean = true
            while (isCheck){
                try {
                    playthread!!.join()
                }catch (e: InterruptedException){

                }
            }
        }
    }
}