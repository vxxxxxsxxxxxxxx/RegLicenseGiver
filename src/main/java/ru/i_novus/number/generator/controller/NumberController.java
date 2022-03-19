package ru.i_novus.number.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.i_novus.number.generator.service.NumberService;

@RestController
@RequestMapping("/number")
public class NumberController {

    @Autowired
    NumberService numberService;

    @GetMapping("/random")
    public String random() {
        return numberService.random();
    }

    @GetMapping("/next")
    public String next() {
        return numberService.next();
    }

    @GetMapping("/prev")
    public String prev() {
        return numberService.prev();
    }

    @GetMapping("/debug/restart")
    public String restart() {
        return "Restarted.\n" + numberService.restart();
    }

    @GetMapping("/debug/start")
    public String toStart() {
        return numberService.setToStart();
    }

    @GetMapping("/debug/end")
    public String toEnd() {
        return numberService.setToEnd();
    }

    @GetMapping("/debug/set/chars/start")
    public String toStartChars() {
        return numberService.setCharsToStart();
    }

    @GetMapping("/debug/set/chars/end")
    public String toEndChars() {
        return numberService.setCharsToEnd();
    }

    @GetMapping("/debug/set/nums/start")
    public String toStartNums() {
        return numberService.setNumsToStart();
    }

    @GetMapping("/debug/set/nums/end")
    public String toEndNums() {
        return numberService.setNumsToEnd();
    }

    @GetMapping("/debug/presence")
    public String presence() {
        if (numberService.ExistsInBD()){
            return "Is in BD.";
        } else {
            return "Isn't in BD.";
        }
    }

    @GetMapping("/debug/silent/next")
    public String silentNext() {
        return numberService.silentNext();
    }

    @GetMapping("/debug/silent/prev")
    public String silentPrev() {
        return numberService.silentPrev();
    }

    @GetMapping("/debug/savecurrent")
    public String saveCurrentNumberToDB() {
        return numberService.SaveCurrentNumberToDB();
    }

    @GetMapping("/debug/showcurrent")
    public String getCurrentNumber() {
        // Should be private if isn't used in debugging
        return numberService.GetCurrentFullNumber();
    }

}
