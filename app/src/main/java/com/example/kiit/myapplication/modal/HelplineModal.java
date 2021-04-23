package com.example.kiit.myapplication.modal;

import java.io.Serializable;

public class HelplineModal implements Serializable {
    String st_name;
    String phone;

    public HelplineModal(String st_name, String phone) {
        this.st_name = st_name;
        this.phone = phone;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
