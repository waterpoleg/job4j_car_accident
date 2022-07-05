package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ThreadSafe
@Service
public class AccidentServiceData {

    private final AccidentRepository accidentStore;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentServiceData(AccidentRepository accidentStore,
                               AccidentTypeRepository accidentTypeRepository,
                               RuleRepository ruleRepository) {
        this.accidentStore = accidentStore;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    @Transactional
    public Collection<Accident> getAllAccidents() {
        return accidentStore.findAll();
    }

    @Transactional
    public void save(Accident accident, int typeId, String[] ids) {
        accident.setType(accidentTypeRepository.findById(typeId).get());
        if (ids == null) {
            accident.setRules(new HashSet<>());
        } else {
            Set<Rule> rules = Arrays.stream(ids)
                    .map(id -> ruleRepository.findById(Integer.parseInt(id)).get())
                    .collect(Collectors.toSet());
            accident.setRules(rules);
        }
        accidentStore.save(accident);
    }

    @Transactional
    public Accident getByID(int id) {
        return accidentStore.findById(id).get();
    }

    @Transactional
    public Collection<AccidentType> getAllAccidentTypes() {
        return accidentTypeRepository.findAll();
    }

    @Transactional
    public Collection<Rule> getRules() {
        return ruleRepository.findAll();
    }
}
