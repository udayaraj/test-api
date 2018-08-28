package com.testapi.restapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

    @JsonProperty("zipcode")
    private String zipcode;

    @JsonProperty("geo")
    private Geo geo;

    @JsonProperty("suite")
    private String suite;

    @JsonProperty("city")
    private String city;

    @JsonProperty("street")
    private String street;

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getSuite() {
        return suite;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

}