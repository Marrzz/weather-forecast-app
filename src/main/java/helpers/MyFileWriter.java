package helpers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class MyFileWriter {

    public void writeToFile(String json, String city) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s_%s.json", city, java.time.LocalDate.now())));
        writer.write(json);

        writer.close();


    }
}
