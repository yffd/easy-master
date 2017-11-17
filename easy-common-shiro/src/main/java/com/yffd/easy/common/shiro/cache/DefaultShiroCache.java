package com.yffd.easy.common.shiro.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月9日 上午10:24:14 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DefaultShiroCache<K, V> implements Cache<K, V> {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultShiroCache.class);

	@Override
	public V get(K key) throws CacheException {
		LOG.info("=========shiro DefaultShiroCache [get] key:" + key);
		return null;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		LOG.info("=========shiro DefaultShiroCache [put] key:" + key + ", value:" + value);
		return null;
	}

	@Override
	public V remove(K key) throws CacheException {
		LOG.info("=========shiro DefaultShiroCache [remove] key:" + key);
		return null;
	}

	@Override
	public void clear() throws CacheException {
		LOG.info("=========shiro DefaultShiroCache [clear]");
		
	}

	@Override
	public int size() {
		LOG.info("=========shiro DefaultShiroCache [size]");
		return 0;
	}

	@Override
	public Set<K> keys() {
		LOG.info("=========shiro DefaultShiroCache [keys]");
		return null;
	}

	@Override
	public Collection<V> values() {
		LOG.info("=========shiro DefaultShiroCache [values]");
		return null;
	}

}

