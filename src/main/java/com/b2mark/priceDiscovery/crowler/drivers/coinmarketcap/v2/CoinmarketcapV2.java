/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.crowler.drivers.coinmarketcap.v2;

import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.crowler.drivers.Driver;
import com.b2mark.priceDiscovery.crowler.drivers.DriverInterface;
import com.b2mark.priceDiscovery.entity.Price;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CoinmarketcapV2 extends Driver implements DriverInterface {

    public CoinmarketcapV2(RestTemplateBuilder restTemplateBuilder) {
        super();
        drivername = "coinmarketcap-v2";
        endpoint = "https://api.coinmarketcap.com/v2/ticker/1/";
        restTemplate = restTemplateBuilder.build();
        supporterdCoin = new Coin[]{Coin.BITCOIN,Coin.ETHEREUM};
        sortSupportCoin();
    }

    @Override
    public List<Price> crowl(Coin... prices) {
        System.out.println("JEUSDEUG: -----------This  here");
        try {
            return getBtcPriceOld();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private List<Price> getBtcPriceOld() throws IOException {
        List<Price> priceList = new ArrayList<>();
        ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class);
        Date date = new Date();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode root1 = root.findPath("data");
        JsonNode root3 = root1.findPath("1");
        String name = root3.findPath("name").asText();
        String symbol = root3.findPath("symbol").asText();
        JsonNode root4 = root3.findPath("quotes");
        Iterator<String> strQueue = root4.fieldNames();
        while (strQueue.hasNext()) {
            Price price = new Price();
            price.setDate(date);
            price.setCoin(Coin.fromName(name));
            String strDestSymbol = strQueue.next();
            price.setDestCoin(Coin.fromSymbol(strDestSymbol));
            String priceStr = root4.findPath(strDestSymbol).findPath("price").asText();
            price.setPrice(priceStr);
            price.setDriverName(drivername);
            priceList.add(price);
        }
        return priceList;
    }
}
