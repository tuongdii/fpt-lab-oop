package data;

import java.text.*;

public class Food implements Comparable<Food>{
    private String id;
    private String name;
    private double weight;
    private String type;
    private String place;
    private String expiredDate;

    public Food() {
    }

    public Food(String id, String name, double weight, String type, String place, String expiredDate) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.expiredDate = expiredDate;
    }

 

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    public String getPlace() {
        return place;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return String.format("|%-5s|%-20s|%-4.1f  |%-10s|%-30s|%-13s|\n",
                            id, name, weight, type, place, expiredDate);
    }
    public void output(){
        System.out.printf("|%-5s|%-20s|%-4.1f  |%-10s|%-30s|%-13s|\n",
                            id, name, weight, type, place, expiredDate);
    }    
 

    @Override
    public int compareTo(Food that) {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return f.parse(that.getExpiredDate()).compareTo(f.parse(this.getExpiredDate())) ;                
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return 0;
            }
        }      
    }
