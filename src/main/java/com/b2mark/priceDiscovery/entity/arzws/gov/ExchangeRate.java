package com.b2mark.priceDiscovery.entity.arzws.gov;

import java.util.HashMap;
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
"id",
"name",
"icon",
"current",
"minVal",
"maxVal",
"insertDate",
"dateSerial",
"differencePercentage",
"differenceValue"
})
@Setter
@Getter
public class ExchangeRate {

@JsonProperty("id")
public Long id;
@JsonProperty("name")
public String name;
@JsonProperty("icon")
public String icon;
@JsonProperty("current")
public Double current;
@JsonProperty("minVal")
public Double minVal;
@JsonProperty("maxVal")
public Double maxVal;
@JsonProperty("insertDate")
public String insertDate;
@JsonProperty("dateSerial")
public Long dateSerial;
@JsonProperty("differencePercentage")
public Object differencePercentage;
@JsonProperty("differenceValue")
public String differenceValue;
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


public Coin getCoin()
{
    return Coin.fromSymbol(icon);
}

}