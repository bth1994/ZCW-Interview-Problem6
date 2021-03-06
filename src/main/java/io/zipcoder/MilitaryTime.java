package io.zipcoder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MilitaryTime {

    private static String[] timeWords = {
            "Zero",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen",
    };

    private static String[] tensWords = {
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty"
    };

    public String convertToMilitaryTime(String time) throws Exception{
        time = convertNumerically(time);
        String[] splitTime = time.split(":");
        Integer hours = Integer.parseInt(splitTime[0]);
        Integer minutes = Integer.parseInt(splitTime[1]);

        String output = new StringBuilder()
                .append(convertHours(hours))
                .append(convertMinutes(minutes))
                .toString();

        return output;
    }

    public String convertHours(Integer hours) {
        StringBuilder output = new StringBuilder();
        if(hours >= 10 && hours < 20) {
            output.append(timeWords[hours] + " Hundred and ");
        } else if(hours < 10) {
            output.append(timeWords[hours / 10]);
            output.append(" ");
            output.append(timeWords[hours % 10] + " Hundred and ");
        } else if(hours > 19) {
            output.append(tensWords[(hours / 10) - 2]);
            output.append(" ");
            output.append(timeWords[hours % 10] + " Hundred and ");
        }
        return output.toString();
    }

    public String convertMinutes(Integer minutes) {
        StringBuilder output = new StringBuilder();
        if(minutes > 10 && minutes < 20) {
            output.append(timeWords[minutes] + " Hours");
        } else if(minutes < 10) {
            output.append(timeWords[minutes / 10]);
            output.append(" ");
            output.append(timeWords[minutes % 10] + " Hours");
        } else if(minutes > 19) {
            output.append(tensWords[(minutes / 10) - 2]);
            output.append(" ");
            if(minutes % 10 != 0) {
                output.append(timeWords[minutes % 10]);
                output.append(" ");
            }
            output.append("Hours");
        }
        return output.toString();
    }

    public String convertNumerically(String time) throws Exception {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = parseFormat.parse(formatTime(time));
        return displayFormat.format(date);
    }

    public String formatTime(String time) {
        String[] splitTime = time.split("");
        for(int i = splitTime.length - 1; i > splitTime.length - 4; i--) {
            if(i == splitTime.length - 3) {
                splitTime[i] = splitTime[i] + " ";
            }
            splitTime[i] = splitTime[i].toUpperCase();
        }
        return String.join("", splitTime);
    }
}
