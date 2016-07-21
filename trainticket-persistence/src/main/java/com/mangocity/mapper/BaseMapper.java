package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 基础mapper接口，实现CRUD基本方法
 * @author hongxiaodong
 *
 * @param <T>
 */
public interface BaseMapper<T> {
	
	/**
	 * 保存
	 * @param t
	 * @return
	 */
	public Long save(T t);
	
	/**
	 * 更新
	 * @param t
	 * @return
	 */
	public int update(T t);
	
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public T find(@Param("id")Long id);
	
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delete(@Param("id")Long id);
	
	
	/**
	 * 分页查询
	 * @param startNum开始页数
	 * @param pageCount查询的数量
	 * @return
	 */
	public List<T> pageBySql(@Param("startNum") int startNum,@Param("pageCount") int pageCount);

}
