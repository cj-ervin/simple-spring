package org.springframework.aop.framework;

import org.springframework.aop.AdvisedSupport;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public class ProxyFactory extends AdvisedSupport {


    public ProxyFactory() {
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (this.isProxyTargetClass() || this.getTargetSource().getTargetClass().length == 0) {
            return new CglibAopProxy(this);
        }

        return new JdkDynamicAopProxy(this);
    }
}
