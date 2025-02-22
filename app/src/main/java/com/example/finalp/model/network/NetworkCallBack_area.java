package com.example.finalp.model.network;



import com.example.finalp.model.Area;

import java.util.List;

public interface NetworkCallBack_area {
    public void onSuccess(List<Area> areaList);
    public void onFailure(String error);
}
