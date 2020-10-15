package edu.pucmm.Practica3.Controllers;

import edu.pucmm.Practica3.DTO.ValorationCreationDTO;
import edu.pucmm.Practica3.Entities.Question;
import edu.pucmm.Practica3.Entities.Valoration;
import edu.pucmm.Practica3.Repository.QuestionRepository;
import edu.pucmm.Practica3.Services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@Controller
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SurveyService surveyService;

    @GetMapping
    public String questionary(Model model) {
        ValorationCreationDTO valorationCreationDTO = new ValorationCreationDTO();
        for( Question question: questionRepository.findAll()){
            Valoration valoration = new Valoration();
            valoration.setQuestion(question);
            valorationCreationDTO.addValoration(valoration);
        }
        model.addAttribute("form", valorationCreationDTO);
        return "Questions";
    }

    @PostMapping(value = "survey")
    public String PostSurvey(@ModelAttribute ValorationCreationDTO form, Principal principal){
        surveyService.postQuestionary(form.getValorations(), principal.getName());
        return "redirect:/thanks";
    }

    @GetMapping("thanks")
    public String afterQuestionary(Model model) {
        return "Thanks";
    }

}
