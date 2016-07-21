package com.mangocity.api;

import com.mangocity.base.IBaseService;
import com.mangocity.model.Charge;

/**
 * 服务类
 * @author hongxiaodong
 *
 */
public interface IChargeService extends IBaseService<Charge>{
	
	/**
	 * 查询服务
	 * @param id
	 * @return
	 */
	public Charge findByName(String name);

}
