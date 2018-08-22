# Android串口读写

1、获取串口列表.

2、选择匹配的串口打开。
注意权限！！！

(1)可手动在adb shell修改权限，输入chmod 606 /dev/ttymxc3(我的串口节点是/dev/ttymxc3)

(2)在对应的init.rc中，添加chmod 0606 /dev/ttymxc3

3、手动在adb shell中输入stop console关闭串口(获取通过代码实现,需要system权限),否则获取不到数据或者数据不完整.

4、通过PC端的sscom5.13.1工具打开电脑com串口，然后发送数据.(pc端工具已上传)

5、读取到数据后,可按照自定义协议进行解析.