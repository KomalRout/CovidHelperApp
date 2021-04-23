package com.example.kiit.myapplication.modal;

public class StateWiseModal {
    private String state;
    private String confirmed;
    private String confirmed_new;
    private String active;
    private String death;
    private String death_new;
    private String recovered;
    private String recovered_new;
    private String lastupdate;

    public StateWiseModal(String state, String confirmed, String confirmed_new, String active, String death, String death_new, String recovered, String recovered_new, String lastupdate) {
        this.state = state;
        this.confirmed = confirmed;
        this.confirmed_new = confirmed_new;
        this.active = active;
        this.death = death;
        this.death_new = death_new;
        this.recovered = recovered;
        this.recovered_new = recovered_new;
        this.lastupdate = lastupdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getConfirmed_new() {
        return confirmed_new;
    }

    public void setConfirmed_new(String confirmed_new) {
        this.confirmed_new = confirmed_new;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getDeath_new() {
        return death_new;
    }

    public void setDeath_new(String death_new) {
        this.death_new = death_new;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getRecovered_new() {
        return recovered_new;
    }

    public void setRecovered_new(String recovered_new) {
        this.recovered_new = recovered_new;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }


}
