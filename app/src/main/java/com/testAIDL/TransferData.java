package com.testAIDL;

import android.os.Parcel;
import android.os.Parcelable;

public class TransferData implements Parcelable{
	public String srcPath;
	public String dstPath;
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(srcPath);
		dest.writeString(dstPath);
	}
	
	public void readFromParcel(Parcel in) {
		srcPath = in.readString();
		dstPath = in.readString();
	}
	public static final Parcelable.Creator<TransferData> CREATOR
			= new Creator<TransferData>() {
				
				@Override
				public TransferData[] newArray(int size) {
					// TODO Auto-generated method stub
					return new TransferData[size];
				}
				
				@Override
				public TransferData createFromParcel(Parcel source) {
					// TODO Auto-generated method stub
					return new TransferData(source);
				}
			};
			
	private TransferData(Parcel in){
		readFromParcel(in);
	}
	public TransferData(String src){
		srcPath =src;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		return sb.append("TransferData ,srcPath: ").append(srcPath)
				.append(",dstPath: ").append(dstPath).
				append(". ").append(this.hashCode()).toString();
	}
	
}
