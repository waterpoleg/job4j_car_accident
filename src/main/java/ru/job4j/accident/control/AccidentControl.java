package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentServiceData;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentServiceData service;

    public AccidentControl(AccidentServiceData accidentService) {
        this.service = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getAllAccidentTypes());
        model.addAttribute("rules", service.getRules());
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.getByID(id));
        model.addAttribute("types", service.getAllAccidentTypes());
        model.addAttribute("rules", service.getRules());
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int typeId,
                       HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        service.save(accident, typeId, ids);
        return "redirect:/";
    }
}
