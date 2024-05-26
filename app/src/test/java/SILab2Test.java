import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Item {
    String name;
    String barcode; //numerical
    int price;
    float discount;

    public Item(String name, String code, int price, float discount) {
        this.name = name;
        this.barcode = code;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBarcode(String code) {
        this.barcode = code;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}

public class SILab2Test {
    @Test
    public void everyBranch() {
        RuntimeException ex;
        List<Item> allItems = new ArrayList<>();

        Item noName = new Item("", "0123456789", 10, 0);
        Item noCode = new Item("Apple", null, 20, 0);
        Item wrongCode = new Item("Banana", "aaaaaaaa", 10, 0);
        Item withDiscount = new Item("Milk", "0123456789", 30, 1.10F);
        Item bigIf = new Item("Watch", "0123456789", 350, 1.20F);

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 10));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        allItems.add(wrongCode);

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems, 100));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        allItems.clear();
        allItems.add(noCode);

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems, 2000));
        assertTrue(ex.getMessage().contains("No barcode!"));

        allItems.clear();

        allItems.add(noName);
        allItems.add(withDiscount);
        allItems.add(bigIf);

        assertTrue(SILab2.checkCart(allItems, 1000));
    }

    public void multipleCondition() {
        List<Item> allItems = new ArrayList<>();
        Item item1 = new Item("Watch", "0123456789", 350, 1.20F);
        Item item2 = new Item("Watch", "aaaaaaaaaa", 350, 1.20F);
        Item item3 = new Item("Watch", "0123456789", 350, 0);
        Item item4 = new Item("Watch", "0123456789", 100, 1.20F);

        allItems.add(item1);
        allItems.add(item2);
        allItems.add(item3);
        allItems.add(item4);

        assertTrue(SILab2.checkCart(allItems, 1300));
    }
}