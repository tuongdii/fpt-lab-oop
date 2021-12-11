
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Inputter {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final int MAX_ELEMENT =500;
    private static Scanner sc = new Scanner(System.in);
    
    public static int getPostiveAnInteger(String inputMsg, String errorMsg){  
        int n; 
        do{
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine()); 
                if(n<0){
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            }
        }while(true);
    }
    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            }
        }
    }

    public static String getID(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false)
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            else
                return id;            
        }
    }
    
    public static String getString(String inputMsg, String errorMsg) {
        String id;        
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();            
            if (id.length() == 0 || id.isEmpty())
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            else 
                return id;
        }
    }
    public static String getString(String inputMsg, String errorMsg, int lowerBound, int upperBound ) {
        int tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        String id;        
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();            
            if (id.length() == 0 || id.isEmpty() || id.length()< lowerBound || id.length()>upperBound )
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            else 
                return id;
        }
    }
    public static int getYesNo(String inputMsg, String errorMsg){
        String id;        
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            if(id.equalsIgnoreCase("yes")||id.equalsIgnoreCase("y"))
                return 1;
            else{
                if(id.equalsIgnoreCase("no")||id.equalsIgnoreCase("n"))
                    return -1;
                else
                    System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            }                               
        }
    }
    public static String getDate(String inputMsg, String errorMsg) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        String dateStr;
        while (true) {
            try {
                dateStr = getString(inputMsg, errorMsg);
                if(sdf.parse(dateStr).compareTo(sdf.parse("08/03/2021"))>0&&sdf.parse(dateStr).compareTo(sdf.parse(sdf.format(date)))<=0)
                    return dateStr;
                else
                    System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            } catch (ParseException e) {
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            }
        }
    }
    public static String getDateTmp(String inputMsg, String errorMsg) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        String dateStr;
        while (true) {
            try {
                dateStr = getString(inputMsg, errorMsg);
                if(sdf.parse(dateStr).compareTo(sdf.parse("08/03/2021"))>=0)
                    return dateStr;
                else
                    System.out.println(errorMsg);
            } catch (ParseException e) {
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            }
        }
    }
    
    public static int daysBetween(String d1, String d2){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try{
        return (int) ((sdf.parse(d2).getTime()-sdf.parse(d1).getTime())/((24 * 60 * 60 * 1000)));
        }catch(ParseException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public static String getDate1(String d1){
        String d2;
        while (true) {
            d2 = getDateTmp("Enter the date of the second injection: ", "Input invalid!");     
            int n = daysBetween(d1, d2);
            if (n<27 || n >85){
                System.out.println("The second dose of vaccine must be given 4 to 12 weeks after the first injection !");
                System.out.println("Your first dose of vaccine was given on "+ d1);
            }
            else
                return d2;           
        }
    }
    public static String getPlace(String inputMsg, String errorMsg){
        ArrayList<String> list = new ArrayList();
        ArrayList<String> list1 = new ArrayList();
        Menu menu = new Menu("Looks like these are the provinces you want to mention. Please select a province:");
        list = FileDAO.readFilePlace("place.txt");
        do{
        String place = Inputter.getString(inputMsg, errorMsg);
        for (String string : list) {
            if(string.equalsIgnoreCase(place.replaceAll("\\s\\s+"," ")))
                return string;
        }
        String[] arr = place.split("\\s");
        for (String w : arr) {
            if(w.length()>1){
            for (String string : list) {
                if(string.contains(w.toUpperCase()))
                    list1.add(string);
            }
        }
        }
        if(list1.isEmpty())
                System.out.println(ANSI_RED + "Your input could not give a hint. Let's try again!" + ANSI_RESET);
        }while(list1.isEmpty());
        Set<String> dataSet = new HashSet<>(list1);        
        for (int i = 0; i < dataSet.size(); i++) {
            menu.addNewOption((i+1) + ". "+ list1.get(i));
        }
        menu.printMenu();
        int choice = menu.getChoice();
        return list1.get(choice-1);
    }
    
}
