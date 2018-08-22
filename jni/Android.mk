LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SHARED_LIBRARIES := liblog libcutils
				
LOCAL_MODULE    := libSerialPort
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := SerialPort.c

include $(BUILD_SHARED_LIBRARY)

