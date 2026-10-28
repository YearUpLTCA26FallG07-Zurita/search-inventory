package com.pluralsight;

import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
public class SearchInventory {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Product> inventory = getInventory();
        bonusInventory(inventory);
        inventory.sort(Comparator.comparing(Product::getName));
        System.out.println("""
                \nCurrent Inventory:
                ------------------""");

        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.printf("id:|%d| %-30s | $%.2f%n",
                    p.getId(), p.getName(), p.getPrice());

        }
    }

    public static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<Product>();

        inventory.add(new Product(1573, "IPhone", 1600.00));
        inventory.add(new Product(2298, "USB-C Charger", 20.00));
        inventory.add(new Product(3394, "AirPods Max", 250.00));
        inventory.add(new Product(4045, "AirPods Gen 1", 90.00));
        inventory.add(new Product(5211, "AirTag", 15.00));

        return inventory;
    }
    public static ArrayList<Product> bonusInventory(ArrayList<Product> inventory) throws FileNotFoundException {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("inventory.csv"));

            String input;

            while ((input = bufferedReader.readLine()) != null) {
                String[] bonusInventory = input.split("\\|");
                int id = Integer.parseInt(bonusInventory[0]);
                String name = bonusInventory[1];
                double price = Double.parseDouble(bonusInventory[2]);
                Product bonusItem = new Product(id, name, price);
                inventory.add(bonusItem);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }
}