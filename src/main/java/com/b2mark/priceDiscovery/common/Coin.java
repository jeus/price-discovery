/**
 * <h1></h1>
 *
 * @author b2mark
 * @version 1.0
 * @since 2018
 */

package com.b2mark.priceDiscovery.common;

import lombok.Getter;
import reactor.util.annotation.Nullable;


@Getter
public enum Coin {
    BITCOIN(1, "bitcoin","BTC",Type.CRYPTO),
    ETHEREUM(2, "ethereum","ETH",Type.CRYPTO),
    USDOLLAR(3, "usdollar","USD",Type.FIAT);


    private final int id;
    private final String name;
    private final String symbol;
    private final Type type;

    private Coin(int id, String name,String symbol,Type type) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.type = type;
    }

    public String toString() {
        return this.name;
    }

    public static Coin id(int id) {
        Coin coin = fromId(id);
        if (coin == null) {
            throw new IllegalArgumentException("No matching constant for [" + id + "]");
        } else {
            return coin;
        }
    }


    @Nullable
    public static Coin fromId(int id) {
        Coin[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Coin coin = var1[var3];
            if (coin.id == id) {
                return coin;
            }
        }
        return null;
    }


    @Nullable
    public static Coin fromSymbol(String symbol) {
        for (Coin coin : values()) {
            if (coin.getSymbol().equalsIgnoreCase(symbol)) {
                return coin;
            }
        }
        return null;
    }

    @Nullable
    public static Coin fromName(String name) {
        for (Coin coin : values()) {
            if (coin.getName().equalsIgnoreCase(name)) {
                return coin;
            }
        }
        return null;
    }

    @Getter
    public static enum Type {
        FIAT("Fiat"),
        CRYPTO("Crypto"),
        TOKEN("Token");

        private final String name;

        private Type(String name) {
            this.name = name;
        }
        public String toString() {
            return this.name;
        }
    }

}
