package me.aroze.chatminigames.utils;

import com.google.common.primitives.Chars;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;

import static me.aroze.chatminigames.ChatMinigames.instance;

public class WordUtils {

    public static String shuffleString(String text) {
        List<Character> chars = Chars.asList(text.toCharArray());
        Collections.shuffle(chars);
        return StringUtils.join(chars.stream().toArray());
    }

    public static String getRandomWord() {
        List<String> wordList = instance.getConfig().getStringList("wordList");
        return instance.getConfig().getStringList("wordList").get((int) Math.floor(Math.random() * wordList.size()));
    }

}
