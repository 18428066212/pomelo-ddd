package com.pomelo.ddd.starter.creator;

import com.pomelo.ddd.core.bean.Creator;
import com.pomelo.ddd.starter.util.SpringUtil;


public class BeanCreatorForSpring implements Creator {

    @Override
    public Object create(Class<?> aClass) {

        return SpringUtil.getBean(aClass);
    }

}
