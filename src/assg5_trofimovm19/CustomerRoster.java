package assg5_trofimovm19;

import assg5_trofimovm19.BinaryTree.BinarySearchTree;
import assg5_trofimovm19.BinaryTree.TreeIterator;

import java.io.*;

public class CustomerRoster {
    public BinarySearchTree<Customer, ?> tree = new BinarySearchTree<>();
    public TreeIterator<Customer> bst = new TreeIterator<Customer>(tree);

    private File file;

    public void add(String id, String name, String phoneNumber) {
        Customer customer = new Customer(id, name, phoneNumber);
        tree.insert(customer);
    }

    public void remove(Customer customer) {
        tree.delete(customer);
    }

    public void updateName(Customer customer, String name) {
        customer.setName(name);
    }

    public void updatePhoneNumber(Customer customer, String phoneNumber) {
        customer.setPhoneNumber(phoneNumber);
    }

    public void display(Customer customer) {
        println(customer);
    }

    public void displayAll() {
        Customer customer = null;

        bst.setInorder();
        while (bst.hasNext()) {
            customer = bst.next();
            println(customer);
        }
    }

    public Customer exist(String id) {
        return searchItem(id);
    }

    public void loadData(String filePath) {
        tree.makeEmpty();

        BufferedReader reader;
        FileReader fileReader;

        try {
            file = new File(filePath);
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                var student = line.split("\t");
                if (student.length != 3) {
                    println("Wrong file filling: Customer must contains 3 fields.");
                    exit(1);
                }

                add(student[0], student[1], student[2]);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        if (file == null) {
            println("File is not opened.");
            return;
        }

        PrintWriter fileWriter;
        try {
            fileWriter = new PrintWriter(file);
            Customer customer = null;

            bst.setInorder();
            while (bst.hasNext()) {
                customer = bst.next();
                fileWriter.write(customer.toString() + "\n");
            }

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            fileWriter = null;
            e.printStackTrace();
        }
    }

    private Customer searchItem(String id) {
        Customer customer = null;

        bst.setInorder();
        while (bst.hasNext()) {
            customer = bst.next();
            if (customer.getId().equals(id)) {
                return customer;
            }
        }

        return null;
    }

    private void println(Object o) {
        System.out.println(o.toString());
    }

    private void exit(int status) {
        System.exit(status);
    }

}
