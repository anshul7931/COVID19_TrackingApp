package com.corona.coronaVirusTracker.model;

public class LocationStats {
	
	private String state;
	
	private String country;
	
	private int latestTotalCases;
	
	private int reportedToday;

	public int getReportedToday() {
		return reportedToday;
	}

	public void setReportedToday(int reportedToday) {
		this.reportedToday = reportedToday;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

	

}
