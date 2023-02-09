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
    public void purchase_should_return_product_price() {
        BigDecimal actualPrice = inventory.purchase("A1");
        BigDecimal expectedPrice = new BigDecimal("3.05");

        Assert.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void purchase_should_return_zero_if_product_not_found() {
        BigDecimal actualPrice = inventory.purchase("Z42");
        BigDecimal expectedPrice = new BigDecimal("0");

        Assert.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void purchase_should_return_zero_if_product_is_sold_out() {
        inventory.purchase("A1");
        inventory.purchase("A1");
        inventory.purchase("A1");
        inventory.purchase("A1");
        inventory.purchase("A1");
        BigDecimal actualPrice = inventory.purchase("A1");
        BigDecimal expectedPrice = new BigDecimal("0");

        Assert.assertEquals(expectedPrice, actualPrice);
    }


}
