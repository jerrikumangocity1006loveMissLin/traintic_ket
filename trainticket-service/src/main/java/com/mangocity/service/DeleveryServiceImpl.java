/**
 * 
 */
package com.mangocity.service;

import java.util.List;

import com.mangocity.api.IDeliverySevice;
import com.mangocity.model.Delivery;
import com.mangocity.service.base.BaseServiceImpl;

/**
 * @author lizhi
 *
 */
public class DeleveryServiceImpl extends BaseServiceImpl<Delivery> implements IDeliverySevice {

	/* (non-Javadoc)
	 * @see com.mangocity.base.IBaseService#save(java.lang.Object)
	 */
	@Override
	public Long save(Delivery t) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mangocity.base.IBaseService#update(java.lang.Object)
	 */
	@Override
	public int update(Delivery t) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.mangocity.base.IBaseService#find(java.lang.Long)
	 */
	@Override
	public Delivery find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mangocity.base.IBaseService#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.mangocity.base.IBaseService#pageBySql(int, int)
	 */
	@Override
	public List<Delivery> pageBySql(int pageNum, int pageCount) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mangocity.api.IDeliverySevice#batchSaveDelevery(java.util.List)
	 */
	@Override
	public void batchSaveDelevery(List<Delivery> deliverys) {
		// TODO Auto-generated method stub

	}

}
