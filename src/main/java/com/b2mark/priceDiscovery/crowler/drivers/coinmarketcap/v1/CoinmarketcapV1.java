/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.crowler.drivers.coinmarketcap.v1;

import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.crowler.drivers.Driver;
import com.b2mark.priceDiscovery.crowler.drivers.DriverInterface;
import com.b2mark.priceDiscovery.entity.Price;
import com.b2mark.priceDiscovery.entity.coinmarketcap.Coinmarketcap;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.*;

public class CoinmarketcapV1 extends Driver implements DriverInterface {


    public CoinmarketcapV1(RestTemplateBuilder restTemplateBuilder) {
        super();
        drivername = "coinmarketcap-v1";
        endpoint = "https://api.coinmarketcap.com/v1/ticker/bitcoin/";
        restTemplate = restTemplateBuilder.build();
        supporterdCoin = new Coin[]{Coin.BITCOIN,Coin.ETHEREUM};
        sortSupportCoin();
    }

    @Override
    public List<Price> crowl(Coin... prices) {
            return getPrices(prices);
    }

    private List<Price> getPrices(Coin... coins)  {
       List<Price> prices = new ArrayList<>();
        for(Coin coins1 : coins)
        {
            if(isSupport(coins1) > 0) {
                Coinmarketcap[] coinmarketcap = null;
                try {
                     coinmarketcap = restTemplate.getForObject(endpoint, Coinmarketcap[].class);
                }catch (Exception ex)
                {
                    System.out.println("JEUSDEBUG: ERROR   "+ex.getMessage());
                }
                Price price = convertToPrice(coinmarketcap[0]);
                prices.add(price);
            }
        }
        return prices;
    }

    private Price convertToPrice(Coinmarketcap coinmarketcap)
    {
        Price price = new Price();
        Date date = coinmarketcap.getLastUpdated();
        System.out.println("last_Update:"+date);
        price.setDate(date);
        price.setCoin(coinmarketcap.getCoin());
        price.setPrice(coinmarketcap.getPriceUsd());
        price.setDestCoin(coinmarketcap.getDestCoin());
        price.setDriverName(drivername);
        return price;
    }
}
