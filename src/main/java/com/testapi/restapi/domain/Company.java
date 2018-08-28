package com.testapi.restapi.domain;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("company")
public class Company {

    @Column("bs")
    private String bs;

    @Column("catchPhrase")
    private String catchPhrase;

    @Column("name")
    private String name;

    Company() {
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getBs() {
        return bs;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}