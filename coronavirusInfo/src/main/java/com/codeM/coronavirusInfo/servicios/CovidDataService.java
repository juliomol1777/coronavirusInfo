package com.codeM.coronavirusInfo.servicios;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.codeM.coronavirusInfo.model.EstadisticasRegion;

@Service
public class CovidDataService {

	private static String VIRUS_CASOS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<EstadisticasRegion> totalStadist = new ArrayList<>();
	
	public List<EstadisticasRegion> getTotalStats() {
		return totalStadist;
	}
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void virusDatos() throws IOException, InterruptedException {
		List<EstadisticasRegion> nuevasEstadis = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_CASOS_URL)).build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		for (CSVRecord record : records) {
			EstadisticasRegion estadisRegion = new EstadisticasRegion();
			estadisRegion.setEstado(record.get("Province/State"));
			estadisRegion.setPais(record.get("Country/Region"));
			int ultimosCasos = Integer.parseInt(record.get(record.size() - 1));
			int casosPrevios = Integer.parseInt(record.get(record.size() - 2));
			estadisRegion.setUltimosCasos(ultimosCasos);
			estadisRegion.setDiferenciaDiaria(ultimosCasos - casosPrevios);
			nuevasEstadis.add(estadisRegion);	
		
		}
		this.totalStadist = nuevasEstadis;
	}

}
