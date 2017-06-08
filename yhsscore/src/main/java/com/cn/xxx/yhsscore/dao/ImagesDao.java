package com.cn.xxx.yhsscore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.ImagesDO;

@Repository
public class ImagesDao extends BaseDao<ImagesDO> {
	@Autowired
	private ImageDao imageDao ;
	
	public ImagesDao() {
		super(ImagesDO.class);
	}
	
	public void saveImagesDO(ImagesDO images){
		if(images.getList()!=null && images.getList().size()>0){
			this.imageDao.insertObjects(images.getList());
		}
		this.saveOrUpdate(images);
	}

}