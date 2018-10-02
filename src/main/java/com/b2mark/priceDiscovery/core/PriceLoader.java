package com.b2mark.priceDiscovery.core;

import com.b2mark.priceDiscovery.entity.Price;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Component
public class PriceLoader {
//    private final ReactiveRedisConnectionFactory factory;
//    private final ReactiveRedisOperations<String, Price> priceOps;
//
//    public PriceLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Price> priceOps) {
//        this.factory = factory;
//        this.priceOps = priceOps;
//    }
//
//    @PostConstruct
//    public void loadData() {
//        factory.getReactiveConnection().serverCommands().flushAll().thenMany(
//                Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
//                        .map(name -> new Price("US dollar", "USD", (new Date()), "BTC", "10000"))
//                        .flatMap(price -> priceOps.opsForValue().set(price.getId(), price)))
//                .thenMany(priceOps.keys("*")
//                        .flatMap(priceOps.opsForValue()::get))
//                .subscribe(System.out::println);
//    }
}