/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.entity.coinmarketcap;

import com.b2mark.priceDiscovery.common.Coin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "symbol",
        "rank",
        "price_usd",
        "price_btc",
        "24h_volume_usd",
        "market_cap_usd",
        "available_supply",
        "total_supply",
        "max_supply",
        "percent_change_1h",
        "percent_change_24h",
        "percent_change_7d",
        "last_updated"
})
@Setter
@Getter
public class Coinmarketcap {

        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("symbol")
        private String symbol;
        @JsonProperty("rank")
        private String rank;
        @JsonProperty("price_usd")
        private String priceUsd;
        @JsonProperty("price_btc")
        private String priceBtc;
        @JsonProperty("24h_volume_usd")
        private String _24hVolumeUsd;
        @JsonProperty("market_cap_usd")
        private String marketCapUsd;
        @JsonProperty("available_supply")
        private String availableSupply;
        @JsonProperty("total_supply")
        private String totalSupply;
        @JsonProperty("max_supply")
        private String maxSupply;
        @JsonProperty("percent_change_1h")
        private String percentChange1h;
        @JsonProperty("percent_change_24h")
        private String percentChange24h;
        @JsonProperty("percent_change_7d")
        private String percentChange7d;
        @JsonProperty("last_updated")
        private String lastUpdated;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }


    @Override
    public String toString() {
        Gson json = new Gson();
        return json.toJson(this);
    }


    public Date getLastUpdated()
    {
        long longDate = Long.parseLong(lastUpdated);
        System.out.println("JEUSDEBUG:        LOOOOOOOOOOONG: "+longDate);
                Date date = new Date();
        System.out.println("JEUSDEBUG: LOøøøøøøøøøNg:"+date.getTime());
        date.setTime(longDate*1000);

        System.out.println("JEUSDEBUG: DATE––––––––––––≥≥≥≥≥≥≥ "+ date);
        return  date;
    }

    @JsonIgnoreProperties
    public Coin getCoin()
    {
        return Coin.fromSymbol(symbol);
    }

    @JsonIgnoreProperties
    public Coin getDestCoin()
    {
        return Coin.fromSymbol("USD");
    }
}
