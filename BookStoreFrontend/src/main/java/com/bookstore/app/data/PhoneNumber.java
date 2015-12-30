package com.bookstore.app.data;

import java.io.Serializable;

public class PhoneNumber implements Serializable {
    private String areaCode;
    private String prefix;
    private String number;

    public PhoneNumber(String code, String number, String prefix) {
        this.areaCode = code;
        this.number = number;
        this.prefix = prefix;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}