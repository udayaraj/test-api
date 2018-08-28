package com.testapi.restapi.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DomainTest {

    private Address address;
    private Company company;
    private Geo geo;
    private UserResponse userResponse;
    private Users users;

    @Before
    public void setUp() {
        address = address();
        company = company();
        geo = geo();
        userResponse = userResponse();
        users = users();
    }

    @Test
    public void testAddress() {
        String zipcode = address.getZipcode();
        Geo geo = address.getGeo();
        String suite = address.getSuite();
        String city = address.getCity();
        String street = address.getStreet();

        address.setZipcode(zipcode);
        address.setGeo(geo);
        address.setSuite(suite);
        address.setCity(city);
        address.setStreet(street);

        Assert.assertEquals(zipcode, address.getZipcode());
        Assert.assertEquals(geo, address.getGeo());
        Assert.assertEquals(suite, address.getSuite());
        Assert.assertEquals(city, address.getCity());
        Assert.assertEquals(street, address.getStreet());
    }

    @Test
    public void testCompany() {
        String bs = company.getBs();
        String catchPhrase = company.getCatchPhrase();
        String name = company.getName();

        company.setBs(bs);
        company.setCatchPhrase(catchPhrase);
        company.setName(name);

        Assert.assertEquals(bs, company.getBs());
        Assert.assertEquals(catchPhrase, company.getCatchPhrase());
        Assert.assertEquals(name, company.getName());
    }

    @Test
    public void testGeo() {
        String lng = geo.getLng();
        String lat = geo.getLat();

        geo.setLng(lng);
        geo.setLat(lat);

        Assert.assertEquals(lng, geo.getLng());
        Assert.assertEquals(lat, geo.getLat());
    }

    @Test
    public void testUserResponse() {
        String website = userResponse.getWebsite();
        Address address = userResponse.getAddress();
        String phone = userResponse.getPhone();
        String name = userResponse.getName();
        Company company = userResponse.getCompany();
        int id = userResponse.getId();
        String email = userResponse.getEmail();
        String username = userResponse.getUsername();

        userResponse.setWebsite(website);
        userResponse.setAddress(address);
        userResponse.setPhone(phone);
        userResponse.setName(name);
        userResponse.setCompany(company);
        userResponse.setId(id);
        userResponse.setEmail(email);
        userResponse.setUsername(username);

        Assert.assertEquals(website, userResponse.getWebsite());
        Assert.assertEquals(address, userResponse.getAddress());
        Assert.assertEquals(phone, userResponse.getPhone());
        Assert.assertEquals(name, userResponse.getName());
        Assert.assertEquals(company, userResponse.getCompany());
        Assert.assertEquals(id, userResponse.getId());
        Assert.assertEquals(email, userResponse.getEmail());
        Assert.assertEquals(username, userResponse.getUsername());
    }

    @Test
    public void testUsers() {
        int id = users.getId();
        Company company = users.getCompany();

        users.setId(id);
        users.setCompany(company);

        Assert.assertEquals(id, users.getId());
        Assert.assertEquals(company, users.getCompany());
    }

    private Address address() {
        Address address = new Address();
        address.setZipcode("122545");
        address.setGeo(geo());
        address.setSuite("suite");
        address.setCity("city");
        address.setStreet("street");
        return address;
    }

    private Company company() {
        Company company = new Company();
        company.setBs("bs");
        company.setCatchPhrase("catchPhrase");
        company.setName("name");
        return company;
    }

    private Geo geo() {
        Geo geo = new Geo();
        geo.setLng("12.15.263");
        geo.setLat("15.15.265");
        return geo;
    }

    private Users users() {
        Users users = new Users();
        users.setId(0);
        users.setCompany(company());
        return users;
    }

    private UserResponse userResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setWebsite("website");
        userResponse.setAddress(address());
        userResponse.setPhone("12312132321");
        userResponse.setName("name");
        userResponse.setCompany(company());
        userResponse.setId(0);
        userResponse.setEmail("email");
        userResponse.setUsername("username");
        return userResponse;
    }

}