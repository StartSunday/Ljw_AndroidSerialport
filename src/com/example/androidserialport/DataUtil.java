package com.example.androidserialport;

import java.nio.ByteBuffer;

public final class DataUtil {

    private static final String TAG = "DataUtil";
    public static final int PACKET_MIN_LENGTH = 5;

    public static boolean isNonEmptyByteArray(byte[] from) {
        for (byte b : from) {
            if (b != 0)
                return true;
        }
        return false;
    }

    public static byte[] shortToByte(short value) {
        byte[] result = new byte[2];
        result[0] = (byte) ((value >> 8) & 0xFF);
        result[1] = (byte) (value & 0xFF);
        return result;
    }

    
    public static byte[] intToBytes(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
    }

    public static String bytesToHexString(byte[] data) {
        if (data == null) return "";
        StringBuilder s = new StringBuilder(data.length * 2);
        for (byte b : data) {
            s.append(String.format("%02x", b & 0xFF))
            .append(" ");
        }
        return s.toString();
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String bytesToHexString(byte[] src, int len) {
        StringBuilder stringBuilder = new StringBuilder("");
        if ((src == null) || (src.length <= 0) || (len <= 0)) {
            return null;
        }
        for (int i = 0; i < len; ++i) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    
    public static int bytesToInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

    public static short bytesToShort(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getShort();
    }

    public static long bytesToLong(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getLong();
    }

    public static float bytesToFloat(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getFloat();
    }

    public static byte[] read2Byte(byte[] src,int offset) {
        byte[] data = new byte[2];
        System.arraycopy(src, offset, data, 0, 2);
        return data;
    }

    public static byte[] read4Byte(byte[] src,int offset) {
        byte[] data = new byte[4];
        System.arraycopy(src, offset, data, 0, 4);
        return data;
    }
    public static byte[] read3Byte(byte[] src,int offset) {
        byte[] data = new byte[3];
        System.arraycopy(src, offset, data, 0, 3);
        return data;
    }

    public static short readShort(byte[] src,int offset) {
        byte[] data = new byte[2];
        System.arraycopy(src, offset, data, 0, 2);
        return bytesToShort(data);
    }

    public static int readInt(byte[] src,int offset) {
        byte[] data = new byte[4];
        System.arraycopy(src, offset, data, 0, 4);
        return bytesToInt(data);
    }

    public static short unsignByteToShort(byte b) {
        byte[] data = new byte[2];
        data[0] = 0;
        data[1] = b;
        return bytesToShort(data);
    }

    public static byte[] doubleToBytes(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    public static String byteToHex(byte b) {
        return String.format("%02x", b & 0xFF);
    }
    
    /**
	 * 仅限于转换"8a"类似的字符串
	 */
	public static byte string2Byte(String s){
		int flag = Integer.parseInt(s,16);
		byte by = (byte)flag;
		return by;
	}
}
