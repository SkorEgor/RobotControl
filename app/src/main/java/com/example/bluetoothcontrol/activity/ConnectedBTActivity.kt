package com.example.bluetoothcontrol.activity

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluetoothcontrol.BTConnect
import com.example.bluetoothcontrol.adapter.ListAdapterBT
import com.example.bluetoothcontrol.data.BTDevice
import com.example.bluetoothcontrol.databinding.ActivityMainBinding
import java.util.ArrayList

/**This activity shows connected devices and establishes connection
 *Эта активность показывает подключенные устройства и устанавливает соединение
 */
class ConnectedBTActivity : AppCompatActivity(), ListAdapterBT.Listener {

    private var btAdapter: BluetoothAdapter? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListAdapterBT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initializing adapter / Инициализация адаптера листа
        adapter = ListAdapterBT(this)
        binding.btRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.btRecyclerView.adapter  = adapter

        val devices = connectBT()
        //Submit a new list to be diffed, and displayed
        //Отправьте новый список для изменения и отображения.
        adapter.submitList(devices)
    }

    private fun connectBT(): ArrayList<BTDevice> {
        //1) Get the BluetoothAdapter.

        //The BluetoothAdapter is required for any and all Bluetooth activity.
        //Адаптер Bluetooth необходим для любых действий по Bluetooth.
        val btManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = btManager.adapter
        // val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

        //If getDefaultAdapter() returns null, then the device !doesn't support Bluetooth!
        //Если функция getDefaultAdapter() возвращает значение null, то устройство !не поддерживает Bluetooth!
        if (btAdapter == null) {
            // Device doesn't support Bluetooth
        }

        //2) Enable Bluetooth.
        //Включите Bluetooth.
        if (btAdapter?.isEnabled == false) {
            //request that Bluetooth be enabled
            //Запрос, чтобы Bluetooth был включен
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)

            val REQUEST_ENABLE_BT = 1
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            Log.d("BTDevice",  "YES")
            //bluetoothAdapter.enable()
        }


        //3) Scan bluetooth device ->  paired devices
        var devices = ArrayList<BTDevice>()
        //Запрос сопряженных устройств
        val pairedDevices: Set<BluetoothDevice>? = btAdapter?.bondedDevices
        pairedDevices?.forEach { it ->
            devices.add(BTDevice (it.name,it.address))
            Log.d("BTDevice",  it.name.toString())
        }

        return devices
    }

    override fun onClick(item: BTDevice) {
        //1)An Intent is a messaging object you can use to request an action from another app component.
        //Intent это объект обмена сообщениями, который вы можете использовать для запроса действия от другого компонент приложения.
        //2)apply and also return a context object.
        //apply и also возвращают объект контекста.
        val i = Intent().apply{
            // passing object with key "DEVICE_KEY" and the value of the element number
            // передача объекта с ключом "DEVICE_KEY" и значением номер элемента
            putExtra(DEVICE_KEY, item)
        }
        //var btConnect = btAdapter?.let { BTConnect(it) }
        //btConnect?.connect(item.mac!!)
        //shut down the activity
        //Завершаем работу активности
        setResult(RESULT_OK, i)
        finish()

    }


    //public objects, static
    //общедоступные объекты, статичные
    companion object{
        const val DEVICE_KEY  = "device_key"
    }
}