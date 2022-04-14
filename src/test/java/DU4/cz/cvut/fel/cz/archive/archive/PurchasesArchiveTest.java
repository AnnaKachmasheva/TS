package DU4.cz.cvut.fel.cz.archive.archive;

import DU4.cz.cvut.fel.cz.archive.ItemPurchaseArchiveEntry;
import DU4.cz.cvut.fel.cz.archive.PurchasesArchive;
import DU4.cz.cvut.fel.cz.archive.shop.Generator;
import DU4.cz.cvut.fel.cz.shop.Item;
import DU4.cz.cvut.fel.cz.shop.Order;
import DU4.cz.cvut.fel.cz.shop.ShoppingCart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PurchasesArchiveTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private ArrayList<Order> orderArchive;
    private HashMap<Integer, ItemPurchaseArchiveEntry> itemArchive;
    private PurchasesArchive purchasesArchive;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        orderArchive = Generator.generateOrderArchive();
        itemArchive = Generator.generateItemArchive();
        purchasesArchive = new PurchasesArchive(itemArchive, orderArchive);
    }

    @Test
    void printItemPurchaseStatistics_printlnStatistics_success() {
        purchasesArchive.printItemPurchaseStatistics();

        StringBuilder str = new StringBuilder();
        str.append("ITEM PURCHASE STATISTICS:").append("\r\n");

        Collection<ItemPurchaseArchiveEntry> itemEntries = itemArchive.values();
        for (ItemPurchaseArchiveEntry e : itemEntries)
            str.append(e.toString()).append("\r\n");

        assertEquals(str.toString(), outputStreamCaptor.toString());
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void getHowManyTimesHasBeenItemSold_getItemSoldCount_success() {
        Item item = Generator.generateStandardItem();
        ItemPurchaseArchiveEntry itemPurchaseArchiveEntry = new ItemPurchaseArchiveEntry(item);
        int count = 10;
        itemPurchaseArchiveEntry.setSoldCount(count);
        itemArchive.put(item.getID(), itemPurchaseArchiveEntry);
        PurchasesArchive pa = new PurchasesArchive(itemArchive, orderArchive);

        int countSold = pa.getHowManyTimesHasBeenItemSold(itemPurchaseArchiveEntry.getRefItem());

        assertEquals(count, countSold);
    }

    @Test
    void putOrderToPurchasesArchive_putOrderWithExistingItem_countSoldItemPlus () {
        Item item1 = Generator.generateStandardItem();
        Item item2 = Generator.generateStandardItem();
        ItemPurchaseArchiveEntry itemPA1 = new ItemPurchaseArchiveEntry(item1);
        ItemPurchaseArchiveEntry itemPA2 = new ItemPurchaseArchiveEntry(item2);
        itemPA2.setSoldCount(10);
        HashMap<Integer, ItemPurchaseArchiveEntry> itemsPA = new HashMap<>();
        itemsPA.put(itemPA1.getRefItem().getID(), itemPA1);
        itemsPA.put(itemPA2.getRefItem().getID(), itemPA2);

        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        ShoppingCart cart = new ShoppingCart(items);
        Order order = new Order(cart, "customerName", "customerAddress", 1);
        PurchasesArchive pa = new PurchasesArchive(itemsPA, new ArrayList<>());

        pa.putOrderToPurchasesArchive(order);
        HashMap<Integer, ItemPurchaseArchiveEntry> itemsPAfterAddedOrder = pa.getItemPurchaseArchive();

        assertEquals(2, itemsPAfterAddedOrder.get(item1.getID()).getCountHowManyTimesHasBeenSold());
        assertEquals(11, itemsPAfterAddedOrder.get(item2.getID()).getCountHowManyTimesHasBeenSold());
    }

    @Test
    void putOrderToPurchasesArchive_putOrderWithNotExistingItem_countSoldItemPlus () {
        Item item1 = Generator.generateStandardItem();
        Item item2 = Generator.generateStandardItem();
        ItemPurchaseArchiveEntry itemPA1 = new ItemPurchaseArchiveEntry(item1);
        itemPA1.setSoldCount(150);
        HashMap<Integer, ItemPurchaseArchiveEntry> itemsPA = new HashMap<>();
        itemsPA.put(itemPA1.getRefItem().getID(), itemPA1);

        ArrayList<Item> items = new ArrayList<>();
        items.add(item2);
        ShoppingCart cart = new ShoppingCart(items);
        Order order = new Order(cart, "customerName", "customerAddress", 1);
        PurchasesArchive pa = new PurchasesArchive(itemsPA, new ArrayList<>());

        pa.putOrderToPurchasesArchive(order);
        HashMap<Integer, ItemPurchaseArchiveEntry> itemsPAfterAddedOrder = pa.getItemPurchaseArchive();

        assertEquals(150, itemsPAfterAddedOrder.get(item1.getID()).getCountHowManyTimesHasBeenSold());
        assertEquals(1, itemsPAfterAddedOrder.get(item2.getID()).getCountHowManyTimesHasBeenSold());
    }

    @Test
    void itemPurchaseArchive_Mockito() {
        HashMap<Integer, ItemPurchaseArchiveEntry> itemsPAExpected = new HashMap<>(Map.copyOf(itemArchive));
        PurchasesArchive pa = mock(PurchasesArchive.class);

        when(pa.getItemPurchaseArchive()).thenReturn(itemArchive);

        assertEquals(itemsPAExpected, pa.getItemPurchaseArchive());
    }

    @Test
    void orderArchive_Mockito() {
        List<Order> expectedOrders = new ArrayList<>(List.copyOf(orderArchive));
        PurchasesArchive pa = mock(PurchasesArchive.class);

        when(pa.getOrderArchive()).thenReturn(orderArchive);

        assertEquals(expectedOrders, pa.getOrderArchive());

        String customerName = "customerNameNew";
        String customerAddress = "customerAddressNew";
        int state = 1000000000;
        orderArchive.get(0).setCustomerName(customerName);
        orderArchive.get(0).setCustomerAddress(customerAddress);
        orderArchive.get(0).setState(state);

        pa.getOrderArchive().get(0).setCustomerName(customerName);
        pa.getOrderArchive().get(0).setCustomerAddress(customerAddress);
        pa.getOrderArchive().get(0).setState(state);

        assertEquals(expectedOrders.get(0), pa.getOrderArchive().get(0));
    }
}