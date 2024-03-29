package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aidl.IMyAidlInterface;

/**
 * Created by anchaoguang on 2019-11-21.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_num1;
    private EditText et_num2;
    private EditText edit_show_result;
    private Button btn_count;
    private int mNum1;
    private int mNum2;
    private int mTotal;
    private IMyAidlInterface iMyAidlInterface;
    //绑定服务回调
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //服务绑定成功后调用，获取服务端的接口，这里的service就是服务端onBind返
            //回的iBinder即已实现的接口
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //解除绑定时调用，清空接口，防止内容溢出
            iMyAidlInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService();
        initView();
    }

    //初始化界面
    private void initView() {
        et_num1 = findViewById(R.id.et_num1);
        et_num2 = findViewById(R.id.et_num2);
        edit_show_result = findViewById(R.id.edit_show_result);
        btn_count = findViewById(R.id.btn_count);
        btn_count.setOnClickListener(this);
    }

    //按钮点击事件
    private void handleBtnClickEvent() {
        mNum1 = Integer.parseInt(et_num1.getText().toString());
        mNum2 = Integer.parseInt(et_num2.getText().toString());
        try {
            mTotal = iMyAidlInterface.add(mNum1, mNum2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        edit_show_result.setText(mTotal + "");
    }

    //绑定服务
    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.aidl",
                "com.example.aidl.IRemoteService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        Log.i("ancg", "已经启动了service！");
    }

    @Override
    public void onClick(View v) {
        handleBtnClickEvent();
    }

    protected void onDestroy() {
        super.onDestroy();
        //当活动销毁时解除绑定
        unbindService(conn);
    }
}