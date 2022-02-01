package com.example.bluetoothcontrol

import android.bluetooth.BluetoothAdapter
import android.util.Log

/**
 * Класс принимает адаптер, мак и запускает отдельный поток для подключения
 */
class BTConnect(private val adapter: BluetoothAdapter) {
    lateinit var btConnectTread: ConnectThread
    fun connect(mac: String) {
        Log.d("Mylog","aaa")
        if (adapter.isEnabled && mac.isNotEmpty()) {
            //Получаем устройство
            val device = adapter.getRemoteDevice(mac)
            device.let {
                Log.d("Mylog","run ConnectThread")
                //Второстепенный поток для соединения
                btConnectTread = ConnectThread(it)
                btConnectTread.start()
            }
        }
    }

    fun sendMassage(massage: String){
        btConnectTread.threadRxTx.sendMassage(massage.toByteArray())
    }
}