package com.codecademy.goldmedal.repositories;

import org.springframework.data.repository.CrudRepository;
import com.codecademy.goldmedal.model.GoldMedal;

public interface GoldMedalRepository extends CrudRepository<GoldMedal,id>{

}