/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.crowler.drivers;

import com.b2mark.priceDiscovery.entity.Price;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public abstract class Driver {

    protected String endpoint;
    protected RestTemplate restTemplate;

    public Driver(){

    }

    private List<Price> currencyPrices;


    protected abstract boolean crowl();

    public void addPrices(Price price) {
        currencyPrices.add(price);
    }

    public List<Price> getPrice() {
        return currencyPrices;
    }

}
