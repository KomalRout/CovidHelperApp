package com.example.kiit.myapplication.modal;

public class DistrictModal {

    private String district;

    public DistrictModal(String district, String confirmed, String active, String recovered, String deceased, String newConfirmed, String newRecovered, String newDeceased) {
        this.district = district;
        this.confirmed = confirmed;
        this.active = active;
        this.recovered = recovered;
        this.deceased = deceased;
        this.newConfirmed = newConfirmed;
        this.newRecovered = newRecovered;
        this.newDeceased = newDeceased;
    }

    private String confirmed;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(String newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public String getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(String newRecovered) {
        this.newRecovered = newRecovered;
    }

    public String getNewDeceased() {
        return newDeceased;
    }

    public void setNewDeceased(String newDeceased) {
        this.newDeceased = newDeceased;
    }

    private String active;
    private String recovered;
    private String deceased;
    private String newConfirmed;
    private String newRecovered;
    private String newDeceased;
}
