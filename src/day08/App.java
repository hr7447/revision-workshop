package day08;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {

    public static void main(String[] args) {
        // Task 3: Create ArrayList and add products
        List<Product> products = new ArrayList<>();
        Date currentDate = new Date();

        products.add(new Product(1L, "Mouse", "For click UI on screen", "Computer", 99.0f, currentDate));
        products.add(new Product(2L, "Keyboard", "device that allows alpha numerics inputs", "Computer", 235.5f,
                currentDate));
        products.add(new Product(3L, "15.6 inch monitor", "Extended display panel", "Computer", 157.5f, currentDate));
        products.add(new Product(4L, "Huawei Pura 70 Ultra", "Huawei phone", "Mobile", 900.0f, currentDate));
        products.add(new Product(5L, "Huawei Mate 50 Pro", "Huawei phone", "Mobile", 1200.0f, currentDate));
        products.add(new Product(6L, "iPhone 16 Pro", "Iphone", "Mobile", 2000.0f, currentDate));
        products.add(new Product(7L, "iPhone 14 Pro", "Iphone", "Mobile", 1800.0f, currentDate));

        // Task 4: Filter mobile phones above $1500
        System.out.println("Mobile phones above $1500:");
        List<Product> filteredProducts = products.stream()
                .filter(p -> p.getProdCat().equals("Mobile") && p.getPrice() > 1500)
                .toList();

        for (Product p : filteredProducts) {
            System.out.println(p);
        }

        // Task 5: Write filtered output to file
        if (args.length > 0) {
            String filePath = args[0];
            try {
                FileWriter writer = new FileWriter(filePath);
                for (Product p : filteredProducts) {
                    writer.write(p.toString() + "\n");
                }
                writer.close();
                System.out.println("Filtered products written to " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }

        // Task 6: Generate permutations
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter 4 characters (digits or alphabets):");
        String input = scanner.nextLine().toUpperCase();

        if (input.length() == 4) {
            Set<String> permutations = new HashSet<>();
            generatePermutations("", input, permutations);

            System.out.println("\nPermutations:");
            for (String perm : permutations) {
                System.out.println(perm);
            }
        } else {
            System.out.println("Please enter exactly 4 characters.");
        }

        scanner.close();
    }

    // Helper method for generating permutations
    private static void generatePermutations(String prefix, String str, Set<String> permutations) {
        int n = str.length();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                generatePermutations(prefix + str.charAt(i),
                        str.substring(0, i) + str.substring(i + 1, n),
                        permutations);
            }
        }
    }

}
