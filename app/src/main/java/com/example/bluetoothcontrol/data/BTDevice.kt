package com.example.bluetoothcontrol.data

import java.io.Serializable

/**
 * Information about the connected bluetooth device (MAC and NAME)
 * информация о подключенном блютуз устройстве (MAC и ИМЯ)
 */
//Serializable/Сериализация — процесс перевода структуры данных в последовательность байтов. Делает класс доступным для передачи
class BTDevice(input_name:String, input_mac:String): Serializable {
    var name: String? = null
    var mac:String? = null
    init{
        name = input_name
        mac = input_mac
    }
}