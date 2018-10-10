/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.price.driver;

import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.entity.Price;

public interface Market {
    public Price getPrice(Coin coin1, Coin coin2);
}
