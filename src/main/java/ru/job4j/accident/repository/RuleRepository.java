package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface RuleRepository extends CrudRepository<Rule, Integer> {

    @Override
    List<Rule> findAll();
}
