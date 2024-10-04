package gjxt.portal.apigateway.sercurity.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {



	@Autowired
	private RedisTemplate redisTemplate;

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		//设置完这个可以直接将对象以json格式存入redis中，但是取出来的时候要用JSON.parseArray(Json.toJsonString(object),Object.class)解析一下
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		//调用后完成设置
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	/**
	 * 加锁
	 */
	public boolean lock(String key, String value) {
		//setIfAbsent相当于jedis中的setnx，如果能赋值就返回true，如果已经有值了，就返回false
		//即：在判断这个key是不是第一次进入这个方法
		if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
			//第一次，即：这个key还没有被赋值的时候
			return true;
		}
		String current_value = (String) redisTemplate.opsForValue().get(key);
		if (!current_value.equals("") && Long.parseLong(current_value) < System.currentTimeMillis()) {	//超时了

			String old_value = (String) redisTemplate.opsForValue().getAndSet(key, value);
			if (!old_value.equals("") && old_value.equals(current_value)) {
				return true;
			}
		}
		return false;
	}
	//解锁
	public void unlock(String key, String value) {
		try {
			if (redisTemplate.opsForValue().get(key).toString().equals(value)) {
				redisTemplate.opsForValue().getOperations().delete(key);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 有则取出key值所对应的值
	public String getValueByKey(String key){
		return (String) redisTemplate.opsForValue().get(key);

	}

	// 设置当前的key以及value值
	public void set(String key,String value){
		redisTemplate.opsForValue().set(key, value);
	}

	public void set(String key,String value,long timeout){
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.DAYS);

	}
	
	public void setValueExpireMinutes(String key,String value,long timeout){
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MINUTES);

	}

	public void setValueExpireSeconds(String key,String value,long timeout){
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}
	/**
	* 从redis中获取key对应的过期时间;
	* 如果该值有过期时间，就返回相应的过期时间; 单位：秒
	* 如果该值没有设置过期时间，就返回-1;
	* 如果没有该值，就返回-2;
	 * @return 
	*/
	public Long getKeyExpire(String key){
		return  redisTemplate.opsForValue().getOperations().getExpire(key);

	}
	
	//  删除key
	public Boolean delete(String key){
		return redisTemplate.delete(key);

	}


	// 有则取出key值所对应的值
	public Object getObjectByKey(String key){
		return  redisTemplate.opsForValue().get(key);

	}

	// 设置当前的key以及value值
	public void setObject(String key,Object value){
		redisTemplate.opsForValue().set(key, value);

	}

	// 存list
	public void setList(String key,List<String> list){
		redisTemplate.opsForList().rightPushAll(key, list);

	}
	// 通过leftPop(K key)方法 移除集合中的左边第一个元素。
	public String getListByKey(String key) {
		return (String) redisTemplate.opsForList().leftPop(key);
	}

	// 获取list大小
	public Long getListSize(String key) {
		Long size = redisTemplate.opsForList().size(key);
		return size;

	}

	// 存入map
	public void put(String key,String hashKey,Object value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}
	// 存入map
	public void putAll(String key,Map<String,String> map) {
		redisTemplate.opsForHash().putAll(key, map);
	}
	// 取map
	public Map<String,String>  getMap(String key) {
		return redisTemplate.opsForHash().entries(key); 
	}

	// 取map:通过get(H key, Object hashKey)方法获取map键的值
	public Object getMapValue(String key,String hashKey) {
		return redisTemplate.opsForHash().get(key, hashKey);
	}

	// 删除变量中的键值对，可以传入多个参数，删除多个键值对。
	public void remove(String key, Object... hashKeys) {
		redisTemplate.opsForHash().delete(key, hashKeys);
	}

	// map size
	public int sizeOfMap(String key) {
		return  redisTemplate.opsForHash().entries(key).size();  
	}

	/**
	 * 监听key过期事件
	 */
	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(factory);
		return container;
	}

	public long getExpire(String tradeNo) {
		Long expire = redisTemplate.getExpire(tradeNo);
		return expire;
	}

	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	public void increment(String key) {
		redisTemplate.opsForValue().increment(key);
	}
}
