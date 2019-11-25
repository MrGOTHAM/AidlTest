package com.example.aidl.person;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.example.aidl.DataTestAidlInterface;
import com.example.aidl.Person;

import java.util.List;

/**
 * Created by anchaoguang on 2019-11-21.
 */
public class IRemoteService extends Service {
    List<Person> persons;

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
    
   private IBinder iBinder = new DataTestAidlInterface.Stub() {
       @Override
       public List<com.example.aidl.Person> getPersonListIn(com.example.aidl.Person person) throws RemoteException {
           persons.add(person);
           return persons;
       }
   };
}
