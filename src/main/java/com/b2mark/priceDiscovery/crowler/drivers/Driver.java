/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.crowler.drivers;

import com.b2mark.priceDiscovery.common.Coin;
import org.springframework.web.client.RestTemplate;


public abstract class Driver {

    protected String drivername;
    protected String endpoint;
    protected RestTemplate restTemplate;
    protected Coin[] supporterdCoin;

    protected void sortSupportCoin() {
        int n = supporterdCoin.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (supporterdCoin[j].getId() > supporterdCoin[j + 1].getId()) {
                    // swap temp and arr[i]
                    Coin temp = supporterdCoin[j];
                    supporterdCoin[j] = supporterdCoin[j + 1];
                    supporterdCoin[j + 1] = temp;
                }
    }

    protected int coinSupported(Coin coin) {
        System.out.println("JEUS FIND--------");
        int l = 0, r = supporterdCoin.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            System.out.println("JEUS FIND--------∆∆∆∆∆∆∆∆∆∆∆");

            // Check if x is present at mid
            if (supporterdCoin[m].getId() == coin.getId())
                return supporterdCoin[m].getId();

            System.out.println("JEUS FIND--------∆∆∆∆∆∆∆∆∆∆∆");
            // If x greater, ignore left half
            if (supporterdCoin[m].getId() < coin.getId())
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        return -1;
    }


}
