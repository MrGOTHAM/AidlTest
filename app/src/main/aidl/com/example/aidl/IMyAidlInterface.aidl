package com.example.aidl;

interface IMyAidlInterface {
// 除了short外的基本数据类型都可以，因为short无法被序列化
    int add(int num1, int num2);
}
