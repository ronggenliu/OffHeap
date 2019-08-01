import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.ehcache.Cache;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public class HelloHeapServiceImpl implements HelloHeapService {

    private static Map<String, InHeapClass> inHeapCache = Maps.newHashMap();

    private static Cache<String, OffHeapClass> offHeapCache;

    static {
        ResourcePools resourcePools = ResourcePoolsBuilder.newResourcePoolsBuilder()
                .offheap(1, MemoryUnit.MB)
                .build();

        CacheConfiguration<String, OffHeapClass> configuration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, OffHeapClass.class, resourcePools)
                .build();

        offHeapCache = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("cacher", configuration)
                .build(true)
                .getCache("cacher", String.class, OffHeapClass.class);


        for (int i = 1; i < 10001; i++) {
            inHeapCache.put("InHeapKey" + i, new InHeapClass("InHeapKey" + i, "InHeapValue" + i));
            offHeapCache.put("OffHeapKey" + i, new OffHeapClass("OffHeapKey" + i, "OffHeapValue" + i));
        }
    }

    @Data
    @AllArgsConstructor
    private static class InHeapClass implements Serializable {
        private String key;
        private String value;
    }

    @Data
    @AllArgsConstructor
    private static class OffHeapClass implements Serializable {
        private String key;
        private String value;
    }

    @Override
    public void helloHeap() {
        System.out.println(JSON.toJSONString(inHeapCache.get("InHeapKey1")));
        System.out.println(JSON.toJSONString(offHeapCache.get("OffHeapKey1")));
        Iterator iterator = offHeapCache.iterator();
        int sum = 0;
        while (iterator.hasNext()) {
            System.out.println(JSON.toJSONString(iterator.next()));
            sum++;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) throws InterruptedException {
        HelloHeapService helloHeapService = new HelloHeapServiceImpl();
        helloHeapService.helloHeap();
        Thread.sleep(10 * 60 * 1000);
    }
}