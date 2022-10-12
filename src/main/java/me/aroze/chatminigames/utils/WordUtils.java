package me.aroze.chatminigames.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.aroze.chatminigames.ChatMinigames.instance;

public class WordUtils {

    public static String shuffleString(String text) {
        List<String> chars = Arrays.asList(text.split(""));
        Collections.shuffle(chars);
        return String.join("", chars);
    }

    public static String getRandomWord() {
        List<String> wordList = instance.getConfig().getStringList("wordList");
        return instance.getConfig().getStringList("wordList").get((int) Math.floor(Math.random() * wordList.size()));
    }

    public static String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        while (result.length() < 20) result.append(characters.charAt((int) (Math.random() * characters.length())));
        return result.toString();
    }

}
