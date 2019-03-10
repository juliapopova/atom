package ru.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testBulls_3() {
        String gameWord = "abaka";
        String gamePlayer = "aaaaa";
        int countGame = Game.countBulls(gameWord, gamePlayer);
        int countTrue = 3;
        assertEquals(countGame, countTrue);
    }

    @Test
    public void testBulls_0() {
        String gameWord = "abaka";
        String gamePlayer = "lllll";
        int countGame = Game.countBulls(gameWord, gamePlayer);
        int countTrue = 0;
        assertEquals(countGame, countTrue);
    }

    @Test
    public void testCows_0() {
        String gameWord = "abaka";
        String gamePlayer = "aaaaa";
        int countGame = Game.countCows(gameWord, gamePlayer);
        int countTrue = 0;
        assertEquals(countGame, countTrue);
    }

    @Test
    public void testCows_5() {
        String gameWord = "vlrte";
        String gamePlayer = "lvert";
        int countGame = Game.countCows(gameWord, gamePlayer);
        int countTrue = 5;
        assertEquals(countGame, countTrue);
    }

}