package edu.pucmm.Practica1.Controllers;

import edu.pucmm.Practica1.Entity.Student;
import edu.pucmm.Practica1.Services.StudentServices;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * Created by vacax on 21/09/16.
 */
@Controller()
@RequestMapping("/student")
public class StudentController {

    //Inyección de dependencia para la internacionalización
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private StudentServices studentServices;

    @RequestMapping("/create")
    public String CreateStudent(Model model){
        Student student = new Student();
        model.addAttribute("add", true);
        model.addAttribute("student", student);

        return "createStudent";
    }

    @PostMapping("/create")
    public String addStudent(Model model,@ModelAttribute("student")Student student){
        Student new_student = studentServices.StudentCreate(student);
        return "redirect:/student";
    }

    @RequestMapping
    public String getAllStudent(Model model){
        List<Student> students = studentServices.getAllStudent();
        model.addAttribute("students", students);
        return "ListStudent";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Integer id, Model model) {
        try {
            studentServices.deleteStudent(id);
            return "redirect:/student";
        } catch (Exception err) {
            return "";
        }
    }

    @GetMapping("update/{studentId}")
    public String showEditStudent(Model model, @PathVariable("studentId") Integer studentId){
        Student student = null;
        try {
            student = studentServices.findById(studentId);
        } catch ( Exception ex) {
        }
        model.addAttribute("add", false);
        model.addAttribute("student",student);
        return "createStudent";
    }

    @PostMapping("update/{studentId}")
    public String updateNote(Model model, @PathVariable Integer studentId, @ModelAttribute("student") Student student){
        try {
            student.setMatricula(studentId);
            studentServices.Update(studentId,student);
            return "redirect:/student";
        } catch (Exception err) {
            return "redirect:/student";
        }
    }
}
