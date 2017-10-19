package com.testAIDL;

import android.os.IBinder;
import com.testAIDL.TransferData;
interface IPreparePresendCallBack{

void onPrepare(in IBinder messenger);

void compress(inout TransferData data);
}