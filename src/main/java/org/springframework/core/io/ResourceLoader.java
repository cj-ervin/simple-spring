package org.springframework.core.io;

/**
 * 资源加载器接口
 *
 * @Author ervin
 * @Date 2023/8/5
 */
public interface ResourceLoader {

	Resource getResource(String location);
}
