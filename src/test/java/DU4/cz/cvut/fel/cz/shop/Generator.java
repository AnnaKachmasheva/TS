package DU4.cz.cvut.fel.cz.shop;

import DU4.cz.cvut.fel.cz.ItemPurchaseArchiveEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();

    //generate StandardItem
    public static int generateID () {
        return RAND.nextInt(1000);
    }

    public static String generateName () {
        return "name" + RAND.nextInt(1000);
    }

    public static float generatePrice () {
        return RAND.nextFloat(100000);
    }

    public static String generateCategory () {
        return "category" + RAND.nextInt(1000);
    }

    public static int generatePoints () {
        return RAND.nextInt(1000);
    }

    public static StandardItem generateStandardItem() {
        return new StandardItem(
                generateID(),
                generateName(),
                generatePrice(),
                generateCategory(),
                generatePoints()
        );
    }

    //generate ShoppingCart
    public static ShoppingCart generateShoppingCart() {
        int countItems = RAND.nextInt(100);
        ArrayList items = new ArrayList<>();
        for (int i = 0; i < countItems; i++)
            items.add(generateStandardItem());

        return new ShoppingCart(items);
    }

    //generate Order
    public static String generateAddress () {
        return "address" + RAND.nextInt(1000);
    }

    public static int generateState () {
        return RAND.nextInt(1000);
    }

    public static Order generateOrder () {
        return new Order(generateShoppingCart(), generateName(), generateAddress());
    }

    public static ArrayList<Order> generateOrderArchive () {
        int countOrder = RAND.nextInt(2, 1000);
        ArrayList orders = new ArrayList<>();
        for (int i = 0; i < countOrder; i++)
            orders.add(generateOrder());
        return orders;
    }

    public static ItemPurchaseArchiveEntry generateItemPurchaseArchiveEntry() {
        ItemPurchaseArchiveEntry item = new ItemPurchaseArchiveEntry(generateStandardItem());
        item.setSoldCount(RAND.nextInt(1000));
        return item;
    }

    public static HashMap<Integer, ItemPurchaseArchiveEntry> generateItemArchive () {
        HashMap<Integer, ItemPurchaseArchiveEntry> map = new HashMap<>();
        int size = RAND.nextInt(2, 10);
        for (int i = 0; i < size; i++)
            map.put(RAND.nextInt(1000), generateItemPurchaseArchiveEntry());
        return map;
    }

}
