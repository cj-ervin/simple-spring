package org.springframework.core.convert.converter;

/**
 * 类型转换抽象接口
 *
 * @Author ervin
 * @Date 2023/8/5
 */
public interface Converter<S, T> {

	/**
	 * 类型转换
	 */
	T convert(S source);
}
