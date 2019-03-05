package ru.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Game {

    public static void main (String[] argc)
    {
        System.out.println("Welcome to Bulls and game!");
        List<String> dictionary = new ArrayList<String>();


        try {
            File file = new File("C:\\Users\\а\\Documents\\GitHub\\atom\\homeworks\\HW2\\dictionary");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            dictionary.add(0) = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
