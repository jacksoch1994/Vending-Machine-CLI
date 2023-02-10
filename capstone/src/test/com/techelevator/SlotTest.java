package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class SlotTest {

    @Test
    public void vend_should_return_vocalization_when_quantity_above_zero() {
        Slot slot = new Slot( "Candy", new BigDecimal(5), "candy", 5);
        Assert.assertEquals(slot.getVocalization(), slot.vend());
    }

    @Test
    public void vend_should_decrement_quantity() {
        Slot slot = new Slot( "Candy", new BigDecimal(5), "candy", 1);
        slot.vend();
        Assert.assertEquals(0, slot.getQuantity());
    }

    @Test
    public void vend_should_return_an_empty_string_if_no_product_to_vend() {
        Slot slot = new Slot( "Candy", new BigDecimal(5), "candy", 0);
        slot.vend();
        Assert.assertEquals("", slot.vend());
    }


}
