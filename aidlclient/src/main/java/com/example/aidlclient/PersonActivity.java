package com.example.aidlclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.Toast;
import com.example.aidl.DataTestAidlInterface;
import com.example.aidl.Person;

import java.util.List;

/**
 * Created by anchaoguang on 2019-11-21.
 */
public class PersonActivity extends Activity {
    private Button btn;
    private List<Person> persons;
    private DataTestAidlInterface mDataTestAidlInterface;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDataTestAidlInterface = DataTestAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (mDataTestAidlInterface != null) {
                mDataTestAidlInterface = null;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_activity);
        initView();
        bindService();

    }
    private void initView(){
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(v->{
            Person person = new Person("张三", 10);
            try{
                persons = mDataTestAidlInterface.getPersonListIn(person);
            }catch (RemoteException e){
                e.printStackTrace();
            }
            Toast.makeText(this,persons.toString(),Toast.LENGTH_SHORT).show();
        });
    }

    private void bindService(){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.aidl","com.example.aidl.person.IRemoteService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
