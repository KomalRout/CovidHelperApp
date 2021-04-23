package com.example.kiit.myapplication.modal;

public class VaccineModal {
    private int zero;
    private String state;
    private int total;
    public VaccineModal() {
    }

    public VaccineModal( String state, int total) {
        this.state = state;
        this.total = total;
    }

    public int getZero() {
        return zero;
    }

    public void setZero(int zero) {
        this.zero = zero;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
