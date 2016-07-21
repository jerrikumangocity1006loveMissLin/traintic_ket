package com.mangocity.base;

import java.util.List;

/**
 * 基类处理
 * @author hongxiaodong
 *
 * @param <T>
 */
public interface IBaseService<T> {
	
	/**
	 * 保存
	 * @param bank
	 */
	public Long save(T t);
	
	/**
	 * 更新
	 * @param bank
	 */
	public int update(T t);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public T find(Long id);
	
	
	/**
	 * 删除
	 * @param id
	 */
	public int delete(Long id);
	
	/**
	 * 分页查询
	 * @param pageNum   页码，从1开始
	 * @param pageCount 显示条数
	 * @return
	 */
	public List<T> pageBySql(int pageNum,int pageCount);
}
