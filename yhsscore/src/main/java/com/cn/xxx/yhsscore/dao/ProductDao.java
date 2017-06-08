package com.cn.xxx.yhsscore.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.ProductDO;

/**
 * @author xulong
 */
@Repository
public class ProductDao extends BaseDao<ProductDO> {

	protected ProductDao() {
		super(ProductDO.class);
	}

	public List<ProductDO> queryAllProductsByPage(int pagesize, int pagenum) {
		// TODO Auto-generated method stub
		int limitstart = (pagenum - 1) * pagesize;
		int limitcount = pagesize;
		String query = "from ProductDO p order by u.created desc ";
		Query myquery = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query);
		myquery.setFirstResult(limitstart);
		myquery.setMaxResults(limitcount);
		List<ProductDO> list = (List<ProductDO>) myquery.list();
		return list;
	}

	public ProductDO queryProductByProductNO(String productNo) {
		// TODO Auto-generated method stub
		String hql = "from ProductDO p where p.deleted=false and p.productNo = :productNo";
		ProductDO product = (ProductDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("productNo", productNo).uniqueResult();
		return product;
	}

	public ProductDO queryAllProductsByName(String name) {
		// TODO Auto-generated method stub
		String hql = "FROM ProductDO p where p.name = :name and p.deleted = false";
		ProductDO prod = (ProductDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("name", name).uniqueResult();
		return prod;
	}

	public ProductDO queryProductsByStatus(String status) {
		// TODO Auto-generated method stub
		String hql = "from ProductDO u where u.deleted=false and u.status = :status";
		ProductDO product = (ProductDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("status", status).uniqueResult();
		return product;
	}

	public String queryProductPriceByNo(String productNo) {
		// TODO Auto-generated method stub
		try {
			String hql="FROM ProductDO u where p.deleted = false and p.productNo = :productNo";
			ProductDO p = (ProductDO)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("productNo", productNo).uniqueResult();
			return p.getPrice(); 
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public ProductDO queryProductByOrderId(String orderId) {
		// TODO Auto-generated method stub
		String hql = "from ProductDO p where p.orderId = :orderId";
		ProductDO product = (ProductDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("orderId", orderId).uniqueResult();
		return product;
	}

}
