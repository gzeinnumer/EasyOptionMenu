package com.gzeinnumer.eom;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gzeinnumer.eom.dialog.DynamicOptionMenu;

import java.util.List;

public class DynamicOptionMenuBuilder<T> {
    private FragmentTransaction transaction;
    private DynamicOptionMenu<T> dialog;
    public <T> DynamicOptionMenuBuilder(FragmentManager fragmentManager) {
        transaction = fragmentManager.beginTransaction();
        Fragment previous = fragmentManager.findFragmentByTag(DynamicOptionMenu.TAG);
        if (previous != null) {
            transaction.remove(previous);
        }
    }

    public DynamicOptionMenuBuilder<T> builder(List<T> list){
        dialog = new DynamicOptionMenu<>(list);
        return this;
    }

    public DynamicOptionMenuBuilder<T> setTitle(String title) {
        dialog.setTitle(title);
        return this;
    }

    public DynamicOptionMenuBuilder<T> addSub(DynamicOptionMenu.CallBack<T>... callBacks){
        dialog.setCallBacks(callBacks);
        return this;
    }

    public DynamicOptionMenuBuilder<T> finalCallBack(DynamicOptionMenu.CallBackFinal<T> callBackFinal){
        dialog.setCallBackFinal(callBackFinal);
        return this;
    }

    public void show() {
        dialog.show(transaction, DynamicOptionMenu.TAG);
    }
}
