package com.wss.eat_space_iz.utils

import android.os.CountDownTimer
import java.util.*
import java.util.concurrent.TimeUnit

class TimerUtil :
    CountDownTimer {

    private var onTimerListener: OnTimerListener
    private var millisInFuture: Long = 0

    constructor(
        millisInFuture: Long,
        countDownInterval: Long,
        onTimerListener: OnTimerListener
    ) : super(millisInFuture, countDownInterval) {
        this.onTimerListener = onTimerListener
    }

    override fun onFinish() {
        onTimerListener.onTimerFinish()
    }

    override fun onTick(millisUntilFinished: Long) {
        var strTime : String = "00:00"
        if(millisInFuture>(60*60*1000)) {
            strTime = String.format(
                Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
            )
        }else{
            strTime = String.format(
                Locale.getDefault(), "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 60,
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
            )
        }
        onTimerListener.onGetTimerEvent(strTime)
    }


    interface OnTimerListener {
        fun onGetTimerEvent(time : String)
        fun onTimerFinish()
    }

}