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
    List<GoldMedal> findByNameOrderByYearAsc(String name);
    List<GoldMedal> findByNameOrderByYearDesc(String name);

}
