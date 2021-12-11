package data;

import java.io.*;
import java.text.*;
import java.util.*;
import util.Inputter;

public class FoodManager {

    private ArrayList<Food> list = new ArrayList();

    public void addFood() {
        String id, name, type, place, expiredDate;
        double weight;
        int pos;
        int check, check1 = -2;
        do {
            do {
                id = Inputter.getString("-Enter the food id: ", "Input invalid!");
                pos = searchID(id);
                if (pos >= 0) {
                    System.out.println("The food id already exists");
                }
            } while (pos != -1);
            name = Inputter.getString("-Enter the name of the food: ", "Input invalid!");
            type = Inputter.getString("-Enter the type of the food: ", "Input invalid!");
            place = Inputter.getPlace();
            expiredDate = Inputter.getDate("-Enter an expiration date: ", "Input invalid!");
            if (checkDay(expiredDate) < 0) {
                System.out.println("Food has expired!!!");
                check1 = Inputter.getYesNo("Do you want to continue importing this food?(Yes/No)", "Please enter Yes/No or Y/N!");
            }
            if (check1 == 1 || check1 == -2) {
                weight = Inputter.getDouble("-Enter the weight of the food: ", "Input invalid!", 0, 20);
                list.add(new Food(id, name, weight, type, place, expiredDate));
                System.out.println("Add successfully");
                
            }
            check = Inputter.getYesNo("Do you want to continue adding another food? (Yes/No) ", "Please enter Yes/No or Y/N!");
        } while (check == 1);
    }

    public void searchByName() {
        String key;
        int check;
        if (list.isEmpty()) {
            System.out.println("The food list is empty. Nothing to search");
        } else {
            do {
                int s = 0;
                key = Inputter.getString("-Enter the name of the food you want to find:", "Input invalid!");
                for (Food food : list) {
                    if (food.getName().toLowerCase().contains(key.toLowerCase().replaceAll("\\s\\s+"," "))) {
                        food.output();
                        s++;
                    }
                }
                if (s == 0) {
                    System.out.println("This food does not exist!");
                }
                check = Inputter.getYesNo("Do you want to continue searching another food? (Yes/No) ", "Please enter Yes/No or Y/N!");
            } while (check == 1);
        }
    }

    public void removeByID() {
        if (list.isEmpty()) {
            System.out.println("The food list is empty. Nothing to remove.");
        } else {
            int check;
            String id = Inputter.getString("-Enter the food id: ", "Input invalid");
            if (searchID(id) == -1) {
                System.out.println("This food does not exist!");
            } else {
                System.out.println("The food you want to remove: ");
                list.get(searchID(id)).output();
                check = Inputter.getYesNo("Are you sure you want to delete it?(Yes/No)", "Please enter Yes/No or Y/N!");
                if (check == 1) {
                    list.remove(searchID(id));
                    System.out.println("Remove successfully!");
                } else {
                    System.out.println("Remove failed!");
                }
            }
        }
    }

    public void sortByDate() {
        if (list.isEmpty()) {
            System.out.println("The food list is empty. Nothing to print!");
        }
        Collections.sort(list);
        System.out.println("The food list after sorting: ");
        System.out.printf("|%-5s|%-20s|%-6s|%-10s|%-30s|%-10s|\n", "ID", "Name", "Weight", "Type", "Place", "Expired Date ");
        for (Food food : list) {
            food.output();
        }
    }

    public void display() {
        if (list.isEmpty()) {
            System.out.println("The food list is empty. Nothing to print");
        } else {
            System.out.printf("|%-5s|%-20s|%-6s|%-10s|%-30s|%-10s|\n", "ID", "Name", "Weight", "Type", "Place", "Expired Date ");
            for (Food food : list) {
                food.output();
            }
        }
    }

    public int searchID(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public int checkDay(String str) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            String str1 = sdf.format(date);
            return sdf.parse(str).compareTo(sdf.parse(str1));
        } catch (ParseException e) {
            System.out.println("Error");
            return -1;
        }
    }

    public void saveFile() {
        if (list.isEmpty()) {
            System.out.println("Save FAILED. Empty list.");
            return;
        }
        try {
            FileWriter fw = new FileWriter("foodlist.txt");
            fw.write("===========================================================================================\n");
            fw.write(String.format("|%-5s|%-20s|%-6s|%-10s|%-30s|%-10s|\n", "ID", "Name", "Weight", "Type", "Place", "Expired Date "));
            for (Food food : list) {
                fw.write(food.toString());
            }
            fw.write("===========================================================================================");
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void data() {
        list.add(new Food("123", "Ca rot", 1.0, "Cu", "Cool refrigerator compartment", "1/10/2021"));
        list.add(new Food("456", "Thit ga", 2.5, "Thit", "Cool refrigerator compartment", "1/11/2021"));
        list.add(new Food("234", "Ot", 0.5, "Trai", "Cool refrigerator compartment", "20/9/2021"));
    }

}
    
    
