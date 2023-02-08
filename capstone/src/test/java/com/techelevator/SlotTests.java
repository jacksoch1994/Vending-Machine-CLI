package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class SlotTests {

    @Test
    public void vend_should_return_price_when_quantity_above_zero() {
        Slot slot = new Slot( "Candy", new BigDecimal(5), "candy", 5);
        Assert.assertEquals(new BigDecimal(5), slot.vend());
    }

    @Test
    public void vend_should_decrement_quantity() {
        Slot slot = new Slot( "Candy", new BigDecimal(5), "candy", 5);
        slot.vend();
        Assert.assertEquals(4, slot.getQuantity());
    }


}
