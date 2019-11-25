// DataTestAidlInterface.aidl
package com.example.aidl;

import com.example.aidl.Person;
interface DataTestAidlInterface {
   List<Person> getPersonListIn(in Person person);
}
