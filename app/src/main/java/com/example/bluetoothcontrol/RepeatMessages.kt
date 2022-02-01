package com.example.bluetoothcontrol

import android.util.Log

//Вариант 1
/*
class RepeatMessages(private val bTConnect: BTConnect,private val message: String): Thread() {
    var threadOn: Boolean = false

    override fun run() {
        threadOn = true
        Log.d("Mylog","RUNRepeatMessages!!!")
        while (threadOn){
            bTConnect.sendMassage(message)
            Log.d("Mylog","${threadOn}transmitted message: ${message}")
            sleep(50)
        }

    }
    fun stopTh(){
        threadOn = false
        Log.d("Mylog","stopTh")
        Log.d("Mylog","Th: $isAlive")

    }
    fun startTh(){
        Log.d("Mylog","Th: $isAlive")
        if (isAlive){
            Log.d("Mylog","threadOn = true")
            threadOn = true
        }else{
            Log.d("Mylog","start()")
            start()
        }
    }

}

 */
class RepeatMessages(private val bTConnect: BTConnect): Thread() {
    private var repeatOn: Boolean = false
    private var threadOn: Boolean = false
    private lateinit var message: String

    override fun run() {
        Log.d("Mylog","RUNRepeatMessages!!!")
        while (threadOn){
            if (repeatOn){
                bTConnect.sendMassage(message)
                Log.d("Mylog","${threadOn}transmitted message: ${message}")
                sleep(20)
            }
        }

    }
    fun startTh(newMessages: String){
        Log.d("Mylog","Th: $isAlive")
        repeatOn = true
        message = newMessages
        if (!isAlive){
            Log.d("Mylog","start()")
            threadOn = true
            start()
        }
    }
    fun stopTh(){
        repeatOn = false
        Log.d("Mylog","stopTh")
        Log.d("Mylog","Th: $isAlive")
    }

    fun offTh(){

    }

}
