/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.entity.arzws.gov;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "validationCheckResult",
        "exchangeRate"
})
@Setter
@Getter
public class ArzwsGovEntity {

    @JsonProperty("validationCheckResult")
    public ValidationCheckResult validationCheckResult;
    @JsonProperty("exchangeRate")
    @JsonAlias({"currencyBoard", "bazarExchange"})
    public List<ExchangeRate> exchangeRate = null;
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

}
