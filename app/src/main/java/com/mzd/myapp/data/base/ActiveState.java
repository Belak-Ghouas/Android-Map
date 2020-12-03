package com.mzd.myapp.data.base;

public class ActiveState {

    private final String state;
    private final int uid;

    public ActiveState(String state, int uid) {
        this.state = state;
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public String getState() {
        return state;
    }

}
