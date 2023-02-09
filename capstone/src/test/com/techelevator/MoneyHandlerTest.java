package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class MoneyHandlerTest {

    MoneyHandler money = null;

    @Before
    public void setup() {
        money = new MoneyHandler();
    }

    @Test
    public void feedMoney_should_increase_wallet_value_by_dollar_amount() {
        money.feed(5);
        BigDecimal expectedValue = new BigDecimal(5);
        BigDecimal actualValue = money.getWallet();

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void feedMoney_should_not_change_wallet_value_by_negative_dollar_amount() {
        money.feed(-3);
        BigDecimal expectedValue = new BigDecimal(0);
        BigDecimal actualValue = money.getWallet();

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void get_change_should_return_string_containing_the_change_in_coins() {
        money.feed(5);
        money.spend(new BigDecimal("2.85"));

        String expectedOutput = "Your change: 8 Quarter(s) 1 Dime(s) 1 Nickel(s)";
        Assert.assertEquals(expectedOutput, money.getChange());
    }

    @Test
    public void get_change_should_empty_string_if_no_change_remaining() {
        money.feed(5);
        money.spend(new BigDecimal("5"));

        String expectedOutput = "";
        Assert.assertEquals(expectedOutput, money.getChange());
    }

    @Test
    public void get_change_should_update_wallet_to_zero() {
        money.feed(5);
        money.spend(new BigDecimal("2.85"));

        money.getChange();
        BigDecimal expectedOutput = BigDecimal.ZERO;
        Assert.assertEquals(expectedOutput, money.getWallet());
    }

    @Test
    public void spend_should_reduce_wallet_amount() {
        money.feed(5);
        money.spend(new BigDecimal(2.50));

        BigDecimal expectedValue = new BigDecimal(2.50);
        BigDecimal actualValue = money.getWallet();

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void spend_should_not_change_wallet_amount_if_not_enough_funds() {
        money.feed(5);
        money.spend(new BigDecimal(10));

        BigDecimal expectedValue = new BigDecimal(5.00);
        BigDecimal actualValue = money.getWallet();

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getTotalSales_should_return_total_amount_of_all_money_spent() {

        //User Transaction 1
        money.feed(5);
        money.spend(new BigDecimal(1.50));
        money.getChange();

        //User Transaction 2
        money.feed(3);
        money.spend(new BigDecimal(2));
        money.getChange();

        //User Transaction 2
        money.feed(10);
        money.spend(new BigDecimal(4));
        money.getChange();

        BigDecimal expectedValue = new BigDecimal(7.50);
        BigDecimal actualValue = money.getTotalSales();

        Assert.assertEquals(expectedValue, actualValue);

    }

}
