# EasyLog

Easylog 是Android平台的一款开源的，简单易用的日志输出工具。

## 特点
* 配置简单，使用方便，从此日志输出so easy
* 对工程代码影响小，不需要修改原代码
* 提示信息明显，使用上下边框进行区分
* 定位直接，调试信息输出代码位置

## 快速配置

在项目的`module`模块的`build.gradle`文件添加如下信息：

	dependencies {
		......
    	compile 'com.goorwl:easylog:0.2.0'
	}

同步一下即可。

## 使用方式
代码：

    EasyLog.v("test verbose info...");
    EasyLog.i("test info info...");
    EasyLog.d("test debug info...");
    EasyLog.w("test warn info...");
    EasyLog.e("test error info...");

输出：

	V/= EasyLog =: *******************
	V/= EasyLog =: test verbose info...
	V/= EasyLog =: *******************
	I/= EasyLog =: *****************
	I/= EasyLog =: test info info...
	I/= EasyLog =: *****************
	D/= EasyLog =: ******************
	D/= EasyLog =: test debug info...
	D/= EasyLog =: ******************
	W/= EasyLog =: *****************
	W/= EasyLog =: test warn info...
	W/= EasyLog =: *****************
	E/= EasyLog =: ******************
	E/= EasyLog =: test error info...
	E/= EasyLog =: ******************

## 高级配置
为了提高当前工具的更多效果，现添加如下设置。

### 设置TAG
默认输出日志的TAG是：`= EasyLog =`，如果需要单独设置，可通过`setTag（String tag）`函数进行设置。

代码：

    EasyLog.v("test verbose info...");
    EasyLog.setTag("TEST-TAG");
    EasyLog.i("test info info...");

输出：

    V/= EasyLog =: *******************
    V/= EasyLog =: test verbose info...
    V/= EasyLog =: *******************
    I/TEST-TAG: *****************
    I/TEST-TAG: test info info...
    I/TEST-TAG: *****************


### 设置输出时间
代码：

    EasyLog.setIsTime(true);

输出：

	11-10 03:13:55.459 4597-4597/? D/= EasyLog =: *****************************************
	11-10 03:13:55.459 4597-4597/? D/= EasyLog =: 2017-11-10 03:13:55 == test debug info...
	11-10 03:13:55.459 4597-4597/? D/= EasyLog =: *****************************************
	11-10 03:13:55.459 4597-4597/? W/= EasyLog =: ****************************************
	11-10 03:13:55.459 4597-4597/? W/= EasyLog =: 2017-11-10 03:13:55 == test warn info...
	11-10 03:13:55.459 4597-4597/? W/= EasyLog =: ****************************************

### 设置显示定位信息

代码：

    EasyLog.setClazz(this);		// 参数：Activity
    EasyLog.setIsLine(true);

输出：

	V/= EasyLog =: **********************************************
	V/= EasyLog =: (MainActivity.java:26) == test verbose info...
	V/= EasyLog =: **********************************************
	I/= EasyLog =: *******************************************
	I/= EasyLog =: (MainActivity.java:27) == test info info...
	I/= EasyLog =: *******************************************

注：其中`（MainActivity.java:xx）`是可点击的,将会跳转到源代码部分，效果如下：

![可点击效果](https://i.imgur.com/L2xxh4c.png)

如果未设置`activity`，将会出现如下现象：

	V/= EasyLog =: **********************************************
	V/= EasyLog =: (the activity is null) == test verbose info...
	V/= EasyLog =: **********************************************
	I/= EasyLog =: *******************************************
	I/= EasyLog =: (the activity is null) == test info info...
	I/= EasyLog =: *******************************************

### 全配置效果：

代码：

    EasyLog.setTag("TEST-TAG");
    EasyLog.setIsTime(true);
    EasyLog.setClazz(this);
    EasyLog.setIsLine(true);
    EasyLog.v("test verbose info...");
    EasyLog.i("test info info...");

简易写法：

	EasyLog.setTag("TEST-TAG");
    EasyLog.setClazz(this);
    EasyLog.setAll(true);
    EasyLog.v("test verbose info...");
    EasyLog.i("test info info...");

输出：

	V/TEST-TAG: *********************************************************************
	V/TEST-TAG: 2017-11-10 03:28:00 == (MainActivity.java:25) == test verbose info...
	V/TEST-TAG: *********************************************************************
	I/TEST-TAG: ******************************************************************
	I/TEST-TAG: 2017-11-10 03:28:00 == (MainActivity.java:26) == test info info...
	I/TEST-TAG: ******************************************************************

### 取消输出

鉴于使用当前控件的用户都是开发者，所以当前默认设置的是输出，如果上线，可以在代码比较靠前的位置设置不显示:

	EasyLog.setIsShow(false);

如果需要进行新版本开发，只需要把上面代码注释掉即可显示日志输出信息。

## 补充说明

当前版本比较新，功能基本够用，如果大家还有新的需求，可以提出来，后续版本迭代会进行更新。

## 联系方式

E-mail：goorwl@163.com
