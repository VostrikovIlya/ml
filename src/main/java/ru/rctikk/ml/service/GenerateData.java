package ru.rctikk.ml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rctikk.ml.entity.Button;
import ru.rctikk.ml.repo.WordsRepo;

import java.util.List;
import java.util.Random;

@Service
public class GenerateData {
    @Autowired
    private ButtonService service;

    @Autowired
    private WordsRepo wordsRepo;
    private Random random = new Random();

    public void generateButton(int sizeData) {
        List<String> tags = List.of("button", "span", "a", "input", "div");
        List<String> types = List.of("submit", "button", "reset");
        List<String> classnames = wordsRepo.findAllName();
        for (int i = 0; i < sizeData; i++) {
            String tag = tags.stream().findAny().get();
            String type = tag.equals("input") ? types.stream().findAny().get() : null;
            String classname = classnames.stream().findAny().get();
            boolean isBorder = random.nextFloat(1) < 0.7;
            boolean onClick = !tag.equals("a") && random.nextFloat(1) < 0.8;
            boolean isButton = true;
            service.save(new Button(tag,  classname, type, onClick, isBorder, isButton));
        }
    }
}
