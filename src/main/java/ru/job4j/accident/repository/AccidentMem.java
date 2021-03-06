package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public AccidentMem() {
        accidentTypes.put(1, AccidentType.of(1, "Car & Car"));
        accidentTypes.put(2, AccidentType.of(2, "Car & Man"));
        accidentTypes.put(3, AccidentType.of(3, "Car & Bicycle"));
        rules.put(1, Rule.of(1, "Rule 1"));
        rules.put(2, Rule.of(2, "Rule 2"));
        rules.put(3, Rule.of(3, "Rule 3"));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void create(Accident accident) {
        var newId = id.incrementAndGet();
        accident.setId(newId);
        accidents.put(newId, accident);
    }

    public void save(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public Accident getByID(int id) {
        return accidents.get(id);
    }

    public Collection<AccidentType> getAccidentTypes() {
        return accidentTypes.values();
    }

    public AccidentType getAccidentTypeById(int id) {
        return accidentTypes.get(id);
    }

    public Collection<Rule> getRules() {
        return rules.values();
    }

    public Rule getRuleByID(int id) {
        return rules.get(id);
    }
}
