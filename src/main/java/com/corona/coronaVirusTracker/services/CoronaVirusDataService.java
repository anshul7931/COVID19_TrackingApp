package com.corona.coronaVirusTracker.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.corona.coronaVirusTracker.model.LocationStats;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	private List<LocationStats> allStats = new ArrayList<>();
	
	@PostConstruct // When construction of this service class is done, just execute this method
	@Scheduled(cron = "* 30 * * * *")// Run every second for live updates
	public void fetchVirusData() throws IOException {
		List<LocationStats> newStats = new ArrayList<>();// Creating a new list after scheduled time to avoid concurrency issues with single list
		URL url = new URL(VIRUS_DATA_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
		con.setRequestMethod("GET"); 

		BufferedReader inp = new BufferedReader(new InputStreamReader(con.getInputStream())); 
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(inp);
		
		for (CSVRecord record : records) {
		    LocationStats locationStat = new LocationStats();
		    locationStat.setState(record.get("Province/State"));
		    locationStat.setCountry(record.get("Country/Region"));
		    locationStat.setLatestTotalCases(Integer.parseInt(record.get(record.size() - 1)));//Last column (Latest date)
		    System.out.println(locationStat);
		    newStats.add(locationStat);
		}
		this.allStats = newStats;
		
		inp.close();
	}

	public List<LocationStats> getAllStats() {
		return allStats;
	}
}
