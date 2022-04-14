package DU4.cz.cvut.fel.cz.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    private static ShoppingCart cart;
    private static  String customerName;
    private static String customerAddress;
    private static int state;
    private static Order order;

    @BeforeAll
    public static void createStandardItem() {
        cart = Generator.generateShoppingCart();
        customerName = Generator.generateName();
        customerAddress = Generator.generateAddress();
        state = Generator.generateState();
    }

    @Test
    @DisplayName("ConstructorWithFourParameters")
    void Order_newOrderWithFourParameters() {
        order = new Order(cart, customerName, customerAddress, state);

        Assertions.assertEquals(cart.getCartItems(), order.getItems());
        Assertions.assertEquals(customerName, order.getCustomerName());
        Assertions.assertEquals(customerAddress, order.getCustomerAddress());
        Assertions.assertEquals(state, order.getState());
    }

    @Test
    @DisplayName("ConstructorWithFourParameters")
    void Order_newOrderWithNullFourParameters() {
        order = new Order(new ShoppingCart(), null, null, 0);

        Assertions.assertTrue(order.getItems().size() == 0);
        Assertions.assertNull(order.getCustomerName());
        Assertions.assertNull(order.getCustomerAddress());
        Assertions.assertEquals(0, order.getState());
    }

    @Test
    @DisplayName("ConstructorWithTreeParameters")
    void Order_newOrderWithTreeParameters() {
        order = new Order(cart, customerName, customerAddress);

        Assertions.assertEquals(cart.getCartItems(), order.getItems());
        Assertions.assertEquals(customerName, order.getCustomerName());
        Assertions.assertEquals(customerAddress, order.getCustomerAddress());
        Assertions.assertEquals(0, order.getState());
    }
}