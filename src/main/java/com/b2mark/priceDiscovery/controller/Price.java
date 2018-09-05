/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.controller;


import com.b2mark.priceDiscovery.crowler.drivers.Driver;
import com.b2mark.priceDiscovery.crowler.drivers.coinmarketcap.Coinmarketcap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.io.IOException;

@RestController
@RequestMapping("/price")
public class Price {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @GetMapping("/btc")
    public String getPrice() {
        Driver driver = new Coinmarketcap(restTemplateBuilder);
        try {
            ((Coinmarketcap) driver).getBtcPrice1();
        } catch (IOException ex) {

        }
        return "ok";
    }


}
