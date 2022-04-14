package DU4.cz.cvut.fel.cz.archive.shop;

import DU4.cz.cvut.fel.cz.shop.StandardItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class StandardItemTest {

    private static StandardItem standardItem;
    private static int id;
    private static  String name;
    private static float price;
    private static String category;
    private static int loyaltyPoints;

    @BeforeAll
    public static void createStandardItem() {
        id = Generator.generateID();
        name = Generator.generateName();
        price = Generator.generatePrice();
        category = Generator.generateCategory();
        loyaltyPoints = Generator.generatePoints();
        standardItem = new StandardItem(id, name, price, category, loyaltyPoints);
    }

    @Test
    @DisplayName("Constructor")
    void StandardItem_newStandardItem() {
        Assertions.assertEquals(id, standardItem.getID());
        Assertions.assertEquals(name, standardItem.getName());
        Assertions.assertEquals(price, standardItem.getPrice());
        Assertions.assertEquals(category, standardItem.getCategory());
        Assertions.assertEquals(loyaltyPoints, standardItem.getLoyaltyPoints());
    }

    @Test
    @DisplayName("CopyStandardItem")
    void copy_copyStandardItem_newStandardItem() {
        StandardItem copyStandardItem = standardItem.copy();

        Assertions.assertEquals(id, copyStandardItem.getID());
        Assertions.assertEquals(name, copyStandardItem.getName());
        Assertions.assertEquals(price, copyStandardItem.getPrice());
        Assertions.assertEquals(category, copyStandardItem.getCategory());
        Assertions.assertEquals(loyaltyPoints, copyStandardItem.getLoyaltyPoints());
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        standardItem,
                        standardItem,
                        true),
                Arguments.of(
                        Generator.generateStandardItem(),
                        Generator.generateStandardItem(),
                        false),
                Arguments.of(
                        Generator.generateStandardItem(),
                        standardItem,
                        false),
                Arguments.of(
                        standardItem,
                        null,
                        false));
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void equals_standardItemEqualsObject_isEquals(StandardItem standardItem, Object object, boolean isEquals) {
       Assertions.assertEquals(isEquals, standardItem.equals(object));
    }
}