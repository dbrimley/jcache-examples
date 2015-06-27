package com.craftedbytes;

import com.hazelcast.cache.ICache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Cache Data Example
 */
public class CacheExample {

    private static final String CACHING_PROVIDER_EHCACHE = "org.ehcache.jcache.JCacheCachingProvider";
    private static final String CACHING_PROVIDER_HAZELCAST = "com.hazelcast.cache.HazelcastCachingProvider";
    private static final String CACHING_PROVIDER_COHERENCE = "com.tangosol.coherence.jcache.CoherenceBasedCachingProvider";


    public static void main(String[] args) {

        // Acquire the default cache provider
        CachingProvider cachingProvider = Caching.getCachingProvider(CACHING_PROVIDER_HAZELCAST);

        // Acquire the default cache manager
        CacheManager manager = cachingProvider.getCacheManager();

        // Define a cache
        MutableConfiguration<String, String> cacheConfig =
                new MutableConfiguration<String, String>()
                        .setStoreByValue(true)
                        .setTypes(String.class, String.class);


        // Create the cache
//         Cache<String,String> cache = manager.createCache("capitals", cacheConfig);

        // Get the cache
        Cache<String, String> cache = manager.getCache("capitals", String.class, String.class);

        // Enter some Capitals
        cache.put("UK", "London");
        cache.put("France", "Paris");
        cache.put("Spain", "Madrid");
        cache.put("Belgium", "Brussels");
        cache.put("Germany", "Berlin");

        // I forgot, whats the Capital of Belgium?
        System.out.println("JCache Capital of Belgium is : " + cache.get("Belgium"));

        // Unwrap to the Vendor API if you need to...(Hazelcast in this instance)
//         ICache<String,String> iCache = cache.unwrap(ICache.class);
//         System.out.println("ICache Capital of Belgium is : " + iCache.get("Belgium"));

    }

}
