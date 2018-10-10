/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.price.driver.adapter;

import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.controller.PriceController;
import com.b2mark.priceDiscovery.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class Btc implements PriceAdapter {

    @Autowired
    PriceController priceController;


    @Override
    public Price priceToUsd() {
        Price price = new Price();
        List<Price> priceList = priceController.getPriceArzwsCrypto();
        for (Price price1 : priceList) {
            if (price1.getCoin() == Coin.BITCOIN) {
                price = price1;
            }
        }
        return price;
    }

    @Override
    public Price priceToIrr() {
return null;
    }

    @Override
    public Price priceToBtc() {
        return null;
    }


}
