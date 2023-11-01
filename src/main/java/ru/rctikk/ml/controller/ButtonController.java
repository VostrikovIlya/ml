package ru.rctikk.ml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rctikk.ml.entity.Button;
import ru.rctikk.ml.service.ButtonService;

import java.util.Objects;

@RestController
@RequestMapping("/api/Button")
public class ButtonController {

    private final ButtonService buttonService;
    @Autowired
    public ButtonController(ButtonService buttonService) {
        this.buttonService = buttonService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addData(@RequestBody Button button) {
        button.computeHash();
        Button saveButton = buttonService.save(button);
        if(saveButton.equals(button))
            return ResponseEntity.ok("Save: " + saveButton);
        else
            return ResponseEntity.internalServerError().body("Not save");
    }
}
