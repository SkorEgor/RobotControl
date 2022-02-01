package com.example.bluetoothcontrol

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.util.*

/**
 * Поток для установелния канала связи
 */

class ConnectThread(private val device: BluetoothDevice): Thread() {
    //Индификатор для подключения
    val uuid = "00001101-0000-1000-8000-00805F9B34FB"
    var mySocket: BluetoothSocket? = null
    lateinit var threadRxTx: BTStreamThread

    init{
        try{
            mySocket = device.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
        }catch(i: IOException){

        }
    }

    override fun run() {
        try{
            Log.d("Mylog","connecting . . .")
            mySocket?.connect()
            Log.d("Mylog","connect to device")
            //НАЧАЛО ПОТОКА ОБМЕНА ДАННЫМИ
            threadRxTx = BTStreamThread(mySocket!!)
            threadRxTx.start()
        }catch(i: IOException){
            Log.d("Mylog","Can not connect to device")
        }
    }

    fun closeConnection(){
        try{
            mySocket?.close()

        }catch(i: IOException){

        }
    }
}