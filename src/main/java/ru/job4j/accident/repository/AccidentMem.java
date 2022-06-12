package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(
                1, "name 1", "text 1", "address 1"
        ));
        accidents.put(2, new Accident(
                2, "name 2", "text 2", "address 2"
        ));
        accidents.put(3, new Accident(
                3, "name 3", "text 3", "address 3"
        ));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }
}
