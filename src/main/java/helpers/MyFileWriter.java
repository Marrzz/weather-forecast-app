package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MyFileWriter {

    public void writeToFile(String json, String city) throws IOException {


        File file = new File(String.format("%s_%s.json", city.replace(" ", "_"), java.time.LocalDate.now()));

        if (file.exists()){
            System.out.println("Forecast file for " + city +" found! Overriding file...");
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s_%s.json", city.replace(" ", "_"), java.time.LocalDate.now())));
        writer.write(json);

        writer.close();


    }
}
