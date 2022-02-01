package com.codecademy.goldmedal.repositories;

import com.codecademy.goldmedal.model.GoldMedal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GoldMedalRepository extends CrudRepository<GoldMedal, Long> {
    Optional<GoldMedal> findById(Long id);
    List<GoldMedal> findByYear(Integer year);
    List<GoldMedal> findByCity(String city);
    List<GoldMedal> findBySeason(String season);
    List<GoldMedal> findByName(String name);
    List<GoldMedal> findByCountry(String country);
    List<GoldMedal> findByGender(String gender);
    List<GoldMedal> findBySport(String sport);
    List<GoldMedal> findByDiscipline(String discipline);
    List<GoldMedal> findByEvent(String event);
    List<GoldMedal> findByCountryOrderByYearAsc(String country);
    List<GoldMedal> findByCountryOrderByYearDesc(String country);
    List<GoldMedal> findByCountryOrderBySeasonAsc(String country);
    List<GoldMedal> findByCountryOrderBySeasonDesc(String country);
    List<GoldMedal> findByCountryOrderByCityAsc(String country);
    List<GoldMedal> findByCountryOrderByCityDesc(String country);
    List<GoldMedal> findByCountryOrderByNameAsc(String country);
    List<GoldMedal> findByCountryOrderByNameDesc(String country);
    List<GoldMedal> findByCountryOrderByEventAsc(String country);
    List<GoldMedal> findByCountryOrderByEventDesc(String country);
    List<GoldMedal> findBySeasonOrderByYearAsc(String season);
    List<GoldMedal> findBySeasonOrderByYearDesc(String season);

}
