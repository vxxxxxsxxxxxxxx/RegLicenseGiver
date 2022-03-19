package ru.i_novus.number.generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.i_novus.number.generator.model.Number;

public interface NumberRepository extends JpaRepository<Number, Long>{

    public boolean existsByNumber(String number);

}
