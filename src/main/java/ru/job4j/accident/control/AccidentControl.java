package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
public class AccidentControl {

    private final AccidentService service;

    public AccidentControl(AccidentService accidentService) {
        this.service = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getAllAccidentTypes());
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.getByID(id));
        model.addAttribute("types", service.getAllAccidentTypes());
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int typeId) {
        service.save(accident, typeId);
        return "redirect:/";
    }
}
