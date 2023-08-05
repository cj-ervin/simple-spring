package org.springframework.core.convert.converter;

/**
 * 类型转换器注册接口
 *
 * @Author ervin
 * @Date 2023/8/5
 */
public interface ConverterRegistry {

	void addConverter(Converter<?, ?> converter);

	void addConverterFactory(ConverterFactory<?, ?> converterFactory);

	void addConverter(GenericConverter converter);
}
