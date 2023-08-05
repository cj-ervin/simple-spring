package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源的抽象和访问接口
 *
 * @Author ervin
 * @Date 2023/8/5
 */
public interface Resource {

	InputStream getInputStream() throws IOException;

}
