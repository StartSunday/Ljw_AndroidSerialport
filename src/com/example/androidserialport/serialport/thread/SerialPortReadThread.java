package com.example.androidserialport.serialport.thread;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.example.androidserialport.DataUtil;

import android.util.Log;


/**
 * 串口消息读取线程
 */

public abstract class SerialPortReadThread extends Thread {

    public abstract void onDataReceived(byte[] bytes);

    private static final String TAG = SerialPortReadThread.class.getSimpleName();
    private InputStream mInputStream;
    private byte[] mReadBuffer;

    public SerialPortReadThread(InputStream inputStream) {
        mInputStream = inputStream;
        mReadBuffer = new byte[1024];
    }

    @Override
    public void run() {
        super.run();

        while (!isInterrupted()) {
            try {
                if (null == mInputStream) {
                    Log.e(TAG, "run mInputStream is null");
                    return;
                }

                Log.i(TAG, "run read data");
                int size = mInputStream.read(mReadBuffer);

                if (-1 == size || 0 >= size) {
                    Log.e(TAG, "run read data size error size= "+size);
                    return;
                }

                byte[] readBytes = new byte[size];
                Log.i(TAG, "run: read size = " + size);

                System.arraycopy(mReadBuffer, 0, readBytes, 0, size);
                Arrays.fill(mReadBuffer, (byte)0);
                Log.i(TAG, "run readBytes = " + DataUtil.bytesToHexString(readBytes));
                
                onDataReceived(readBytes);

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    /**
     * 关闭线程 释放资源
     */
    public void release() {
        interrupt();

        if (null != mInputStream) {
            try {
                mInputStream.close();
                mInputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
