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

    public static void main(String[] argc) {
        System.out.println("Welcome to Bulls and Cows game!");
        mainGame();
    }

    public static void mainGame() {
        while (true) {
            String wordGame = newWord();
            System.out.println(wordGame);
            System.out.println("The word have " + wordGame.length() + " letters");
            System.out.println("Good luck!");
            Scanner in = new Scanner(System.in);
            System.out.println("Enter your word: ");
            String wordPlayer = in.nextLine();
            while (wordPlayer.length() != wordGame.length()) {
                System.out.println("Enter word again!");
                wordPlayer = in.nextLine();
            }
            int bulls = countBulls(wordPlayer, wordGame);
//        int cows = countCows(wordPlayer, wordGame);
//        System.out.println("Count bulls is " + bulls + ". Count cows is " + cows);
        }

    }

    public static String newWord() {

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

/*    public static int countBulls(String wordPlayer, String wordGame) {
        int countB = 0, countC = 0;
        boolean flag;
        char letter;
        for (int i = 0; i < wordGame.length(); i++) {
            letter = wordPlayer.charAt(i);
            if (wordGame.indexOf(letter) != -1) {
                if (wordGame.charAt(i) == wordPlayer.charAt(i))
                    countB++;
                else {
                    flag = true;
                    for (int j = i; j < wordGame.length(); j++) {
                        if (wordPlayer.charAt(i) == wordGame.charAt(j)) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag)
                        countC++;
                }
            }


        }

        System.out.println("Count bulls is " + countB + ". Count cows is " + countC);
        return 0;
    }

    public static int countCows (String wordPlayer, String wordGame)
    {
        char letter;
        int count = 0;
        for (int i = 0; i < wordPlayer.length(); i++)
        {
            letter = wordPlayer.charAt(i);
            if (wordGame.indexOf(letter) != -1)
                for (int j = 0; j < wordGame.length(); j++)
                {

                }
        }
        return count;
    }*/
}
