/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.entity;


import com.b2mark.priceDiscovery.common.Coin;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    Coin coin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss:SS", timezone = "UTC")
    Date date;
    Coin destCoin;
    String price;
    String driverName;


    /**
     * create id by format [symbol]_to_[destSymbol]
     * @return
     */
    public String getId() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(coin).append("_to_").append(destCoin).append("_").append(driverName);
        return strBuilder.toString();
    }
}
