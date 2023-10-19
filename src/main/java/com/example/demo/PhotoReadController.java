package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("photo")
public class PhotoReadController {
    @Autowired
    private PhotoReadService photoReadService;

    @PostMapping
    public String readPhoto(@RequestPart MultipartFile file) throws Exception {
        Map<String, Double> result = photoReadService.readExifAndReturnGPS(file);

        return result.toString();
    }
}
