package com.mangocity.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.Account;

public interface AccountMapper extends BaseMapper<Account> {
	
	public Account queryAccount(Map map);
	
	public List<Account> queryAllInfos(@Param("startNum") int startNum,@Param("pageCount") int pageCount);

}
