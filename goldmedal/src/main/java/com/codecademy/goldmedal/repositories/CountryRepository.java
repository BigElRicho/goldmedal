package com.codecademy.goldmedal.repositories;

import com.codecademy.goldmedal.model.Country;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Long> {
    Optional<Country> findById(Long id);
    Optional<Country> findByName(String name);
    Optional<Country> findByCode(String code);
    Optional<Country> findByGdp(BigDecimal gdp);
    Optional<Country> findByPopulation(Integer population);
    // When using findAll with additional constraints, add a By before the constraints.
    List<Country> findAllByOrderByName();
    List<Country> findAllByOrderByNameDesc();
//    List<Country> findAllOrderByCodeAsc();
//    List<Country> findAllOrderByCodeDesc();
    List<Country> findAllByOrderByGdp();
    List<Country> findAllByOrderByGdpDesc();
    List<Country> findAllByOrderByPopulation();
    List<Country> findAllByOrderByPopulationDesc();

}
