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
import com.b2mark.priceDiscovery.crowler.drivers.nerkhapi.currency.NerkhApiDriversCurrency;
import com.b2mark.priceDiscovery.entity.Price;
import com.b2mark.priceDiscovery.price.driver.MarketDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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


    @GetMapping("/btceur")
    public Price getBtcEuro() {
        DriverInterface driver = new CoinmarketcapV1(restTemplateBuilder);
        ((CoinmarketcapV1) driver).setToCoin(Coin.EURO);
        try {
            return driver.crowl(Coin.BITCOIN).get(0);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }


    @GetMapping("/etheur")
    public Price getEthEuro() {
        DriverInterface driver = new CoinmarketcapV1(restTemplateBuilder);
        ((CoinmarketcapV1) driver).setToCoin(Coin.EURO);
        try {
            return driver.crowl(Coin.ETHEREUM).get(0);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }


    @GetMapping("/btcusd")
    public List<Price> getBtcUsd() {
        DriverInterface driver = new CoinmarketcapV1(restTemplateBuilder);
        try {
            return driver.crowl(Coin.BITCOIN);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }


    @GetMapping("/ethusd")
    public Price getEthUsd() {
        DriverInterface driver = new CoinmarketcapV1(restTemplateBuilder);
        try {
            return driver.crowl(Coin.ETHEREUM).get(0);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }




    @GetMapping("/arzwsgov")
    public List<Price> getArzwsGov() {
        DriverInterface driver = new ArzWsGov(restTemplateBuilder);
        try {
            return driver.crowl(Coin.USDOLLAR);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }


    @GetMapping("/arzwsmark")
    public List<Price> getArzwsMarket() {
        DriverInterface driver = new ArzWsMarket(restTemplateBuilder);
        try {
            return driver.crowl(Coin.USDOLLAR,Coin.EURO);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }


   @GetMapping("/nerkhapi")
    public List<Price> getNerkhApi() {
        DriverInterface driver = new NerkhApiDriversCurrency(restTemplateBuilder);
        try {
            return driver.crowl(Coin.USDOLLAR,Coin.EURO);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }


    @GetMapping("/arzwscrypt")
    public List<Price> getArzwsCrypto() {
        DriverInterface driver = new ArzWsCrypto(restTemplateBuilder);
        try {
            return driver.crowl(Coin.USDOLLAR);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }
    @GetMapping("/tbtcirr")
    public Price getTbtcRial() {
       return getBtcRial();
    }

    @GetMapping("/btcirr")
    public Price getBtcRial() {
        Price price;
        Price crypto = null;
        Price market = null;
        List<Price> priceListCrypto = getBtcUsd();
        for (Price price1 : priceListCrypto) {
            if (price1.getCoin() == Coin.BITCOIN) {
                crypto = price1;
            }
        }
        List<Price> pricesMarket = getNerkhApi();
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

        BigDecimal bigDecimal = new BigDecimal("0.99");
        BigDecimal bdBtcRial = bdBtcUsd.multiply(bdUsdRial).multiply(bigDecimal);

        System.out.println("JEUSDEBUG: ======> "+formatter(bdBtcRial));
        price.setPrice(formatter(bdBtcRial));

        return price;
    }


    @GetMapping("/irrbtc")
    public Price getRialBtc() {
        Price btcIrrPrice = getBtcRial();

        Price price  = new Price();
        price.setCoin(Coin.IRANRIAL);
        price.setDestCoin(Coin.BITCOIN);
        price.setDriverName("GENERAL_COIN");
        price.setDate(btcIrrPrice.getDate());

        BigDecimal unit  = new BigDecimal("1");
        BigDecimal btcIrr = new BigDecimal(btcIrrPrice.getPrice());
        BigDecimal irrBtc = unit.divide(btcIrr,18,BigDecimal.ROUND_DOWN);

        System.out.println("JEUSDEBUG: ======> "+formatter(irrBtc));
        price.setPrice(formatter(irrBtc));

        return price;
    }


    @GetMapping("/usdbtc")
    public Price getUsdBtc() {
        Price btcUsdPrice = getBtcUsd().get(0);

        Price price  = new Price();
        price.setCoin(Coin.USDOLLAR);
        price.setDestCoin(Coin.BITCOIN);
        price.setDriverName("GENERAL_COIN");
        price.setDate(btcUsdPrice.getDate());

        BigDecimal unit  = new BigDecimal("1");
        BigDecimal btcUsd = new BigDecimal(btcUsdPrice.getPrice());
        BigDecimal usdBtc = unit.divide(btcUsd,18,BigDecimal.ROUND_DOWN);

        System.out.println("JEUSDEBUG: ======> "+formatter(usdBtc));
        price.setPrice(formatter(usdBtc));

        return price;
    }

    @GetMapping("/eurbtc")
    public Price getEurBtc() {
        Price btcEurPrice = getBtcEuro();

        Price price  = new Price();
        price.setCoin(Coin.EURO);
        price.setDestCoin(Coin.BITCOIN);
        price.setDriverName("GENERAL_COIN");
        price.setDate(btcEurPrice.getDate());

        BigDecimal unit  = new BigDecimal("1");
        BigDecimal btcEur = new BigDecimal(btcEurPrice.getPrice());
        BigDecimal eurBtc = unit.divide(btcEur,18,BigDecimal.ROUND_DOWN);

        System.out.println("JEUSDEBUG: ======> "+formatter(eurBtc));
        price.setPrice(formatter(eurBtc));

        return price;
    }


    @GetMapping("/ethirr")
    public Price getEthRial() {
        Price price;
        Price crypto = null;
        Price market = null;
        Price price1 = getEthUsd();
            if (price1.getCoin() == Coin.ETHEREUM) {
                crypto = price1;
            }
        List<Price> pricesMarket = getNerkhApi();
        for (Price price2 : pricesMarket) {
            if (price2.getCoin() == Coin.USDOLLAR) {
                market = price2;
            }
        }
        price = new Price();
        price.setCoin(Coin.ETHEREUM);
        price.setDestCoin(Coin.IRANRIAL);
        price.setDriverName("GENERAL_COIN");
        price.setDate(market.getDate());

        BigDecimal bdEthUsd = new BigDecimal(crypto.getPrice());
        BigDecimal bdUsdRial = new BigDecimal(market.getPrice());

        BigDecimal bigDecimal = new BigDecimal("0.99");
        BigDecimal bdEthRial = bdEthUsd.multiply(bdUsdRial).multiply(bigDecimal);

        System.out.println("JEUSDEBUG: ======> "+formatter(bdEthRial));
        price.setPrice(formatter(bdEthRial));

        return price;
    }


    @GetMapping("/irreth")
    public Price getRialEth() {
        Price ethIrrPrice = getEthRial();

        Price price  = new Price();
        price.setCoin(Coin.IRANRIAL);
        price.setDestCoin(Coin.ETHEREUM);
        price.setDriverName("GENERAL_COIN");
        price.setDate(ethIrrPrice.getDate());

        BigDecimal unit  = new BigDecimal("1");
        BigDecimal ethIrr = new BigDecimal(ethIrrPrice.getPrice());
        BigDecimal irrEth = unit.divide(ethIrr,18,BigDecimal.ROUND_DOWN);

        System.out.println("JEUSDEBUG: ======> "+formatter(irrEth));
        price.setPrice(formatter(irrEth));

        return price;
    }


    @GetMapping("/usdeth")
    public Price getUsdEth() {
        Price ethUsdPrice = getEthUsd();

        Price price  = new Price();
        price.setCoin(Coin.USDOLLAR);
        price.setDestCoin(Coin.ETHEREUM);
        price.setDriverName("GENERAL_COIN");
        price.setDate(ethUsdPrice.getDate());

        BigDecimal unit  = new BigDecimal("1");
        BigDecimal btcUsd = new BigDecimal(ethUsdPrice.getPrice());
        BigDecimal usdEth = unit.divide(btcUsd,18,BigDecimal.ROUND_DOWN);

        System.out.println("JEUSDEBUG: ======> "+formatter(usdEth));
        price.setPrice(formatter(usdEth));

        return price;
    }

    @GetMapping("/eureth")
    public Price getEurEth() {
        Price ethEurPrice = getEthEuro();

        Price price  = new Price();
        price.setCoin(Coin.EURO);
        price.setDestCoin(Coin.ETHEREUM);
        price.setDriverName("GENERAL_COIN");
        price.setDate(ethEurPrice.getDate());

        BigDecimal unit  = new BigDecimal("1");
        BigDecimal btcUsd = new BigDecimal(ethEurPrice.getPrice());
        BigDecimal eurEth = unit.divide(btcUsd,18,BigDecimal.ROUND_DOWN);

        System.out.println("JEUSDEBUG: ======> "+formatter(eurEth));
        price.setPrice(formatter(eurEth));

        return price;
    }


    @GetMapping("/usdirr")
    public Price getUsdollarRial(){

        Price market = null;

        List<Price> pricesMarket = getNerkhApi();
        for (Price price1 : pricesMarket) {
            if (price1.getCoin() == Coin.USDOLLAR) {
                market = price1;
            }
        }

        return market;

    }

    @GetMapping("/eurirr")
    public Price getEuroRial(){

        Price market = null;

        List<Price> pricesMarket = getNerkhApi();
        for (Price price1 : pricesMarket) {
            if (price1.getCoin() == Coin.EURO) {
                market = price1;
            }
        }

        return market;
    }

    @GetMapping("/check")
    public String check() {
        return "THIS IS LAST VERSION 1";
    }


    private String formatter(BigDecimal bigDecimal) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(18);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        return df.format(bigDecimal);
    }
}
