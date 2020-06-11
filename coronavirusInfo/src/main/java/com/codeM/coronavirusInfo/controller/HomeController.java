package com.codeM.coronavirusInfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codeM.coronavirusInfo.model.EstadisticasRegion;
import com.codeM.coronavirusInfo.servicios.CovidDataService;

@Controller
public class HomeController {
	
	@Autowired
    CovidDataService covidDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<EstadisticasRegion> statsTotales = covidDataService.getTotalStats();
        int totalCasosRepor = statsTotales.stream().mapToInt(stat -> stat.getUltimosCasos()).sum();
        int totalNuevosCasos = statsTotales.stream().mapToInt(stat -> stat.getDiferenciaDiaria()).sum();
        model.addAttribute("statsTotales", statsTotales);
        model.addAttribute("totalCasosRepor", totalCasosRepor);
        model.addAttribute("totalNuevosCasos", totalNuevosCasos);
        return "home";
    }


}
