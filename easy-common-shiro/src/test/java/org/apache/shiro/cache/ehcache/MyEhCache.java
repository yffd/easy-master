package org.apache.shiro.cache.ehcache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月9日 上午10:00:17 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MyEhCache<K, V> implements Cache<K, V> {
	public MyEhCache(net.sf.ehcache.Ehcache cache) {
        if (cache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
    }
	@Override
	public V get(K key) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}

