package com.cn.xxx.commons.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cn.xxx.commons.model.AbstractBaseDO;

public class CollectionUtil {
    public static <E extends AbstractBaseDO> List<E> sortById(List<E> list,final String desc){
    	Collections.sort(list, new Comparator<E>(){
			@Override
			public int compare(E e1, E e2) {
				if(desc.toLowerCase().equals("desc")){
					return e2.getId().compareTo(e1.getId());
				}else{
					return e1.getId().compareTo(e2.getId());
				}
			}
    	});
    	return list;
    }
    public static <E extends AbstractBaseDO> Set<E> sortById(Set<E> set,final String desc){
    	List<E> list = sortById(new ArrayList<E>(set), desc);
    	return new HashSet<E>(list);
    }
    public static <E extends AbstractBaseDO> List<E> removeDeletedAndSortById(List<E> list,final String desc){
    	removeDeleted(list);
    	
    	return sortById(list, desc);
    }
    public static <E extends AbstractBaseDO> Set<E> removeDeletedAndSortById(Set<E> set,final String desc){
    	removeDeleted(set);
    	return sortById(set, desc);
    	
    }
    public static <E extends AbstractBaseDO> void sortByFieldName(List<E> list,final String fieldName,final String desc){
    	Collections.sort(list, new Comparator<E>(){
			@Override
			public int compare(E e1, E e2) {
				Object fild1 = getFieldValue(fieldName,e1);
				Object fild2 = getFieldValue(fieldName,e2);
				if(fild1 == null || fild2 == null){
					return 0;
				}else if(fild1 instanceof String || fild1 instanceof Long ||fild1 instanceof Integer){
					if(desc.toLowerCase().equals("desc")){
						return ((String)fild1).compareTo((String)fild2);
					}else{
						return ((String)fild2).compareTo((String)fild1);
					}
				}else{
					return 0;
				}
			}
    	});
    }
    public static <E extends AbstractBaseDO> void removeDeleted(Collection<E> list){
    	Iterator<E> it = list.iterator();  
		while(it.hasNext()) {  
			E ele = it.next();
			if(ele.isDeleted()){
				it.remove();
			}
		}  
    }
    /** 
     * 根据属性名获取属性值 
     * */  
	private static Object getFieldValue(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}
    
}

