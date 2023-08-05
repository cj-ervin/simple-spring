package org.springframework.core.convert;

/**
 * 类型转换抽象接口
 *
 * @Author ervin
 * @Date 2023/8/5
 */
public interface ConversionService {

	boolean canConvert(Class<?> sourceType, Class<?> targetType);

	<T> T convert(Object source, Class<T> targetType);
}
