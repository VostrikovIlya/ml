package ru.rctikk.ml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rctikk.ml.repo.ButtonRepo;
import ru.rctikk.ml.entity.Button;

import java.util.List;

@Service
public class ButtonService {

    private final ButtonRepo buttonRepo;
    @Autowired
    public ButtonService(ButtonRepo buttonRepo) {
        this.buttonRepo = buttonRepo;
    }

    public Button save(Button button) {
        if(findButtonByHash(button.getHash()))
            return button;
        return buttonRepo.save(button);
    }

    public List<Button> getDateSet() {
        return buttonRepo.findAll();
    }

    public boolean findButtonByHash(String hash) {
        return buttonRepo.findButtonByHash(hash).isPresent();
    }

}
