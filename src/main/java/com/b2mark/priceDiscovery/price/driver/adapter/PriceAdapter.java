/**
 * <h1></h1>
 * Adapter interface for get data from server.
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.price.driver.adapter;

import com.b2mark.priceDiscovery.entity.Price;

public interface PriceAdapter {

    //TODO if call time is not expire have to get again data from server.
    //TODO save data in reddis system. naming bt IRTOUSD for example.
    public Price priceToUsd();
    public Price priceToIrr();
    public Price priceToBtc();
}
