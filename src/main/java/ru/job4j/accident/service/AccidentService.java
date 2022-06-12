package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@ThreadSafe
@Service
public class AccidentService {

    private final AccidentMem accidentStore;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentStore = accidentMem;
    }

    public Collection<Accident> getAllAccidents() {
        return accidentStore.getAccidents();
    }
}
