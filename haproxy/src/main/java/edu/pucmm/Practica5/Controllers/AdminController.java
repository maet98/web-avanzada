package edu.pucmm.Practica5.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.pucmm.Practica5.Repository.QuestionRepository;
import edu.pucmm.Practica5.Services.ValorationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ValorationService valorationService;

    @Value("${server.port}")
    private String port;

    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping
    public String home(Model model) {
        model.addAttribute("dataList",valorationService.getAverage());
        model.addAttribute("questionComment", questionRepository.findQuestionByComment(true));
        model.addAttribute("port", this.port);
        return "Home";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        var dataList = valorationService.getData();
        var question = questionRepository.findAll();
        JsonArray jsonQuestion = new JsonArray();
        JsonArray jsonData = new JsonArray();
        JsonObject json = new JsonObject();

        question.forEach(question1 -> {
            jsonQuestion.add(question1.getValue());
        });
        for(int i = 0;i < dataList.size();i++) {
            JsonObject actual = new JsonObject();
            Integer id = i + 1;
            actual.addProperty("name",id);
            JsonArray array = new JsonArray();
            for(var ac: dataList.get(i).entrySet()){
                array.add(ac.getValue());
            }
            actual.add("data", array);
            jsonData.add(actual);
        }
        json.add("question", jsonQuestion);
        json.add("series",jsonData);
        return json.toString();
    }

}
