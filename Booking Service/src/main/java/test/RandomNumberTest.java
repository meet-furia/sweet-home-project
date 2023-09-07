package test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class RandomNumberTest {
    public static void main(String[] args) {
        String s = String.valueOf(getRandomNumbers(5));
        System.out.println(s);
        ArrayList arrayList = (getRandomNumbers(5));
        String s1 = getStringRandomNumbers(arrayList);
        System.out.println(s1);
        Date d1 = new Date(2023,07,25);
        Date d2 = new Date(2023,07,28);
        int numberOfDays = calculatingTotalNumberOfDays(d1, d2);
        System.out.println(numberOfDays);

        System.out.println(new Date().toString());
    }

    private static ArrayList<String> getRandomNumbers(int count) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }

    private static String getStringRandomNumbers(ArrayList<String> arrayList) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            s.append(arrayList.get(i));
            if (i < arrayList.size() - 1) {
                s.append(",");
            }
        }
        return s.toString();
    }

    private static int calculatingTotalNumberOfDays(Date fromDate, Date toDate) {
        // Calculate the number of days between the dates
        return (int) ChronoUnit.DAYS.between(fromDate.toInstant(), toDate.toInstant());
    }
}