package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class InventoryTest {

    Inventory inventory;

    @Before
    public void setup() {
        inventory = new Inventory();
    }

    @Test
    public void costOf_should_return_product_price() {
        BigDecimal actualPrice = inventory.costOf("A1");
        BigDecimal expectedPrice = new BigDecimal("3.05");

        Assert.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void costOf_should_return_zero_if_product_not_found() {
        BigDecimal actualPrice = inventory.costOf("Z42");
        BigDecimal expectedPrice = new BigDecimal("0");

        Assert.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void consume_should_return_blank_string_if_product_is_sold_out() {
        inventory.consume("A1");
        inventory.consume("A1");
        inventory.consume("A1");
        inventory.consume("A1");
        inventory.consume("A1");
        String actual = inventory.consume("A1");
        String expected = "";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void consume_should_reduce_the_product_quantity_when_called() {
        inventory.consume("A1");
        inventory.consume("A1");
        inventory.consume("A1");
        inventory.consume("A1");
        inventory.consume("A1");

        boolean expected = inventory.isInStock("A1");

        Assert.assertFalse(expected);
    }

    @Test
    public void nameOf_should_return_the_product_name() {
        String actual = inventory.nameOf("A1");
        String expected = "Potato Crisps";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void nameOf_should_return_empty_string_if_product_does_not_exist() {
        String actual = inventory.nameOf("AV451");
        String expected = "";

        Assert.assertEquals(expected, actual);
    }




}
