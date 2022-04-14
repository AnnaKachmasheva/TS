package DU4.cz.cvut.fel.cz.archive;

import DU4.cz.cvut.fel.cz.ItemPurchaseArchiveEntry;
import DU4.cz.cvut.fel.cz.shop.Generator;
import DU4.cz.cvut.fel.cz.shop.StandardItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemPurchaseArchiveEntryTest {

    private static ItemPurchaseArchiveEntry itemPurchaseArchiveEntry;
    private static StandardItem standardItem;

    @BeforeAll
    public static void createItemStock () {
        standardItem = Generator.generateStandardItem();
        itemPurchaseArchiveEntry = new ItemPurchaseArchiveEntry(standardItem);
    }
    @Test

    @DisplayName("Constructor")
    void ItemStock_newItemStock() {
        Assertions.assertEquals(standardItem, itemPurchaseArchiveEntry.getRefItem());
        Assertions.assertEquals(1, itemPurchaseArchiveEntry.getCountHowManyTimesHasBeenSold());
    }
}