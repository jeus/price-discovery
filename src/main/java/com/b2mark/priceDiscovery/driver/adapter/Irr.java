/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.driver.adapter;

import com.b2mark.priceDiscovery.entity.Price;

import java.util.Date;

public class Irr implements PriceAdapter {

    @Override
    public Price priceToUsd() {
        Price price = new Price();
        price.setDate(new Date());
        price.setName("United state dollar");
        price.setSymbol("USD");
        return price;
    }

    @Override
    public Price priceToIrr() {
        return null;
    }


    @Override
    public Price priceToBtc(){
        return null;
    }
}
