/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.crowler.drivers.nerkhapi.currency;

import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.crowler.drivers.nerkhapi.NerkhApiDrivers;
import org.springframework.boot.web.client.RestTemplateBuilder;

public class NerkhApiDriversCurrency extends NerkhApiDrivers {

    public NerkhApiDriversCurrency(RestTemplateBuilder restTemplateBuilder) {
        super();
        drivername = "nerkhapi-currency";
        endpoint = "http://api.backino.net/nerkhapi/api/28178b4dc903566dc47418845963a54e/currency/";
        restTemplate = restTemplateBuilder.build();
        supporterdCoin = new Coin[]{Coin.USDOLLAR,Coin.SYRIANPOUND,Coin.EURO};
        sortSupportCoin();
    }

}
