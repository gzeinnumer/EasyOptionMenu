package com.gzeinnumer.eom.dialog;

import java.util.List;

class BaseList<T> {
    private int pos;
    private List<T> data;

    public BaseList(int pos, List<T> data) {
        this.pos = pos;
        this.data = data;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
