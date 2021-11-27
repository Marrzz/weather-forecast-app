package helpers;

import exception.WrongFileFormatException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MyFileReader {

    public List<String> readFile(String fileName) throws FileNotFoundException, WrongFileFormatException {

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            String extension = fileName.substring(i + 1);

            if (!extension.equals("txt")) {
                throw new WrongFileFormatException();
            }
        }
        File file = new File(fileName);


        List<String> cities = new LinkedList<>();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String city = scanner.next().trim();
                cities.add(city);

            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File Not found! Check the file name and extension, only txt files are accepted " +
                    "and the file must be in root folder!");
        }


        return cities;
    }

    ;

    public String readForecastFile(String fileName) {
        File file = new File(fileName);

        String result = "";

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                result += scanner.next();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
