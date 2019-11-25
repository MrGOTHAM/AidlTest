package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by anchaoguang on 2019-11-21.
 * 服务类代码
 */
public class IRemoteService extends Service {

    // 将接口暴露给客户端
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    // 实现我们之前定义的接口
    private IBinder iBinder = new IMyAidlInterface.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    };
}

