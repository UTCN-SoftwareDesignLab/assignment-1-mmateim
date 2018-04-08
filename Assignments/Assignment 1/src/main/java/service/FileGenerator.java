package service;

import DTO.ActivityDTO;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileGenerator {

    private String fileName;
    private List<ActivityDTO> activities;

    public FileGenerator(String fileName, List<ActivityDTO> activities) {
        this.fileName = fileName;
        this.activities = activities;
    }

    public boolean generate(){
        Path file = Paths.get(fileName);
        List<String> lines = new ArrayList<>();
        for(ActivityDTO activity : activities){
            lines.add("date : " + activity.getDate() + " - " + activity.getDescription());
        }
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
