package com.assignment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController
@RequestMapping("api/v1/classifier")
public class CategoryController {



    @Autowired
    MedicalCategoryClassifier medicalCategoryClassifier;
    @GetMapping("/category")
    public String getCategory(@RequestBody String description) {
//        return description;
        return medicalCategoryClassifier.classifyMedicalCategory(description);
    }

}
