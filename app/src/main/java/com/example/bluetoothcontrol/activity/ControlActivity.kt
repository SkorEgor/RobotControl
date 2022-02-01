package com.example.bluetoothcontrol.activity


import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.bluetoothcontrol.BTConnect
import com.example.bluetoothcontrol.R
import com.example.bluetoothcontrol.RepeatMessages
import com.example.bluetoothcontrol.data.BTDevice
import com.example.bluetoothcontrol.databinding.ActivityControlBinding
import android.view.View.OnTouchListener

import android.annotation.SuppressLint


class ControlActivity : AppCompatActivity() {
    private lateinit var binding: ActivityControlBinding
    private lateinit var actListLauncher: ActivityResultLauncher<Intent>
    private lateinit var btConnect: BTConnect
    private var mac: String? = null

    private fun init(){
        val btManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val btAdapter = btManager.adapter
        btConnect = BTConnect(btAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityControlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBtListResult()
        init()
        initializingButtons()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //instantiate menu XML files into Menu objects.
        //создания экземпляров XML-файлов меню в объекты меню.
        menuInflater.inflate(R.menu.control_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId== R.id.list){
            actListLauncher.launch(Intent(this, ConnectedBTActivity::class.java))
        }else if(item.itemId== R.id.connect){
            if (mac!=null){
                Log.d("Mylog","mac: $mac")
                btConnect.connect(mac!!)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Смотрим какой устройство выбранно
    private fun onBtListResult(){
        actListLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                Log.d("Mylog","Name: ${(it.data?.getSerializableExtra(ConnectedBTActivity.DEVICE_KEY)as BTDevice).name}")
                mac = (it.data?.getSerializableExtra(ConnectedBTActivity.DEVICE_KEY) as BTDevice).mac
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializingButtons(){
        //initializing the forward button   F
        var repeatMessages = RepeatMessages(btConnect)
        binding.forwardBut.setOnTouchListener(OnTouchListener { _, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> { repeatMessages.startTh("F")}
                MotionEvent.ACTION_UP -> { repeatMessages.stopTh() }
            }
            return@OnTouchListener true
        })
        //initializing the back button      B
        binding.backBut.setOnTouchListener(OnTouchListener { _, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> { repeatMessages.startTh("B")}
                MotionEvent.ACTION_UP -> { repeatMessages.stopTh() }
            }
            return@OnTouchListener true
        })
        //initializing the left button      L
        binding.leftBut.setOnTouchListener(OnTouchListener { _, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> { repeatMessages.startTh("L")}
                MotionEvent.ACTION_UP -> { repeatMessages.stopTh() }
            }
            return@OnTouchListener true
        })
        //initializing the left button      R
        binding.rightBut.setOnTouchListener(OnTouchListener { _, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> { repeatMessages.startTh("R")}
                MotionEvent.ACTION_UP -> { repeatMessages.stopTh() }
            }
            return@OnTouchListener true
        })
    }
}