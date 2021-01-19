package com.vagrancy.study.wedget;

import android.os.Parcel;
import android.os.Parcelable;

import com.vagrancy.study.common.base.BaseNiceDialog;

/**
 * @author Vagrancy
 * @date 2021/1/19
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 弹窗监听器
 */
public abstract class ViewConvertListener  implements Parcelable {

    protected abstract void convertView(NiceViewHolder holder, BaseNiceDialog dialog);

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public ViewConvertListener() {
    }

    protected ViewConvertListener(Parcel in) {
    }

    public static final Creator<ViewConvertListener> CREATOR = new Creator<ViewConvertListener>() {
        @Override
        public ViewConvertListener createFromParcel(Parcel source) {
            return new ViewConvertListener(source){
                @Override
                protected void convertView(NiceViewHolder holder, BaseNiceDialog dialog) {

                }
            };
        }

        @Override
        public ViewConvertListener[] newArray(int size) {
            return new ViewConvertListener[size];
        }
    };
}