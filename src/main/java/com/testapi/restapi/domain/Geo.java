package com.testapi.restapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geo{

	@JsonProperty("lng")
	private String lng;

	@JsonProperty("lat")
	private String lat;

	public void setLng(String lng){
		this.lng = lng;
	}

	public String getLng(){
		return lng;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

}