package assg5_trofimovm19;

import assg5_trofimovm19.BinaryTree.KeyedItem;

public class Customer extends KeyedItem<String> {
    private String id;
    private String name;
    private String phoneNumber;

    public Customer(String id, String name, String phoneNumber) {
        super(id);
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return  id + "\t" +
                name + "\t" +
                phoneNumber;
    }
}
