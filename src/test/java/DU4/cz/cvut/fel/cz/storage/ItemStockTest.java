package DU4.cz.cvut.fel.cz.storage;

import DU4.cz.cvut.fel.cz.shop.Generator;
import DU4.cz.cvut.fel.cz.shop.StandardItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ItemStockTest {

    private static ItemStock itemStock;
    private static StandardItem standardItem;

    @BeforeAll
    public static void createItemStock () {
        standardItem = Generator.generateStandardItem();
        itemStock = new ItemStock(standardItem);
    }

    @Test
    @DisplayName("Constructor")
    void ItemStock_newItemStock() {
        Assertions.assertEquals(standardItem, itemStock.getItem());
        Assertions.assertEquals(0, itemStock.getCount());
    }

    @ParameterizedTest(name = "count {0} increment {1} should be equal to {2}")
    @CsvSource({"0, 0, 0", "3, 1, 4", "4, -4, 0", "4, -10, 0"})
    void increaseItemCount_countPlusX_countIncreasedByX(int initialCount, int x, int finalCount) {
        itemStock.setCount(initialCount);
        itemStock.increaseItemCount(x);

        Assertions.assertEquals(finalCount, itemStock.getCount());
    }

    @ParameterizedTest(name = "count {0} decrease {1} should be equal to {2}")
    @CsvSource({"0, 0, 0", "3, 1, 2", "4, -4, 8", "4, 10, 0"})
    void decreaseItemCount_countMinusX_countDecreasedByX(int initialCount, int x, int finalCount) {
        itemStock.setCount(initialCount);
        itemStock.decreaseItemCount(x);

        Assertions.assertEquals(finalCount, itemStock.getCount());
    }
}