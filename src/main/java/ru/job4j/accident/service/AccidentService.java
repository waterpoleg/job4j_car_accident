package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ThreadSafe
@Service
public class AccidentService {

    private final AccidentJdbcTemplate accidentStore;

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentStore = accidentJdbcTemplate;
    }

    public Collection<Accident> getAllAccidents() {
        return accidentStore.getAccidents();
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accidentStore.create(accident);
        } else {
            accidentStore.save(accident);
        }
    }

    public void save(Accident accident, int typeId, String[] ids) {
        accident.setType(accidentStore.getAccidentTypeById(typeId));
        if (ids == null) {
            accident.setRules(new HashSet<>());
        } else {
            Set<Rule> rules = Arrays.stream(ids)
                    .map(id -> accidentStore.getRuleByID(Integer.parseInt(id)))
                    .collect(Collectors.toSet());
            accident.setRules(rules);
        }
        if (accident.getId() == 0) {
            accidentStore.create(accident);
        } else {
            accidentStore.save(accident);
        }
    }

    public Accident getByID(int id) {
        return accidentStore.getByID(id);
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        return accidentStore.getAccidentTypes();
    }

    public Collection<Rule> getRules() {
        return accidentStore.getRules();
    }
}
