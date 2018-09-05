/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.crowler.drivers.coinmarketcap;

import com.b2mark.priceDiscovery.crowler.drivers.Driver;
import com.b2mark.priceDiscovery.entity.Price;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import java.io.IOException;

public class Coinmarketcap extends Driver {


    public Coinmarketcap(RestTemplateBuilder restTemplateBuilder) {
        super();
        endpoint = "https://api.coinmarketcap.com/v2/ticker/?convert=BTC&limit=1";
        restTemplate = restTemplateBuilder.build();
    }

    @Override
    protected boolean crowl() {
        return false;
    }

    public String getBtcPrice() throws IOException {
        Date date = new Date();
        List<Price> priceList = new ArrayList<>();
        ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode root1 = root.findPath("data");
        JsonNode root3 = root1.findPath("1");
        String name = root3.findPath("name").asText();
        String symbol = root3.findPath("symbol").asText();
        JsonNode root4 = root3.findPath("quotes");
        Iterator<String> strQueue = root.fieldNames();
        while (strQueue.hasNext()) {
            Price price = new Price();
            price.setDate(date);
            price.setName(name);
            price.setSymbol(symbol);
            String strDestSymbol = strQueue.next();
            price.setDestSymbol(strDestSymbol);
            String priceStr = root4.findPath(strDestSymbol).findPath("price").asText();
            price.setPrice(priceStr);
        }
        return "OK";
    }


    public String getBtcPrice1() throws IOException {
        Price price = new Price();
        price.setDate(new Date());
        ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        Iterator<String> strQueue = root.fieldNames();

        while (strQueue.hasNext()) {
            System.out.println("-----------" + strQueue.next());
        }
        System.out.println(price);
        return "OK";
    }


}
