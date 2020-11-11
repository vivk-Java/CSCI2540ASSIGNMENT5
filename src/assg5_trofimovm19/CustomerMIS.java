package assg5_trofimovm19;

import java.util.Objects;
import java.util.Scanner;

public class CustomerMIS {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static final String path = "assg5_CustomerRoster.txt";
    private static final String Menu =
            "1.\tDisplay the roster\n" +
            "2.\tAdd a new customer\n" +
            "3.\tDelete a customer\n" +
            "4.\tSearch customer by ID\n" +
            "5.\tUpdate customer's name by ID\n" +
            "6.\tUpdate customer's phone number by ID\n" +
            "7.\tSave and exit\n";

    private static final Integer DISPLAY = 1;
    private static final Integer ADD_CUSTOMER = 2;
    private static final Integer DELETE_CUSTOMER = 3;
    private static final Integer SEARCH_BY_ID = 4;
    private static final Integer UPDATE_NAME = 5;
    private static final Integer UPDATE_PHONE_NUMBER = 6;
    private static final Integer SAVE = 7;

    private static CustomerRoster roster;
    private static Scanner in;

    public static void main(String[] args) {
        roster = new CustomerRoster();
        roster.loadData(path);

        in = new Scanner(System.in).useDelimiter("\n");

        Integer next;
        String nextLine;

        while (true) {
            println(Menu);
            print(">\t");

            nextLine = in.nextLine();
            println();
            while (nextLine.isBlank()) {
                nextLine = in.nextLine();
                println();
            }

            try {
                next = Integer.valueOf(nextLine);
            } catch (NumberFormatException e) {
                errMessage("Unknown menu item.\n");
                continue;
            }

            if (Objects.equals(next, DISPLAY)) {
                displayAll();
            } else if (Objects.equals(next, ADD_CUSTOMER)) {
                addCustomer();
            } else if (Objects.equals(next, DELETE_CUSTOMER)) {
                deleteCustomer();
            } else if (Objects.equals(next, SEARCH_BY_ID)) {
                search();
            } else if (Objects.equals(next, UPDATE_NAME)) {
                updateName();
            } else if (Objects.equals(next, UPDATE_PHONE_NUMBER)) {
                updatePhoneNumber();
            } else if (Objects.equals(next, SAVE)) {
                saveExit();
                break;
            } else {
                println("Unknown menu item.");
            }
        }

    }

    private static void displayAll() {
        roster.displayAll();
        println();
    }

    private static void addCustomer() {
        print("PRINT ID >\t");
        String id = in.nextLine();

        Customer customer = roster.exist(id);
        if (customer != null) {
            errMessage("Wrong customer ID:\t" +
                    "Customer with such ID already exists.\n");
            return;
        }

        print("PRINT NAME >\t");
        String name = in.nextLine();

        print("PRINT PHONE NUMBER >\t");
        String phoneNumber = in.nextLine();

        roster.add(id, name, phoneNumber);
        successMessage("Success.\n");
    }

    private static void deleteCustomer() {
        print("PRINT ID >\t");
        String id = in.nextLine();

        Customer customer = roster.exist(id);
        if (customer == null) {
            errMessage("Wrong customer ID:\t" +
                    "Customer with such ID does not exists.\n");
            return;
        }

        roster.remove(customer);
        successMessage("Success.\n");
    }

    private static void search() {
        print("PRINT ID >\t");
        String id = in.nextLine();

        Customer customer = roster.exist(id);
        if (customer == null) {
            errMessage("Wrong customer ID:\t" +
                    "Customer with such ID does not exists.\n");
            return;
        }

        println(customer);
        successMessage("Success.\n");
    }

    private static void updateName() {
        print("PRINT ID >\t");
        String id = in.nextLine();

        Customer customer = roster.exist(id);
        if (customer == null) {
            errMessage("Wrong customer ID:\t" +
                    "Customer with such ID does not exists.\n");
            return;
        }

        print("PRINT NAME >\t");
        String name = in.nextLine();

        roster.updateName(customer, name);
        successMessage("Success.\n");
    }

    private static void updatePhoneNumber() {
        print("PRINT ID >\t");
        String id = in.nextLine();

        Customer customer = roster.exist(id);
        if (customer == null) {
            errMessage("Wrong customer ID:\t" +
                    "Customer with such ID does not exists.\n");
            return;
        }

        print("PRINT PHONE NUMBER >\t");
        String phoneNumber = in.nextLine();

        roster.updatePhoneNumber(customer, phoneNumber);
        successMessage("Success.\n");
    }

    private static void saveExit() {
        roster.save();
        successMessage("Success.\n");

        System.exit(0);
    }

    private static void print(Object o) {
        System.out.print(o.toString());
    }

    private static void println(Object o) {
        System.out.println(o.toString());
    }

    private  static void println() {
        System.out.println();
    }

    private static void errMessage(Object o) {
        println(ANSI_RED + o.toString() + ANSI_RESET);
    }

    private static void successMessage(Object o) {
        println(ANSI_BLUE + o.toString() + ANSI_RESET);
    }
}
