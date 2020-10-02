package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.entities.User;
import edu.pucmm.Practica2.repositories.UserRepository;
import edu.pucmm.Practica2.utils.ImageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user, MultipartFile image) throws IOException {
        user.setImage(ImageProcessor.byteToString(image));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
