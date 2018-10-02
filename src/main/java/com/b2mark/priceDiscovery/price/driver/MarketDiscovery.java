/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.price.driver;

import com.b2mark.priceDiscovery.price.driver.adapter.Btc;
import com.b2mark.priceDiscovery.price.driver.adapter.Irr;
import com.b2mark.priceDiscovery.price.driver.adapter.PriceAdapter;
import com.b2mark.priceDiscovery.price.driver.adapter.Usd;
import com.b2mark.priceDiscovery.entity.Price;

public class MarketDiscovery implements Market {

    @Override
    public void getPrice(String currency1, String currency2) {
        PriceAdapter priceAdapter;
        switch (currency1) {
            case "Irr":
                priceAdapter = new Irr();
                toCurrency(priceAdapter, currency2);
                break;
            case "Usd":
                priceAdapter = new Usd();
                toCurrency(priceAdapter, currency2);
                break;
            case "Btc":
                priceAdapter = new Btc();
                toCurrency(priceAdapter, currency2);
                break;
        }
    }


    /**
     * get methods by priceAdapter and call method peer that.
     *
     * @param priceAdapter
     * @param currency2
     */
    private Price toCurrency(PriceAdapter priceAdapter, String currency2) {
        switch (currency2) {
            case "Irr":
                return priceAdapter.priceToIrr();
            case "Usd":
                return priceAdapter.priceToUsd();
            case "Btc":
                return priceAdapter.priceToBtc();
            default:
                return null;//"this currency is not valid";
        }
    }
}
