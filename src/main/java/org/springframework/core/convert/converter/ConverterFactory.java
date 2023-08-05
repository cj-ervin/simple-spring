package org.springframework.core.convert.converter;

/**
 * 类型转换工厂
 *
 * @Author ervin
 * @Date 2023/8/5
 */
public interface ConverterFactory<S, R> {

	<T extends R> Converter<S, T> getConverter(Class<T> targetType);
}