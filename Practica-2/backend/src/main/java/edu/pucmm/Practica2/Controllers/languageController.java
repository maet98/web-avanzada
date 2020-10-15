package edu.pucmm.Practica2.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/lang")
@CrossOrigin
@RestController
public class languageController {

    @GetMapping
    public String getData(HttpServletRequest request) {
        String lang = request.getHeader("Accept-Language");
        StringBuffer ans = new StringBuffer();
        ans.append(lang.charAt(0));
        ans.append(lang.charAt(1));
        return ans.toString();
    }
}
