package com.example.aidl;

/**
 * 安卓进程通信方式为4种，分别对应四大组件：Activity，Broadcast，ContentProvider，Service
 * （1）activity：Activity可以通过intent访问其他进程的activity
 * （2）broadcast:broadcast可以给Android系统中所有的应用程序发送广播，需要跨进程通信的应用
 * 可以监听这些广播
 * （3）contentProvider:contentProvider可向其他应用程序共享数据，以及运行其他应用程序对其
 * 数据进行增删改查操作。
 * （4）service:service利用AIDL进行通信
 */

/**
 * AIDL:一种接口定义语言，Android的每个进程都运行在独立的虚拟机中，所以，进程间通信较为麻烦。
 * 我们可利用AIDL将一个进程的数据拆分成Android系统可识别的数据单元，然后系统再重新将数据单元
 * 合成传递给另一个进程。这样就实现了进程间的通信。
 *
 *在使用时，需要分别安装两个模块，相当于安装了两个App，先开启服务app,再开启客户端App,否则null
 */

/**
 * 1.先在工程中创建一个新的模块，aidlclient模块
 * 2.在原模块(app)和新加的模块(aidlclient)分别添加与java同级的aidl包
 * 3.在aidl包中分别创建IMyAidlInterface.aidl文件，编写的文件中内容相同(必须一致)
 * 4.然后编译一下程序，可以发现，在project文件下，build->generated中可以看到aidl相关文件
 * 5.其中最重要的一个文件为 IMyAidlInterface接口文件（自动生成的）
 * 6.在服务端java文件夹下创建IRemoteService类，这个类是用来计算客户端传来的数据并返回结果的
 * 7.在服务端的配置清单中注册Service
 * 8.客户端建立自己的xml界面文件
 * 9.编写客户端主文件：当启动客户端，将会执行bindService方法，去绑定服务端的远程服务，一旦绑
 * 定成功，就会回调conn中的onServiceConnected方法，在方法中，我们获取到了服务端实现好的接口。
 * （因为服务端已经将该实现好的接口暴露给了客户端）
 */

// Declare any non-default types here with import statements
// aidl进程传递数据时，都有一个数据拆包和打包的过程，这个很消耗系统内存
// 我们要在传递的数据前面加上 in、out、inout表示数据传递的方向
// in    表示客户端向服务端传递数据，当服务的数据发生改变，不会影响到客户端
// out   表示服务端对数据的修改，客户端会同步变动
// inout 表示客户端和服务端的数据总是同步的