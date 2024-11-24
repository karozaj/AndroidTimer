package com.example.myapplication

import android.os.CountDownTimer

class MyTimer
{
    companion object
    {
        private var countDownTimer: CountDownTimer? = null
        var isRunning:Boolean=false
        var totalTime:Long=0
        var currentTime:Long=0

        var onTickFun:((Long)->Unit)?=null
        var onFinishFun:(()->Unit)?=null

        fun start()
        {
            isRunning=true
            countDownTimer = object : CountDownTimer(currentTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    currentTime=millisUntilFinished
                    onTickFun?.invoke(millisUntilFinished)
                }

                override fun onFinish() {
                    isRunning=false
                    onFinishFun?.invoke()
                }
            }
            countDownTimer?.start()
        }

        fun pause()
        {
            isRunning=false
            countDownTimer?.cancel()
        }

        fun stop() {
            isRunning=false
            countDownTimer?.cancel()
            currentTime= totalTime
        }

        fun updateCurrentTime(time:Long)
        {
            if(currentTime+time>0 && currentTime+time<=3599000)
            {
                currentTime=currentTime+time
            }

            if(isRunning)
            {
                pause()
                start()
            }
        }
    }
}