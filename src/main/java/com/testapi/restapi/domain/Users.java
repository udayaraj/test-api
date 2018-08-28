package com.testapi.restapi.domain;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("users")
public class Users {

    @PrimaryKey("id")
    private int id;

    @Column("company")
    private Company company;

    public Users(int id, Company company) {
        this.id = id;
        this.company = company;
    }

    Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
