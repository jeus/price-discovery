/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.price.driver;

import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.entity.Price;
import com.b2mark.priceDiscovery.price.driver.adapter.Btc;
import com.b2mark.priceDiscovery.price.driver.adapter.Irr;
import com.b2mark.priceDiscovery.price.driver.adapter.PriceAdapter;
import com.b2mark.priceDiscovery.price.driver.adapter.Usd;
import org.springframework.stereotype.Service;

@Service
public class MarketDiscovery implements Market {

    @Override
    public Price getPrice(Coin coin1, Coin coin2) {
        Price price =  null;
        PriceAdapter priceAdapter = null;
        switch (coin1) {
            case IRANRIAL:
                priceAdapter = new Irr();
                 toCurrency(priceAdapter, coin2);
                break;
            case USDOLLAR:
                priceAdapter = new Usd();
                toCurrency(priceAdapter, coin2);
                break;
            case BITCOIN:
                priceAdapter = new Btc();
                toCurrency(priceAdapter, coin2);
                break;
        }
        return price;
    }


    /**
     * get methods by priceAdapter and call method peer that.
     *
     * @param priceAdapter
     * @param coin2
     */
    private Price toCurrency(PriceAdapter priceAdapter, Coin coin2) {
        switch (coin2) {
            case IRANRIAL:
                return priceAdapter.priceToIrr();
            case USDOLLAR:
                return priceAdapter.priceToUsd();
            case BITCOIN:
                return priceAdapter.priceToBtc();
            default:
                return null;//"this currency is not valid";
        }
    }
}
