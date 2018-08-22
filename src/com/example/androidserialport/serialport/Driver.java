package com.example.androidserialport.serialport;


import java.io.File;
import java.util.ArrayList;

import android.util.Log;

public class Driver {

    private static final String TAG = Driver.class.getSimpleName();

    private String mDriverName;
    private String mDeviceRoot;

    public Driver(String name, String root) {
        mDriverName = name;
        mDeviceRoot = root;
    }

    public ArrayList<File> getDevices() {
        ArrayList<File> devices = new ArrayList<File>();
        File dev = new File("/dev");

        if (!dev.exists()) {
            Log.i(TAG, "getDevices: " + dev.getAbsolutePath() + " not exist");
            return devices;
        }
        if (!dev.canRead()) {
            Log.i(TAG, "getDevices: " + dev.getAbsolutePath() + " not read or write permission");
            return devices;
        }

        File[] files = dev.listFiles();

        int i;
        for (i = 0; i < files.length; i++) {
            if (files[i].getAbsolutePath().startsWith(mDeviceRoot)) {
                Log.d(TAG, "Found new device: " + files[i]);
                devices.add(files[i]);
            }
        }
        return devices;
    }

    public String getName() {
        return mDriverName;
    }

}
