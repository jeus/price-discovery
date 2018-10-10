/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.controller;


import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.crowler.drivers.DriverInterface;
import com.b2mark.priceDiscovery.crowler.drivers.arzws.crypto.ArzWsCrypto;
import com.b2mark.priceDiscovery.crowler.drivers.arzws.gov.ArzWsGov;
import com.b2mark.priceDiscovery.crowler.drivers.arzws.market.ArzWsMarket;
import com.b2mark.priceDiscovery.crowler.drivers.coinmarketcap.v1.CoinmarketcapV1;
import com.b2mark.priceDiscovery.entity.Price;
import com.b2mark.priceDiscovery.price.driver.MarketDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Service
@RequestMapping("/price")
public class PriceController {
//    private final ReactiveRedisOperations<String, Price> priceOps;

//    PriceController(ReactiveRedisOperations<String, Price> priceOps) {
//        this.priceOps = priceOps;
//    }

    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    @Autowired
    MarketDiscovery marketDiscovery;


    @GetMapping("/btc")
    public List<Price> getPrice() {
        DriverInterface driver = new CoinmarketcapV1(restTemplateBuilder);
        try {
            return driver.crowl(Coin.BITCOIN);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }


    @GetMapping("/arzwsgov")
    public List<Price> getPriceArzwsGov() {
        DriverInterface driver = new ArzWsGov(restTemplateBuilder);
        try {
            return driver.crowl(Coin.USDOLLAR);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }


    @GetMapping("/arzwsmark")
    public List<Price> getPriceArzwsMarket() {
        DriverInterface driver = new ArzWsMarket(restTemplateBuilder);
        try {
            return driver.crowl(Coin.USDOLLAR);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }


    @GetMapping("/arzwscrypt")
    public List<Price> getPriceArzwsCrypto() {
        DriverInterface driver = new ArzWsCrypto(restTemplateBuilder);
        try {
            return driver.crowl(Coin.USDOLLAR);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }


    @GetMapping("/btcirr")
    public Price getBtcRial() {

        Price price = null;
        Price crypto = null;
        Price market = null;
        List<Price> priceListCrypto = getPriceArzwsCrypto();
        for (Price price1 : priceListCrypto) {
            if (price1.getCoin() == Coin.BITCOIN) {
                crypto = price1;
            }
        }
        List<Price> pricesMarket = getPriceArzwsMarket();
        for (Price price1 : pricesMarket) {
            if (price1.getCoin() == Coin.USDOLLAR) {
                market = price1;
            }
        }

        price = new Price();
        price.setCoin(Coin.BITCOIN);
        price.setDestCoin(Coin.IRANRIAL);
        price.setDriverName("GENERAL_COIN");
        price.setDate(market.getDate());

        BigDecimal bdBtcUsd = new BigDecimal(crypto.getPrice());
        BigDecimal bdUsdRial = new BigDecimal(market.getPrice());

BigDecimal bigDecimal = new BigDecimal("0.95");
        BigDecimal bdBtcRial = bdBtcUsd.multiply(bdUsdRial).multiply(bigDecimal);

        System.out.println("JEUSDEBUG: ======> "+bdBtcRial.toString());
        price.setPrice(bdBtcRial.toString());

        return price;

    }


    @GetMapping("/ethirr")
    public Price getEthRial() {

        Price price = null;
        Price crypto = null;
        Price market = null;
        List<Price> priceListCrypto = getPriceArzwsCrypto();
        for (Price price1 : priceListCrypto) {
            if (price1.getCoin() == Coin.ETHEREUM) {
                crypto = price1;
            }
        }
        List<Price> pricesMarket = getPriceArzwsMarket();
        for (Price price1 : pricesMarket) {
            if (price1.getCoin() == Coin.USDOLLAR) {
                market = price1;
            }
        }

        price = new Price();
        price.setCoin(Coin.ETHEREUM);
        price.setDestCoin(Coin.IRANRIAL);
        price.setDriverName("GENERAL_COIN");
        price.setDate(market.getDate());

        BigDecimal bdEthUsd = new BigDecimal(crypto.getPrice());
        BigDecimal bdUsdRial = new BigDecimal(market.getPrice());

        BigDecimal bigDecimal = new BigDecimal("0.95");
        BigDecimal bdEthRial = bdEthUsd.multiply(bdUsdRial).multiply(bigDecimal);

        System.out.println("JEUSDEBUG: ======> "+bdEthRial.toString());
        price.setPrice(bdEthRial.toString());

        return price;
    }

    @GetMapping("/check")
    public String check() {
        return "THIS IS LAST VERSION 1";
    }

}
