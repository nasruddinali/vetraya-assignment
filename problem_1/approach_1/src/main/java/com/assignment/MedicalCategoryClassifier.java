package com.assignment;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Service
public class MedicalCategoryClassifier {

    private Map<String,String[]> categoryMap = new HashMap<>();

    public MedicalCategoryClassifier() {
        initializeHashMap();
    }


    public void initializeHashMap() {

        categoryMap.put("Ambulance charges", new String[]{"ambulance"});
        categoryMap.put("Anesthesia", new String[]{"anaesthesia","isoflurane","laryngeal","adrenaline","suprane","desflurane","baxter"});
        categoryMap.put("Blood & Blood Products",new String[]{"blood","cell","plasma","red","leuko","leukoreduced","leucodepleted","leukocyte","leukocytedepleted"});
        categoryMap.put("Doctor Fee", new String[]{"dr.","surgeon","consultation","physician","dietician","assessment","consultant","doctor","nutritionist","paediatrics","opthalmology","neurology","consultation","specialist","routine","medicine"});
        categoryMap.put("Food & Beverages", new String[]{"chai", "coffee","meal","dosa","diet","beverages","apple","juice","idly","water","coconut","soup","chapati","curry","kichdi","fruit","salad","milk","wada","utpam","biryani","chicken","plate","mutton","fried","rice","bajji","eggs"});
        categoryMap.put("Medicines & Consumables", new String[]{"iv","mg", "inj","injection","ml","inj.","gloves","zokent","syp","syrup","consumables","diapers","vicryl","venflon","vein","syringe","surgical","glove","sponge","needle","cap","saline","sterile","microshield","pvp","iv","caps","microoptic","ecg","sheet","bandage","handrub","vacutainer","pad","surgical","sterile","ml","molnl","diapers","pads","needles",});
        categoryMap.put("Room Rent", new String[]{"private","semi","daycare","bed","single","double","room","rmo","sharing","ward","bedded","preparation","recovery"});
        categoryMap.put("Procedure Charges", new String[]{"cystoscopy","diagnostic","stabilization","druj","orif","colonoscopy","coronary","angiog","ptca","hernioplasty","lap","lap.","laparoscopic","cystic","adhesiolysis","cholecystectomy","cholecystectomy","cataract","shoulder","humerous","adenoidectomy","replacement","knee","ventral","scar","reconstruction","angioplasty","guidewire","angiography","ptca","caronary","translimin"});


    }


    public String classifyMedicalCategory(String inputText) {
        // Convert input text to lowercase for case-insensitive matching
        String lowerCaseInput = inputText.toLowerCase();
        String[] words = lowerCaseInput.split("[ -()@(0-9)]");

        // Iterate through categories and check for keyword matches
        for ( String  word : words){
            for (Map.Entry<String, String[]> entry : categoryMap.entrySet()) {
                String category = entry.getKey();
                String[] keywords = entry.getValue();

                for (String keyword : keywords) {
                    if (word.contains(keyword.toLowerCase())) {
                        return category;
                    }
                }
            }
        }
        return "unknown category";
    }
}
