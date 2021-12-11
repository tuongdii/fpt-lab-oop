
package util;

import java.text.*;
import java.util.*;


public class Inputter {
 
    private static Scanner sc = new Scanner(System.in);
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
                System.out.println(errorMsg);
            }
        }
    }
    public static double getDouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
        double n, tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n <= lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }    
    
    public static String getString(String inputMsg, String errorMsg) {
        String id;        
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();            
            if (id.length() == 0 || id.isEmpty())
                System.out.println(errorMsg);
            else 
                return id;
        }
    }
    public static String getPlace() {
        String id;        
        while (true) {
            Menu menu = new Menu("Choose the place of the food:");
            menu.addNewOption("1. Cool refrigerator compartment");
            menu.addNewOption("2. Refrigerator freezer");
            menu.printMenu();
            int choice = menu.getChoice();
            if(choice == 1)
                return "Cool refrigerator compartment";
            if(choice == 2)
                return "Refrigerator freezer";
                
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
                    System.out.println(errorMsg);
            }                               
        }
    }
    public static String getDate(String inputMsg, String errorMsg) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        String dateStr;
        while (true) {
            try {
                dateStr = getString(inputMsg, errorMsg);
                if(sdf.parse(dateStr).compareTo(sdf.parse("01/01/2050"))<0)
                    return dateStr;
            } catch (ParseException e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static void decorate(){
        System.out.println("================================================================");
    }
}