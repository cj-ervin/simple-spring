package org.springframework.beans;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
