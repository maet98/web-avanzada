package edu.pucmm.Practica1.Services;


import edu.pucmm.Practica1.Entity.Student;
import edu.pucmm.Practica1.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {

    @Autowired
    private StudentRepository studentRepository;

    private boolean existById(Integer id) {
        return studentRepository.existsById(id);
    }

    @Transactional
    public Student StudentCreate(Student student) {
        studentRepository.save(student);
        return student;
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Integer matricula) throws Exception {
        if(!existById(matricula)){
            throw new Exception("Cannot find Student with id:" + matricula);
        }
        studentRepository.deleteById(matricula);
    }

    public Student findById(Integer matricula) {
        return studentRepository.findById(matricula).orElse(null);
    }

    public void Update(Integer matricula, Student student) {
        studentRepository.save(student);
    }
}
