package com.craftedbytes;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;
import javax.cache.processor.MutableEntry;
import javax.cache.spi.CachingProvider;
import java.util.Iterator;
import java.util.Map;

/**
 * Caching Provider Example
 */
public class EntryProcessorUpdateExample {

    private static final String CACHING_PROVIDER_EHCACHE = "org.ehcache.jcache.JCacheCachingProvider";
    private static final String CACHING_PROVIDER_HAZELCAST = "com.hazelcast.cache.HazelcastCachingProvider";


    public static void main(String[] args) {

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
        cache.invoke("Belgium", new EntryProcessor<String, String, Object>() {
            public Object process(MutableEntry<String, String> entry, Object... arguments) throws EntryProcessorException {
                if (entry.exists()) {
                    entry.setValue(entry.getValue().toUpperCase());
                }
                return null;
            }
        });

        // Now Check capital
        System.out.println("Capital of Belgium is : " + cache.get("Belgium"));

    }

}
