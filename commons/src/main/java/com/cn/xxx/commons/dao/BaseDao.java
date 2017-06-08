package com.cn.xxx.commons.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.cn.xxx.commons.model.AbstractBaseDO;

public class BaseDao<T extends AbstractBaseDO> extends HibernateDaoSupport {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDao.class);

    protected Class<T> clazz;

    protected BaseDao(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public Serializable saveOrUpdate(final T obj) {
        Serializable id = null;
        if (obj.getId() != null) {
            updateObject(obj);
        } else {
            id = insertObject(obj);
        }
//        LOGGER.info("Object "+obj.getClass().getName()+" saveOrUpdate,id: " + obj.getId());
        return id;
    }

    /**
     * 更新单个对象
     * @param obj
     * @return
     */
    public boolean updateObject(final T obj) {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        obj.setLastModifier(UserContext.getUserId());
        session.update(obj);
        LOGGER.info("Object "+obj.getClass().getName()+" update, id: " + obj.getId());
        session.flush();
        return true;
    }

    /**
     * 更新Collection
     * @param collections
     * @return
     */
    public boolean updateObjects(final Collection<T> collections) {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        for (T object : collections) {
            object.setLastUpdate();
            //object.setLastModifier(UserContext.getUserId());
            session.update(object);
            LOGGER.info("Object "+object.getClass().getName()+" update, id: " + object.getId());
        }
        session.flush();
        return true;
    }

    /**
     *
     * @param obj
     * @return
     */
    public Serializable insertObject(final T obj) {
        Validate.notNull(obj);
        obj.setCreated();
        obj.setDeleted(false);//是否删除
        obj.setLastUpdate(new Date());//最后修改时间
//        obj.setLastModifier(0L);//最后修改用户ID
        //obj.setCreator(UserContext.getUserId());//创建用户ID
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        final Serializable id = session.save(obj);
        LOGGER.info("New object "+obj.getClass().getName()+" added, id:" + id );
        session.flush();
        return id;
    }

    /**
     *
     * @param obj
     * @return
     */
    public Boolean insertObjects(final Collection<T> collections) {
        Validate.notNull(collections);
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        for (T object : collections) {
            object.setCreated();
            //object.setCreator(UserContext.getUserId());
            session.save(object);
            LOGGER.info("New object "+object.getClass().getName()+" added, id:" + object.getId() );
        }
        session.flush();
        return true;
    }

    /**
     * 条件查询
     * @param hql
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> queryObjectsByParams(String hql,Map<String,Object> params){
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(hql);
        this.getQuery(query,params);
        List<T> objectList = query.list();
        return objectList;
    }
    
    public Object query(String hql,Map<String,Object> params){
    	final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
    	Query query = session.createQuery(hql);
    	query = this.getQuery(query,params);
    	Object obj = query.uniqueResult();
        return obj;
    }
    
	@SuppressWarnings("unchecked")
	public List<T> query(String hql, int size, String... params) {
		final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		if (params != null) {
			int index = 0;
			for (String param : params) {
				query.setParameter(index++, param);
			}
		}
		query.setMaxResults(size);
		return (List<T>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> queryByMap(String hql, int size, Map<String,Object> params) {
		final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query = getQuery(query,params);
		query.setMaxResults(size);
		return (List<T>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> queryByMap(String hql, Map<String,Object> params) {
		final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query = getQuery(query,params);
		return (List<T>) query.list();
	}

  protected   Query getQuery(Query query,Map<String,Object> params){
        if(params!=null){
            for (String key : params.keySet()) {
            	if(params.get(key)!=null && !"".equals(params.get(key).toString())){
            		query.setParameter(key, params.get(key));
            	}
                
            }
        }
        return query;
    }
    /**
     * 逻辑删除
     * @param obj
     */
    public void deleteObjectLogical(final T obj) {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        obj.setDeleted(true);
        updateObject(obj);
        LOGGER.info("Object "+obj.getClass().getName()+" marked as deleted, id:" + obj.getId());
        session.flush();
    }

    /**
     * list 逻辑删除
     * @param col
     */
    public void deleteObjectsLogical(final Collection<T> collections) {
        for (final T object : collections) {
            deleteObjectLogical(object);
        }
    }

    public void internalUndelete(final T obj) {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        obj.setDeleted(false);
        obj.setLastUpdate();
        LOGGER.info("Object "+obj.getClass().getName()+" undeleted, id:" + obj.getId());
        session.update(obj);
        session.flush();
    }

    /**
     * 针对逻辑删除 数据恢复
     * @param col
     */
    public void internalUndelete(final Collection<T> collections) {
        for (final T obj : collections) {
            internalUndelete(obj);
        }
    }

    /**
     * 加载该表所有信息
     * @param clazz
     * @return
     */
    public List<T> queryAll(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        final List<T> list = (List<T>) getHibernateTemplate().find("from " + clazz.getSimpleName() + " t where t.deleted = 0");
        return list;
    }

    /**
     * 根据id加载object
     * @param entity
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
	public T queryObjectById(@SuppressWarnings("rawtypes") final Class entity, Serializable id) {
        return (T) this.getHibernateTemplate().get(entity, id);
    }

    /**
     *
     * @param hql
     * @param parameters
     * @return
     */
    public List<?> query(String hql, Object... parameters) {
        return this.getHibernateTemplate().find(hql, parameters);
    }

    public List<?> query(String hql, String[] paramNames, Object[] values) {
        return this.getHibernateTemplate().findByNamedParam(hql, paramNames, values);
    }

    protected void sortList(List<T> list) {
        // do nothing
    }

    protected String getQueryParamName(String key) {
        try {
            if (AbstractBaseDO.class.isAssignableFrom(this.clazz.getDeclaredField(key).getType()))
                return "t." + key + ".id";
        } catch (NoSuchFieldException e) {
            // do nothing
        } catch (SecurityException e) {
            // do nothing
        }
        return "t." + key;
    }

    protected Object getQueryParamValue(String key, Object value) {
        try {
            if (AbstractBaseDO.class.isAssignableFrom(this.clazz.getDeclaredField(key).getType()))
                return ((Number) value).intValue();
        } catch (NoSuchFieldException e) {
            // do nothing
        } catch (SecurityException e) {
            // do nothing
        }
        return value;
    }

    protected String getOperation(Map<String, Object> opMap, String key) {
        String operation = opMap == null ? null : (String) opMap.get(key);
        if (operation == null || operation.trim().length() == 0 || operation.trim().equals("==")) operation = "=";
        return operation;
    }



    protected void beforeGetList(Map<String, Object> map, Map<String, Object> searchMap, String order) {
        // do nothing
    }

    protected void afterGetList(List<?> list, Map<String, Object> paramMap, Map<String, Object> searchMap, String order) {
        // do nothing
    }

}
