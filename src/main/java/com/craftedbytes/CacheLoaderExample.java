package com.craftedbytes;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Cache Data Example
 */
public class CacheLoaderExample {

    private static final String CACHING_PROVIDER_EHCACHE = "org.ehcache.jcache.JCacheCachingProvider";
    private static final String CACHING_PROVIDER_HAZELCAST = "com.hazelcast.cache.HazelcastCachingProvider";
    private static final String CACHING_PROVIDER_COHERENCE = "com.tangosol.coherence.jcache.CoherenceBasedCachingProvider";

    public static void main(String[] args) {

        // Acquire the default cache provider
        //CachingProvider cachingProvider = Caching.getCachingProvider();
        CachingProvider cachingProvider = Caching.getCachingProvider(CACHING_PROVIDER_COHERENCE);

        // Acquire the default cache manager
        CacheManager manager = cachingProvider.getCacheManager();

        // Define a cache
        MutableConfiguration<String, String> cacheConfig =
                new MutableConfiguration<String, String>().setStoreByValue(true)
                        .setTypes(String.class, String.class)
                        .setCacheLoaderFactory(FactoryBuilder.factoryOf(MyCacheLoader.class))
                        .setReadThrough(true);


        // Create the cache
        Cache<String, String> cache = manager.createCache("capitals", cacheConfig);

        // Enter some Capitals
        cache.put("UK", "London");
        cache.put("France", "Paris");
        cache.put("Spain", "Madrid");
        cache.put("Belgium", "Brussels");
        cache.put("Germany", "Berlin");

        // I forgot, what's the Capital of Portugal?
        System.out.println("Capital of Portugal is : " + cache.get("Portugal"));

    }

}
