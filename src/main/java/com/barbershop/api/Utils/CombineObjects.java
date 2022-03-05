package com.barbershop.api.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CombineObjects<T> {
    public CombineObjects(){}

    /**
     *
     * @param o1 - Object given by the user
     * @param o2 - Object given from the Db
     * @return Merged object that has is null values replaced by the DB ones
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public T merge(T o1, T o2) throws IllegalAccessException, NoSuchFieldException {
        for(Field field : o2.getClass().getFields()){
            field.setAccessible(true);
            Object field1 = field.get(o1);
            Object field2 = field.get(o2);
            if(field1 == null)
                    field.set(o1,field2);
        }
        return o1;
    }

}
