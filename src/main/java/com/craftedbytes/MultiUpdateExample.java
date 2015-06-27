package com.craftedbytes;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import java.util.Iterator;

/**
 * Caching Provider Example
 */
public class MultiUpdateExample {

    private static final String CACHING_PROVIDER_EHCACHE = "org.ehcache.jcache.JCacheCachingProvider";
    private static final String CACHING_PROVIDER_HAZELCAST = "com.hazelcast.cache.HazelcastCachingProvider";
    private static final String CACHING_PROVIDER_COHERENCE = "com.tangosol.coherence.jcache.CoherenceBasedCachingProvider";


    public static void main(String[] args) {

        // Acquire the default cache provider
        // CachingProvider cachingProvider = Caching.getCachingProvider();

        // Acquire an explicit cache provider
        CachingProvider cachingProvider = Caching.getCachingProvider(CACHING_PROVIDER_HAZELCAST);

        // Acquire the default cache manager
        CacheManager manager = cachingProvider.getCacheManager();

        // Define a cache
        MutableConfiguration<String, String> cacheConfig =
                new MutableConfiguration<String, String>()
                        .setStoreByValue(true)
                        .setTypes(String.class, String.class);

        // Create the cache
        Cache<String, String> cache = manager.createCache("capitals", cacheConfig);

        // Enter some Capitals
        cache.put("UK", "London");
        cache.put("France", "Paris");
        cache.put("Spain", "Madrid");
        cache.put("Belgium", "Brussels");
        cache.put("Germany", "Berlin");

        // Convert Capitals to Upper Case

        // Iterate and Update (Also look! No locks available in JCache, using CAS update with replace).
        Iterator<Cache.Entry<String, String>> iterator = cache.iterator();
        while (iterator.hasNext()) {
            Cache.Entry<String, String> cacheEntry = iterator.next();
            String value = cacheEntry.getValue();
            String key = cacheEntry.getKey();
            String upperCase = value.toUpperCase();
            cache.replace(key, value, upperCase);
        }

        // Now Check capital
        System.out.println("Capital of Belgium is : " + cache.get("Belgium"));

    }

}
