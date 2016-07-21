package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.Order;
import com.mangocity.vo.OrderBasisVo;
import com.mangocity.vo.OrderDetailBean;
import com.mangocity.vo.OrderTicketQuery;
import com.mangocity.vo.PageOrderParameter;

public interface TrainOrderMapper extends BaseMapper<Order> {

	/**
	 * 查询所有订单基础信息
	 * 
	 * @return
	 */
	public List<OrderBasisVo> queryOrderBasisList();

	/**
	 * 分页查询订单基础信息
	 * 
	 * @param startNum
	 * @param pageCount
	 * @return
	 */
	public List<OrderBasisVo> queryOrderBasisListByPage(@Param("pageOrderParameter")PageOrderParameter pageOrderParameter,@Param("startNum")int startNum, @Param("pageSize")int pageSize);
	
	/**
	 * 按条件分页查询
	 * @param orderTicketQuery
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public List<OrderBasisVo> queryOrderBasisListByOrderTicket(@Param("orderTicketQuery")OrderTicketQuery orderTicketQuery,@Param("startNum")int startNum, @Param("pageCount")int pageCount);
	
	/**
	 * 根据订单号查询订单详情
	 * @param orderCn
	 * @return
	 */
	public Order findOrderByOrderCn(@Param("orderCn")String orderCn);
	
	public Integer queryOrderNumber(@Param("pageOrderParameter")PageOrderParameter pageOrderParameter);
	
	public void updateOrderBasic(@Param("order")Order order);
	
	public void updateOrderPaymethod(@Param("order")Order order);
	
	/**
	 * 修改订单状态
	 * @param order
	 */
	public void updateOrderStatus(Order order);
	
	/**
	 * 查询退票订单
	 * @param pageOrderParameter
	 * @param startNum
	 * @param pageSize
	 * @return
	 */
	public List<OrderBasisVo> queryRefundOrderBasisListByPage(@Param("pageOrderParameter")PageOrderParameter pageOrderParameter,@Param("startNum")int startNum, @Param("pageSize")int pageSize);

	/**
	 * 查询退票订单数
	 * @param pageOrderParameter
	 * @return
	 */
	public Integer queryRefundOrderNumber(@Param("pageOrderParameter")PageOrderParameter pageOrderParameter);
	
}
