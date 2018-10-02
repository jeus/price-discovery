/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.crowler.drivers;

import com.b2mark.priceDiscovery.common.Coin;
import com.b2mark.priceDiscovery.entity.Price;

import java.util.List;

public interface DriverInterface {
    public List<Price> crowl(Coin... coins);
}
