package edu.pucmm.Practica2.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class ImageProcessor {

    public static String byteToString(MultipartFile image) throws IOException {
        return "data:"+ image.getContentType() +";base64," +  Base64.getEncoder().encodeToString(image.getBytes());
    }

}
