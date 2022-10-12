package me.aroze.chatminigames.utils;

public class MiscUtils {

    public static String makeTimestamp(double milliseconds) {
        StringBuilder timestamp = new StringBuilder();

        double seconds = java.lang.Math.round(milliseconds / 10) / 100.0; // this rounds to 2dp, along with converting to seconds
        int minutes = (int) ((milliseconds / 1000) / 60);

        if (minutes == 1) {
            seconds -= 60;
            seconds = java.lang.Math.round(seconds * 100) / 100.0; // Re-round when subtracting seconds because floating point rounding shitty shit ;-;
            timestamp.append("1 minute and ");
        }

        else if (minutes > 0) {
            seconds -= 60 * minutes;
            seconds = java.lang.Math.round(seconds * 100) / 100.0; // Re-round when subtracting seconds because floating point rounding shitty shit ;-;
            timestamp.append(minutes).append(" minutes and ");
        }

        timestamp.append(seconds).append(" seconds");

        return timestamp.toString();
    }

}
