package com.mangocity.api;

import java.util.List;
import java.util.Map;







import com.mangocity.base.IBaseService;
import com.mangocity.model.Ticket;
import com.mangocity.response.ResponseMessage;
import com.mangocity.vo.HBTicketVo;
import com.mangocity.vo.PassStationVo;
import com.mangocity.vo.RefundFeeVo;
import com.mangocity.vo.TicketInfoVo;
import com.mangocity.vo.PayParamsVo;


/**
 * 测试类
 * @author hongxiaodong
 *
 */
public interface ITrainTicketService extends IBaseService<Ticket>{
	
	/**
	* @Title: 车票改签申请  
	* @param hticket
	* @return 
	* ResponseMessage
	 */
	public ResponseMessage changeTrainTicket(HBTicketVo hticket);
	
	/**
	 * 接口：查询车次、余票及票价信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ResponseMessage queryTrainTicket(Map<String,Object> map) throws Exception;
	
	//获取途经站点信息
	public ResponseMessage queryPassStations(PassStationVo passStationVo) throws Exception;
	
	/**
	 * 火车票退票结果通知
	 * @param trainRefundNotifyVo
	 * @throws Exception
	 */
	public ResponseMessage checkTrainRefundNotify(String params) throws Exception;
	
	
	/**
	 * 火车票改签结果通知
	 * @param trainChangeNotifyVo
	 * @throws Exception
	 */
	public ResponseMessage checkTrainChangeNotify(String params) throws Exception;
	
	/**
	 * 
	* @Title: 查询订单明细  
	* @param orderNo
	* @return 
	* ResponseMessage
	 */
    public ResponseMessage searchTrainOrderDetail(String orderNo);
    
	 /**
	  * 
	 * @Title: changeTrainTicketConfirm  
	 * @param changOrderNo
	 * @return 
	 * ResponseMessage
	  */
    public ResponseMessage changeTrainTicketConfirm(String changOrderNo);
    
    /**
	 * 火车票退票
	 * @throws Exception
	 */
	public ResponseMessage createRefundTicket(PayParamsVo paramsVo,RefundFeeVo refundFeeVo) throws Exception;
	
	/**
	 * 月结审核通过之后通知号百申请退票
	 * @param orderItemId
	 * @param ticketNo
	 * @return
	 * @throws Exception
	 */
	public ResponseMessage checkRefundTicketTMON(Long orderItemId,String ticketNo) throws Exception;
	
	/**
	 * 火车票退单状态更新
	 */
	public ResponseMessage updateReturnTicketStatus(String refundOrderNo) throws Exception;
	
	
	/**
	 * 火车票改签单状态更新
	 */
	public ResponseMessage updateChangeTicketStatus(String changeOrderNo) throws Exception;
	
	/**
	 * 根据票ID获取票信息及乘机人信息
	 * @param id
	 * @return
	 */
	public TicketInfoVo findTicketInfoVoByid(Long id);
	
	/**
	 * 根据支付会话ID查询预订火车票
	 * @param payInfoId
	 * @param type
	 * @return
	 */
	public List<TicketInfoVo> findTicketByPayInfoId(Long payInfoId,String type);
	
	/**
	 * 根据订购项ID查询火车票
	 * @param orderItemId
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findTicketInfoVoByOrderItemId(Long orderItemId,String ticketNo) throws Exception;
	
	public List<TicketInfoVo> findTicketByOrderIdOrCd(Long orderId, String orderCd,String type,Long orderItemId) throws Exception;
	

	/**
	 * 根据号百订单号查询火车票
	 * 
	 * @param hbOrderId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findTicketByHbOrderId(String hbOrderId, String type) throws Exception;
	
	/**
	 * 根据ID更新火车票
	 * @param ticket
	 * @return
	 * @throws Exception
	 */
	public int updateTicket(Ticket ticket) throws Exception;
	
	//取消座位
	public ResponseMessage checkTrainCancel(String orderNo) throws Exception;
	
	/**
	 * 根据订单ID查询可以退票的火车票
	 * @param orderId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findCanRefundTicketByOrderId(Long orderId,String type) throws Exception;	
	
	
	/**
	 * 查询票信息
	 * @param orderCn 订单号
	 * @param type 0-订票 1-改签 2-退票
	 * @param status 票状状态信息
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findTicketInfoByOrderCd(String orderCn,String type,String status)throws Exception;


	/**
	 * 根据号百订单和乘客证件号码获得票信息
	 * @ITrainTicketService
	 * @Ticket
	 */
	public Ticket findTicketByHbIdAndPassengerId(Ticket ticket) throws Exception;
	
	/**
	 * 查询退票火车票
	 * @param orderId
	 * @param orderCd
	 * @param type
	 * @param orderItemId
	 * @return
	 * @throws Exception
	 */
	public List<TicketInfoVo> findRefundTicketByOrderIdOrCd(Long orderId, String orderCd,String type,Long orderItemId) throws Exception;
	
	/**
	 * 审核通过
	 * @param orderId
	 * @param ticketIds
	 * @return
	 * @throws Exception
	 */
	public ResponseMessage checkRefundTicket(Long orderId,List<Long> ticketIds) throws Exception;
}
