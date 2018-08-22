package com.example.androidserialport;


import java.io.File;
import java.util.ArrayList;

import com.example.androidserialport.serialport.Device;
import com.example.androidserialport.serialport.SerialPortFinder;
import com.example.androidserialport.serialport.SerialPortManager;
import com.example.androidserialport.serialport.listener.OnOpenSerialPortListener;
import com.example.androidserialport.serialport.listener.OnSerialPortDataListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String DEVICE_NAME = "ttymxc3";
    private static final int BAUD_RATE = 115200;
    
    private SerialPortManager mSerialPortManager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
    }
    
    private void init() {
        mSerialPortManager = new SerialPortManager();
        mSerialPortManager.setOnOpenSerialPortListener(mListener);
        mSerialPortManager.setOnSerialPortDataListener(mDataListener);
        
        Device device = getSerialPortDevice();
        openSerialPort(device);
    }
    
    private OnOpenSerialPortListener mListener = new OnOpenSerialPortListener() {

        @Override
        public void onSuccess(File device) {
            Log.d(TAG, "mListener onSuccess device: "+device.toString());
        }

        @Override
        public void onFail(File device, Status status) {
            Log.d(TAG, "mListener onFail device: "+device.toString()+"; status: "+status.toString());
        }
    };
    
    private OnSerialPortDataListener mDataListener = new OnSerialPortDataListener() {

        @Override
        public void onDataReceived(byte[] bytes) {
            Log.i(TAG, "onDataReceived: " + DataUtil.bytesToHexString(bytes));
        }

        @Override
        public void onDataSent(byte[] bytes) {
            Log.i(TAG, "onDataSent: " + DataUtil.bytesToHexString(bytes));
        }
    };
    
    private Device getSerialPortDevice() {
        Device device = null;
        SerialPortFinder serialPortFinder = new SerialPortFinder();
        ArrayList<Device> devices = serialPortFinder.getDevices();

        for (int i = 0; i < devices.size(); i++) {
            Log.d(TAG, "getSerialPortDevice: " + devices.get(i).getName());
            if (devices.get(i).getName().equals(DEVICE_NAME)) {
                device = devices.get(i);
                break;
            }
        }
        return device;
    }
    
    private boolean openSerialPort(Device device) {
        boolean ret = mSerialPortManager.openSerialPort(device.getFile(), BAUD_RATE);
        Log.i(TAG, "openSerialPort openSerialPort ret= " + ret);
        return ret;
    }
}
