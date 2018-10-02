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
import com.b2mark.priceDiscovery.crowler.drivers.coinmarketcap.v1.CoinmarketcapV1;
import com.b2mark.priceDiscovery.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {
//    private final ReactiveRedisOperations<String, Price> priceOps;

//    PriceController(ReactiveRedisOperations<String, Price> priceOps) {
//        this.priceOps = priceOps;
//    }

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @GetMapping("/btc")
    public List<Price> getPrice() {
        DriverInterface driver = new CoinmarketcapV1(restTemplateBuilder);
        try {
            return  driver.crowl(Coin.BITCOIN);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: This is get errrroooooorrr");
        }
        return null;
    }
//
//    @GetMapping("/price")
//    public Flux<Price> all() {
//        return priceOps.keys("*").flatMap(priceOps.opsForValue()::get);
//    }

}
