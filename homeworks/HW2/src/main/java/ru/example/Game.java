package ru.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

import org.slf4j.LoggerFactory;

public class Game {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Game.class);

    public static void main(String[] argc) {
        log.info("Game started");
        System.out.println("Welcome to Bulls and Cows game!");
        mainGame();
    }

    public static void mainGame() {
        char choose;
        do {
            String wordGame = newWord();
            log.info("Get word " + wordGame);
            System.out.println("The word have " + wordGame.length() + " letters");
            System.out.println("You have 10 attempts");
            System.out.println("Good luck!");
            Scanner in = new Scanner(System.in);
            int bulls;
            int count = 0;
            do {
                System.out.println("Enter your word: ");
                String wordPlayer = in.nextLine();
                while (wordPlayer.length() != wordGame.length()) {
                    log.info("Enter is wrong");
                    System.out.println("Enter word again!");
                    wordPlayer = in.nextLine();
                }
                bulls = countBulls(wordPlayer, wordGame);
                int cows = countCows(wordPlayer, wordGame);
                if (bulls == wordGame.length()) {
                    log.info("Player win");
                    System.out.println("You win!!!");
                } else
                    System.out.println("Count bulls is " + bulls + ". Count cows is " + cows);
                count++;
            } while (bulls != wordGame.length() && count != 10);
            if (count == 10) {
                log.info("Player loose");
                System.out.println("You loose! :( exexexexe");
                System.out.println("Right word is " + wordGame);
            }


            System.out.println("Repeat? (y/n): ");
            choose = in.next().charAt(0);
        } while (choose == 'y');
        log.info("Game over");
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


    public static int countBulls(String wordPlayer, String wordGame) {
        int count = 0;
        for (int i = 0; i < wordGame.length(); i++) {
            if (wordGame.charAt(i) == wordPlayer.charAt(i))
                count++;
        }
        log.info("Player's word have " + count + " bulls");
        return count;
    }

    public static int countCows(String wordPlayer, String wordGame) {
        char letter;
        int count = 0;
        boolean[] flagGame;
        boolean[] flagPlayer;

        flagGame = new boolean[wordGame.length()];
        flagPlayer = new boolean[wordPlayer.length()];
        for (int i = 0; i < wordGame.length(); i++) {
            if (wordGame.charAt(i) == wordPlayer.charAt(i)) {
                flagGame[i] = false;
                flagPlayer[i] = false;
            } else {
                flagGame[i] = true;
                flagPlayer[i] = true;
            }

        }
        for (int i = 0; i < wordPlayer.length(); i++) {
            letter = wordPlayer.charAt(i);
            if (wordGame.indexOf(letter) != -1 && flagPlayer[i]) {
                for (int j = 0; j < wordGame.length(); j++) {
                    if (letter == wordGame.charAt(j) && flagGame[j]) {
                        flagPlayer[i] = false;
                        flagGame[j] = false;
                        count++;
                        break;
                    }
                }
            }
        }
        log.info("Player's word have " + count + " cows");
        return count;
    }
}
