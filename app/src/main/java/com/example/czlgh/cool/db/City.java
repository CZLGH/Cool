package com.example.czlgh.cool.db;

import android.provider.ContactsContract;

import org.litepal.crud.DataSupport;

/**
 * Created by CZLGH on 2017/12/13.
 */

public class City extends DataSupport{
    private int id;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    private String cityName;

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    private int cityCode;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
