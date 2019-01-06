
package com.b2mark.priceDiscovery.entity.nerkhapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.b2mark.priceDiscovery.common.Coin;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GEL",
    "AMD",
    "AZN",
    "RUB",
    "THB",
    "MYR",
    "HKD",
    "SGD",
    "PKR",
    "INR",
    "SYP",
    "BHD",
    "IQD",
    "OMR",
    "QAR",
    "SAR",
    "KWD",
    "NOK",
    "DKK",
    "SEK",
    "AFN",
    "CHF",
    "NZD",
    "AUD",
    "CAD",
    "JPY",
    "CNY",
    "TRY",
    "AED",
    "GBP",
    "EUR",
    "USD",
    "current"
})

@Getter
@Setter
public class Prices {

    @JsonProperty("GEL")
    private Currency gEL;
    @JsonProperty("AMD")
    private Currency aMD;
    @JsonProperty("AZN")
    private Currency aZN;
    @JsonProperty("RUB")
    private Currency rUB;
    @JsonProperty("THB")
    private Currency tHB;
    @JsonProperty("MYR")
    private Currency mYR;
    @JsonProperty("HKD")
    private Currency hKD;
    @JsonProperty("SGD")
    private Currency sGD;
    @JsonProperty("PKR")
    private Currency pKR;
    @JsonProperty("INR")
    private Currency iNR;
    @JsonProperty("SYP")
    private Currency sYP;
    @JsonProperty("BHD")
    private Currency bHD;
    @JsonProperty("IQD")
    private Currency iQD;
    @JsonProperty("OMR")
    private Currency oMR;
    @JsonProperty("QAR")
    private Currency qAR;
    @JsonProperty("SAR")
    private Currency sAR;
    @JsonProperty("KWD")
    private Currency kWD;
    @JsonProperty("NOK")
    private Currency nOK;
    @JsonProperty("DKK")
    private Currency dKK;
    @JsonProperty("SEK")
    private Currency sEK;
    @JsonProperty("AFN")
    private Currency aFN;
    @JsonProperty("CHF")
    private Currency cHF;
    @JsonProperty("NZD")
    private Currency nZD;
    @JsonProperty("AUD")
    private Currency aUD;
    @JsonProperty("CAD")
    private Currency cAD;
    @JsonProperty("JPY")
    private Currency jPY;
    @JsonProperty("CNY")
    private Currency cNY;
    @JsonProperty("TRY")
    private Currency tRY;
    @JsonProperty("AED")
    private Currency aED;
    @JsonProperty("GBP")
    private Currency gBP;
    @JsonProperty("EUR")
    private Currency eUR;
    @JsonProperty("USD")
    private Currency uSD;
    @JsonProperty("current")
    private Currency current;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonIgnore
    public Currency getByCoin(Coin coin){
        switch (coin) {
            case EURO:
                return getEUR();
            case USDOLLAR:
                return getUSD();
            case SYRIANPOUND:
                return sYP;
                default:
                    return null;
        }

    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
