package com.mangocity.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.Ticket;
import com.mangocity.vo.TicketInfoVo;
import com.mangocity.vo.TicketVo;

public interface TrainTicketMapper extends BaseMapper<Ticket> {

	public void batchSaveTrainTicket(@Param("list") List<Ticket> trainickets);

	/**
	 * 根据订购项ID查询火车票
	 * 
	 * @param orderItemId
	 * @return
	 */
	public List<TicketVo> findTicketByOrderItemId(@Param("orderItemId") Long orderItemId,@Param("type")String type) throws Exception;

	/**
	 * 根据订单号或者订单ID查询火车票
	 * 
	 * @param orderId
	 * @param orderCn
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findTicketByOrderIdOrCd(@Param("orderId") Long orderId, @Param("orderCd") String orderCd,@Param("type")String type,@Param("orderItemId")Long orderItemId)
			throws Exception;

	/**
	 * 根据号百订单号查询火车票
	 * 
	 * @param hbOrderId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findTicketByHbOrderId(@Param("hbOrderId") String hbOrderId, @Param("type") String type)
			throws Exception;

	/**
	 * 获得长时间没得到通知的订单
	 * 
	 * @Title: findTicketByStatus
	 * @return List<Ticket>
	 */
	public List<Ticket> findTicketByStatus(@Param("nowdate") Date nowdate);
	
	/**
	 * 根据订购项ID查询火车票
	 * @param orderItemId
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findTicketInfoVoByOrderItemId(@Param("orderItemId") Long orderItemId,@Param("ticketNo")String ticketNo) throws Exception;
	
	/**
	 * 根据ID更新火车票
	 * @param ticket
	 * @return
	 * @throws Exception
	 */
	public int updateTicket(Ticket ticket) throws Exception;
	
	/**
	 * 回调时更新火车票
	 * @TrainTicketMapper
	 * @void
	 */
	public int updateByHbIdAndPassengerId(Ticket ticket);
	
	
	/**
	 * 根据ID查询所有票信息
	 * @param id
	 * @return
	 */
	public TicketInfoVo findTicketInfoById(@Param("id")Long id);

	
	/**
	 * 根据支付会话ID查询预订火车票
	 * @param payInfoId
	 * @param type
	 * @return
	 */
	public List<TicketInfoVo> findTicketByPayInfoId(@Param("payInfoId") Long payInfoId, @Param("type") String type);

	
	/**
	 * 根据订单ID查询可以退票的火车票
	 * @param orderId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findCanRefundTicketByOrderId(@Param("orderId") Long orderId,@Param("type") String type) throws Exception;
	
	/**
	 * 查询票信息
	 * @param orderCn 订单号
	 * @param type 0-订票 1-改签 2-退票
	 * @param status 票状状态信息
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findTicketInfoByOrderCd(@Param("orderCn") String orderCn,@Param("type") String type,@Param("status") String status)throws Exception;
	
	/**
	 * 根据号百订单和乘客证件更新订单
	 * @TrainTicketMapper
	 * @Ticket
	 */
	public Ticket findTicketByHbIdAndPassengerId(Ticket ticket);
	
	public void updateTicketById(Ticket ticket);
	
	/**
	 * 根据订单ID查询火车票
	 * @param orderItemId
	 * @return
	 * @throws Exception
	 */
	public TicketInfoVo findTicketInfoVoByTicketId(@Param("id")Long id) throws Exception;
	
	/**
	 * 查询退票火车票
	 * @param orderId
	 * @param orderCd
	 * @param type
	 * @param orderItemId
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findRefundTicketByOrderIdOrCd(@Param("orderId") Long orderId, @Param("orderCd") String orderCd,@Param("type")String type,@Param("orderItemId")Long orderItemId) throws Exception;
}
