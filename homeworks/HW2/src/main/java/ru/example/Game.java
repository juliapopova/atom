package ru.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Game {

    public static void main (String[] argc)
    {
        System.out.println("Welcome to Bulls and Cows game!");
        String wordGame = newWord();
        Scanner in = new Scanner(System.in);
        System.out.println(wordGame);
        System.out.println("The word have " + wordGame.length() + " letters");
        System.out.println("Good luck!");
    }

    public static String newWord ()
    {

        ArrayList<String> dictionary = new ArrayList<String>();
        String line;

        try {
            File file = new File("dictionary.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            line = reader.readLine();
            while (line != null) {
                dictionary.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int randIndex = ThreadLocalRandom.current().nextInt(0, dictionary.size());
        return dictionary.get(randIndex);
    }

    public static int countBulls (String wordPlayer, String wordGame)
    {
        int count = 0;
        for (int i = 0; i < wordGame.length(); i++)
            if (wordGame.charAt(i) == wordPlayer.charAt(i))
                count++;

        return count;
    }
}
