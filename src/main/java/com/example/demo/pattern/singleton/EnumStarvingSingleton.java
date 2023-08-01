package com.example.demo.pattern.singleton;

/**
 * 枚举单例
 * @Author ervin
 * @Date 2023/8/1
 */
public class EnumStarvingSingleton {

    private EnumStarvingSingleton() {
    }

    private enum EnumStarvingSingletonHolder {
        HOLDER;
        private EnumStarvingSingleton instance;
        EnumStarvingSingletonHolder() {
            instance = new EnumStarvingSingleton();
        }
    }

    public static EnumStarvingSingleton getInstance() {
        return EnumStarvingSingletonHolder.HOLDER.instance;
    }
}
