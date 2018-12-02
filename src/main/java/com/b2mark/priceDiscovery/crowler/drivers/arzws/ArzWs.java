/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.crowler.drivers.arzws;

import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.crowler.drivers.Driver;
import com.b2mark.priceDiscovery.crowler.drivers.DriverInterface;
import com.b2mark.priceDiscovery.entity.Price;
import com.b2mark.priceDiscovery.entity.arzws.gov.ExchangeRate;
import com.b2mark.priceDiscovery.entity.arzws.gov.ArzwsGovEntity;


import java.util.*;

public abstract class ArzWs extends Driver implements DriverInterface {

    private static final long TICKS_AT_EPOCH = 621355968000000000L;
    private static final long TICKS_PER_MILLISECOND = 10000;
    Map<String, String> map = new HashMap<>();

    public ArzWs() {
        map.put("bazar_aed", "AED");
        map.put("bazar_afn", "AFN");
        map.put("bazar_amd", "AMD");
        map.put("bazar_aud", "AUD");
        map.put("bazar_azn", "AZN");
        map.put("bazar_bhd", "BHD");
        map.put("bazar_cad", "CAD");
        map.put("bazar_chf", "CHF");
        map.put("bazar_cny", "CNY");
        map.put("bazar_dkk", "DKK");
        map.put("bazar_eur", "EUR");
        map.put("bazar_gbp", "GBP");
        map.put("bazar_hkd", "HKD");
        map.put("bazar_inr", "INR");
        map.put("bazar_iqd", "IQD");
        map.put("bazar_jpy", "JPY");
        map.put("bazar_kwd", "KWD");
        map.put("bazar_myr", "MYR");
        map.put("bazar_nok", "NOK");
        map.put("bazar_omr", "OMR");
        map.put("bazar_qar", "QAR");
        map.put("bazar_rub", "RUB");
        map.put("bazar_sar", "SAR");
        map.put("bazar_sek", "SEK");
        map.put("bazar_sgd", "SGD");
        map.put("bazar_thb", "THB");
        map.put("bazar_try", "TRY");
        map.put("bazar_uex", "UEX");
        map.put("bazar_usd", "USD");
    }

    @Override
    public List<Price> crowl(Coin... coins) {
        List<Price> prices = new ArrayList<>();
        ArzwsGovEntity arzwsGovEntity = null;
        try {
            arzwsGovEntity = restTemplate.getForObject(endpoint, ArzwsGovEntity.class);
        } catch (Exception ex) {
            System.out.println("JEUSDEBUG: ERROR   " + ex.getMessage());
        }
        List<Coin> listCoin = Arrays.asList(coins);
        for (ExchangeRate exchangeRate : arzwsGovEntity.getExchangeRate()) {
            String symbol = exchangeRate.getIcon().length() > 3 ? map.get(exchangeRate.getIcon()) : exchangeRate.getIcon();
            if(!listCoin.contains(Coin.fromSymbol(symbol))) {
                continue;
            }
            exchangeRate.setIcon(symbol);
            Price price = convertToPrice(exchangeRate);
            if (price != null) {
                prices.add(price);
            }
        }
        return prices;
    }

    private Price convertToPrice(ExchangeRate exchangeRate) {
        Price price = new Price();
        String symbol = exchangeRate.getIcon().length() > 3 ? map.get(exchangeRate.getIcon()) : exchangeRate.getIcon();
        Coin sourceCoin = Coin.fromSymbol(symbol);
        Coin destCoin = drivername.equals("arzws-crypto") ? Coin.USDOLLAR : Coin.IRANRIAL;
        if (sourceCoin != null && isSupport(sourceCoin) > 0) {
            Date date = new Date((exchangeRate.getDateSerial() - TICKS_AT_EPOCH) / TICKS_PER_MILLISECOND);
            price.setDate(date);
            price.setCoin(sourceCoin);
            price.setPrice(Double.toString(exchangeRate.getCurrent()));
            price.setDestCoin(destCoin);
            price.setDriverName(drivername);
            return price;
        } else {
            return null;
        }
    }
}
