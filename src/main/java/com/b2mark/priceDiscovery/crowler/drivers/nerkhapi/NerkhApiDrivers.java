/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.crowler.drivers.nerkhapi;

import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.crowler.drivers.Driver;
import com.b2mark.priceDiscovery.crowler.drivers.DriverInterface;
import com.b2mark.priceDiscovery.entity.Price;
import com.b2mark.priceDiscovery.entity.arzws.gov.ArzwsGovEntity;
import com.b2mark.priceDiscovery.entity.arzws.gov.ExchangeRate;
import com.b2mark.priceDiscovery.entity.nerkhapi.Currency;
import com.b2mark.priceDiscovery.entity.nerkhapi.Data;
import com.b2mark.priceDiscovery.entity.nerkhapi.NerkhApi;

import java.util.*;

public abstract class NerkhApiDrivers
        extends Driver implements DriverInterface {


    @Override
    public List<Price> crowl(Coin... coins) {
        List<Price> prices = new ArrayList<>();
        NerkhApi nerkhApi = null;
        try {
            nerkhApi  = restTemplate.getForObject(endpoint, NerkhApi.class);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: ERROR   " + ex.getMessage());
        }
        List<Coin> listCoin = Arrays.asList(coins);
        for (Coin coin : listCoin) {

            Currency currency = nerkhApi.getData().getPrices().getByCoin(coin);
            Map<String, Object> coinSymbol = new HashMap<>();
            coinSymbol.put("symbol", coin.getSymbol());
            currency.setAdditionalProperties(coinSymbol);


            Price price = convertToPrice(currency);
            if (price != null) {
                prices.add(price);
            }
        }
        return prices;
    }

    private Price convertToPrice(Currency currency) {
        Price price = new Price();
        String symbol =(String) currency.getAdditionalProperties().get("symbol") ;
        Coin sourceCoin = Coin.fromSymbol(symbol);
        Coin destCoin = Coin.IRANRIAL;
        if (sourceCoin != null && isSupport(sourceCoin) > 0) {
            Date date = new Date();
            price.setDate(date);
            price.setCoin(sourceCoin);
            price.setPrice(currency.getCurrent());
            price.setDestCoin(destCoin);
            price.setDriverName(drivername);
            return price;
        } else {
            return null;
        }
    }
}
