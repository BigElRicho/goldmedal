package com.codecademy.goldmedal.controller;

import com.codecademy.goldmedal.model.*;
import com.codecademy.goldmedal.repositories.CountryRepository;
import com.codecademy.goldmedal.repositories.GoldMedalRepository;
import org.apache.commons.text.WordUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/countries")
public class GoldMedalController {
    // declare references to your repositories
    private GoldMedalRepository goldMedalRepository;
    private CountryRepository countryRepository;

    // update your constructor to include your repositories
    public GoldMedalController(GoldMedalRepository goldMedalRepository, CountryRepository countryRepository) {
        this.goldMedalRepository = goldMedalRepository;
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public CountriesResponse getCountries(@RequestParam String sort_by, @RequestParam String ascending) {
        var ascendingOrder = ascending.toLowerCase().equals("y");
        return new CountriesResponse(getCountrySummaries(sort_by.toLowerCase(), ascendingOrder));
    }

    @GetMapping("/{country}")
    public CountryDetailsResponse getCountryDetails(@PathVariable String country) {
        String countryName = WordUtils.capitalizeFully(country);
        return getCountryDetailsResponse(countryName);
    }

    @GetMapping("/{country}/medals")
    public CountryMedalsListResponse getCountryMedalsList(@PathVariable String country, @RequestParam String sort_by, @RequestParam String ascending) {
        String countryName = WordUtils.capitalizeFully(country);
        var ascendingOrder = ascending.toLowerCase().equals("y");
        return getCountryMedalsListResponse(countryName, sort_by.toLowerCase(), ascendingOrder);
    }

    private CountryMedalsListResponse getCountryMedalsListResponse(String countryName, String sortBy, boolean ascendingOrder) {
        List<GoldMedal> medalsList;
        switch (sortBy) {
            // TODO: list of medals sorted by year in the given order
            case "year":
                if(ascendingOrder == true){
                    medalsList = this.goldMedalRepository.findByCountryOrderByYearAsc(countryName);
                }
                else{
                    medalsList = this.goldMedalRepository.findByCountryOrderByYearDesc(countryName);
                }
                break;
            case "season":
                // list of medals sorted by season in the given order
                if(ascendingOrder == true){
                    medalsList = this.goldMedalRepository.findByCountryOrderBySeasonAsc(countryName);
                }
                else{
                    medalsList = this.goldMedalRepository.findByCountryOrderBySeasonDesc(countryName);
                }
                break;
            case "city":
                // list of medals sorted by city in the given order
                if(ascendingOrder == true){
                    medalsList = this.goldMedalRepository.findByCountryOrderByCityAsc(countryName);
                }
                else{
                    medalsList = this.goldMedalRepository.findByCountryOrderByCityDesc(countryName);
                }
                break;
            case "name":
                // list of medals sorted by athlete's name in the given order
                if(ascendingOrder == true){
                    medalsList = this.goldMedalRepository.findByCountryOrderByNameAsc(countryName);
                }
                else{
                    medalsList = this.goldMedalRepository.findByCountryOrderByNameDesc(countryName);
                }
                break;
            case "event":
                // list of medals sorted by event in the given order
                if(ascendingOrder == true){
                    medalsList = this.goldMedalRepository.findByCountryOrderByEventAsc(countryName);
                }
                else{
                    medalsList = this.goldMedalRepository.findByCountryOrderByEventDesc(countryName);
                }
                break;
            default:
                medalsList = new ArrayList<>();
                break;
        }

        return new CountryMedalsListResponse(medalsList);
    }

    private CountryDetailsResponse getCountryDetailsResponse(String countryName) {
        // get the country; this repository method should return a java.util.Optional
        var countryOptional = this.countryRepository.findByName(countryName);
        if (countryOptional.isEmpty()) {
            return new CountryDetailsResponse(countryName);
        }

        var country = countryOptional.get();
        // get the medal count
        var goldMedalCount = this.goldMedalRepository.findByCountry(countryName).size();
        // get the collection of wins at the Summer Olympics, sorted by year in ascending order
        var summerWins = this.goldMedalRepository.findBySeasonOrderByYearAsc("Summer");
        var numberSummerWins = summerWins.size() > 0 ? summerWins.size() : null;
        // get the total number of events at the Summer Olympics
        var totalSummerEvents = this.goldMedalRepository.findBySeason("Summer").size();
        var percentageTotalSummerWins = totalSummerEvents != 0 && numberSummerWins != null ? (float) summerWins.size() / totalSummerEvents : null;
        var yearFirstSummerWin = summerWins.size() > 0 ? summerWins.get(0).getYear() : null;
        // get the collection of wins at the Winter Olympics
        var winterWins = this.goldMedalRepository.findBySeason("Winter");
        var numberWinterWins = winterWins.size() > 0 ? winterWins.size() : null;
        // get the total number of events at the Winter Olympics, sorted by year in ascending order
        var totalWinterEvents = this.goldMedalRepository.findBySeasonOrderByYearAsc("Winter").size();
        var percentageTotalWinterWins = totalWinterEvents != 0 && numberWinterWins != null ? (float) winterWins.size() / totalWinterEvents : null;
        var yearFirstWinterWin = winterWins.size() > 0 ? winterWins.get(0).getYear() : null;
        // get the number of wins by female athletes
        var numberEventsWonByFemaleAthletes = this.goldMedalRepository.findByGender("Women").size();
        // get the number of wins by male athletes
        var numberEventsWonByMaleAthletes = this.goldMedalRepository.findByGender("Men").size();

        return new CountryDetailsResponse(
                countryName,
                country.getGdp(),
                country.getPopulation(),
                goldMedalCount,
                numberSummerWins,
                percentageTotalSummerWins,
                yearFirstSummerWin,
                numberWinterWins,
                percentageTotalWinterWins,
                yearFirstWinterWin,
                numberEventsWonByFemaleAthletes,
                numberEventsWonByMaleAthletes);
    }

    private List<CountrySummary> getCountrySummaries(String sortBy, boolean ascendingOrder) {
        List<Country> countries;
        switch (sortBy) {
            case "name":
                countries = // TODO: list of countries sorted by name in the given order
                break;
            case "gdp":
                countries = // TODO: list of countries sorted by gdp in the given order
                break;
            case "population":
                countries = // TODO: list of countries sorted by population in the given order
                break;
            case "medals":
            default:
                countries = // TODO: list of countries in any order you choose; for sorting by medal count, additional logic below will handle that
                break;
        }

        var countrySummaries = getCountrySummariesWithMedalCount(countries);

        if (sortBy.equalsIgnoreCase("medals")) {
            countrySummaries = sortByMedalCount(countrySummaries, ascendingOrder);
        }

        return countrySummaries;
    }

    private List<CountrySummary> sortByMedalCount(List<CountrySummary> countrySummaries, boolean ascendingOrder) {
        return countrySummaries.stream()
                .sorted((t1, t2) -> ascendingOrder ?
                        t1.getMedals() - t2.getMedals() :
                        t2.getMedals() - t1.getMedals())
                .collect(Collectors.toList());
    }

    private List<CountrySummary> getCountrySummariesWithMedalCount(List<Country> countries) {
        List<CountrySummary> countrySummaries = new ArrayList<>();
        for (var country : countries) {
            var goldMedalCount = // TODO: get count of medals for the given country
            countrySummaries.add(new CountrySummary(country, goldMedalCount));
        }
        return countrySummaries;
    }
}
