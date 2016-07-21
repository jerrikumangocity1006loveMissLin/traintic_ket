package com.mangocity.api;

import java.util.List;

import com.mangocity.base.IBaseService;
import com.mangocity.model.Account;
import com.mangocity.response.ResponseMessage;
import com.mangocity.vo.AccountVo;

public interface IAccountService extends IBaseService<Account> {
	/**
	 * 获得所有12306账号和其常用联系人(账号池)
	 * 
	 * @IAccountService
	 * @List<AccountVo>
	 */
	public List<AccountVo> queryAllInfo(int startIndex, int pageSize);

	/**
	 * 删除12306账号 和常联系人
	 * 
	 * @IAccountService
	 * @ResponseMessage
	 */
	public ResponseMessage deleteAccount(Account account);

	/**
	 * 同步12306账号下的常用联系人到账号池
	 * 
	 * @IAccountService
	 * @ResponseMessage
	 */
	public ResponseMessage syschroAccountTravellers(Account account);

}
