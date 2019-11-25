package com.example.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anchaoguang on 2019-11-21.
 */
public class Person implements Parcelable {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected Person(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

    // 名字必须是CREATOR，并且final修饰
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            //把我们通过writeToParcel方法写进的数据再读出来，哪个字段先写进去，就先读哪个
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{name=" + name + " ,age=" + age + " }";
    }

    // 需要重写的第一个接口，一般不需要管
    @Override
    public int describeContents() {
        return 0;
    }

    // 需要重写的第二个接口，将对象的每个字段写入Parcel中
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
