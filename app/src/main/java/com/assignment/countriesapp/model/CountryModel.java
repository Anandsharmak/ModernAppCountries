package com.assignment.countriesapp.model;

public class CountryModel {

    public String name;
    public String capital;
    public String flag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public CountryModel(String name, String capital, String flag) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
    }
}
