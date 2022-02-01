package com.example.bluetoothcontrol

import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class BTStreamThread(val btScocket: BluetoothSocket): Thread(){
    var inStream: InputStream?=null
    var outStream: OutputStream?=null

    init{
        try{
            inStream = btScocket.inputStream

        }catch(i: IOException){}
        try{
            outStream = btScocket.outputStream
        }catch(i: IOException){}
    }

    override fun run() {
        //Получаем данные Для одной буквы
        val buf = ByteArray(2)
        while(true){
            try{
                val size = inStream?.read(buf)
                //offset - с какой позиции считывать
                val message = String(buf,0,size!!)
                Log.d("Mylog","mac: ${message}")
            }catch(i: IOException){ break }
        }
    }
    fun sendMassage(byteArray: ByteArray){
        try{
            outStream?.write(byteArray)
        }catch(i: IOException){}
    }
}