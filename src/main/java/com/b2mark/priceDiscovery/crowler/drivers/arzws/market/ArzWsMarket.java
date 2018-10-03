/**
 * <h1>Arzws rial to usd webservice</h1>
 * Govermental price
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.crowler.drivers.arzws.market;

import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.crowler.drivers.arzws.ArzWs;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.*;

public class ArzWsMarket extends ArzWs {

    private static final long TICKS_AT_EPOCH = 621355968000000000L;
    private static final long TICKS_PER_MILLISECOND = 10000;
    Map<String, String> map = new HashMap<>();

    public ArzWsMarket(RestTemplateBuilder restTemplateBuilder) {
        super();
        drivername = "arzws-market";
        endpoint = "http://core.arzws.com/api/core?Token=78cd7981-c23d-4f84-6995-08d627c4638e&what=bazarex";
        restTemplate = restTemplateBuilder.build();
        supporterdCoin = new Coin[]{Coin.IRANRIAL,Coin.USDOLLAR,Coin.SYRIANPOUND};
        sortSupportCoin();
    }
}
