package com.craftedbytes;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

/**
 * Caching Provider Example
 */
public class CachingProviderExample {

    private static final String CACHING_PROVIDER_EHCACHE = "org.ehcache.jcache.JCacheCachingProvider";
    private static final String CACHING_PROVIDER_HAZELCAST = "com.hazelcast.cache.HazelcastCachingProvider";
    private static final String CACHING_PROVIDER_COHERENCE = "com.tangosol.coherence.jcache.CoherenceBasedCachingProvider";


    public static void main(String[] args) {

        // Acquire the default cache provider
        CachingProvider cachingProvider1 = Caching.getCachingProvider();

        // Acquire an explicit cache provider
        // CachingProvider cachingProvider = Caching.getCachingProvider(CACHING_PROVIDER_HAZELCAST);

        // Acquire the default cache manager
        CacheManager manager1 = cachingProvider1.getCacheManager();

    }

}
