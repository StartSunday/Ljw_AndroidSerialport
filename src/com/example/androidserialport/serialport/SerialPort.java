package com.example.androidserialport.serialport;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public class SerialPort {

    static {
        System.loadLibrary("SerialPort");
    }

    private static final String TAG = SerialPort.class.getSimpleName();

    /**
     * 文件设置最高权限 777 可读 可写 可执行
     *
     * @param file 文件
     * @return 权限修改是否成功
     */
    @SuppressLint("NewApi")
    boolean chmod777(File file) {
        if (null == file || !file.exists()) {
            // 文件不存在
            Log.e(TAG, "chmod777 file is not exist");
            return false;
        }
        try {
            // 获取ROOT权限
            Process su = Runtime.getRuntime().exec("/system/bin/su");
            // 修改文件属性为 [可读 可写 可执行]
            String cmd = "chmod 777 " + file.getAbsolutePath() + "\n" + "exit\n";
            su.getOutputStream().write(cmd.getBytes());
            if (0 == su.waitFor() && file.canRead() && file.canWrite() && file.canExecute()) {
                return true;
            }
        } catch (IOException e) {
            // 没有ROOT权限
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
        return false;
    }
    
    /**
     * 文件设置最高权限 777 可读 可写 可执行
     *
     * @param file 文件
     * @return 权限修改是否成功
     */
    public boolean stopConsole() {
    	Log.d(TAG, "stop console");
    	Runtime localRuntime = Runtime.getRuntime();
		int ret = -1;
		try {
			ret = localRuntime.exec(new String[] { "stop", "console"}).waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d(TAG, "stop console e:" + e.toString());
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d(TAG, "stop console e:" + e.toString());
			return false;
		}
		 
		Log.d(TAG, "stop console ret:" + ret);
        return true;
    }

    // 打开串口
    protected native FileDescriptor open(String path, int baudRate, int flags);

    // 关闭串口
    protected native void close();
}
