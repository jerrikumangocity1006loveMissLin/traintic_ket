package com.mangocity.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.base.IBaseService;
import com.mangocity.mapper.BaseMapper;

public class BaseServiceImpl<T> implements IBaseService<T> {
	
	@Autowired
	private BaseMapper<T> baseMapper;

	/**
	 * 查询
	 */
	
	public T find(Long id) {
		
		return baseMapper.find(id);
	}

	
	public Long save(T t) {

		return baseMapper.save(t);
	}

	
	public int update(T t) {
		
		return baseMapper.update(t);
	}

	
	public int delete(Long id) {
		
		return baseMapper.delete(id);
	}
	
	/**
	 * 分页查询
	 * @param pageNum   页码，从1开始
	 * @param pageCount 显示条数
	 * @return
	 */
	
	public List<T> pageBySql(int pageNum, int pageCount) {
		int startNum = 0;
		if(pageNum>0){
			startNum = (pageNum-1)*pageCount;
		}
		return baseMapper.pageBySql(startNum, pageCount);
	}

}
