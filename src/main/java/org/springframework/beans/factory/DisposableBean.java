package org.springframework.beans.factory;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
