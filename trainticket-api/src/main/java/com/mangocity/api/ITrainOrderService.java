package com.mangocity.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mangocity.base.IBaseService;
import com.mangocity.model.Delivery;
import com.mangocity.model.Order;
import com.mangocity.model.OrderItem;
import com.mangocity.response.ResponseMessage;
import com.mangocity.vo.OrderBasisVo;
import com.mangocity.vo.OrderDetailBean;
import com.mangocity.vo.OrderTicketPaInfoVo;
import com.mangocity.vo.OrderTicketQuery;
import com.mangocity.vo.OrderVo;
import com.mangocity.vo.PageOrderParameter;
import com.mangocity.vo.PageQueryResult;
import com.mangocity.vo.TicketInfoVo;

public interface ITrainOrderService extends IBaseService<Order> {
	
	/**
	* @Title: 预定火车票 
	* @param orderVo
	* @return 
	* ResponseMessage
	 */
	 public ResponseMessage createTrainOrder(OrderVo orderVo);
	
	 /**
	 * @Title: 预定火车票   
	 * @param orderJson
	 * @return 
	 * ResponseMessage
	  */
	 public ResponseMessage createTrainOrder(String orderJson);
	 
	 /** 
	 * @Title: 添加 配送项
	 * @param deliveryId
	 * @param orderId
	 * @param date
	 * @return 
	 * ResponseMessage
	  */
	 public ResponseMessage addDeliveryItem(Delivery delivery, long orderId,Date date);
	 
	 /**
	 * @Title: 火车票预定通知接口  
	 * @return 
	 * ResponseMessage
	  */
	 public ResponseMessage bookTicketNotify(String bookResult);
   
   /**
    * 根据订单号查询订单详情
    * @param orderCn
    * @return
    * @throws Exception
    */
   public OrderDetailBean queryOrderDeatilByOrderCn(String orderCn) throws Exception;
   
   /**
    * 根据订单ID查询订单详情
    * @param orderId
    * @return
    * @throws Exception
    */
   public OrderDetailBean queryOrderDeatilByOrderId(Long orderId) throws Exception;
   
   /**
    * 查询所有订单基础信息
    * @return
    */
   public List<OrderBasisVo> queryOrderBasisList();
   
   /**
    * 分页查询订单基础信息
    * @param pageNum
    * @param pageCount
    * @return
    */
  // public List<QueryOrderVo> queryPageOrder(int currentPage,int pageSize);
   public PageQueryResult<OrderBasisVo> queryOrderBasisListByPage(PageOrderParameter pageOrderParameter);
   
   
   /**
    * 根据查询条件查询订单基础信息
    * @param orderTicketQuery
    * @return
    */
   public List<OrderBasisVo> queryOrderBasisListByOrderTicket(OrderTicketQuery orderTicketQuery,int pageNum, int pageCount);
   
   
   /**
    * 根据订单号查询行程信息
    * @param orderCn
    * @return
    * @throws Exception
    */
   public List<TicketInfoVo> queryTicketInfoByOrderCn(String orderCn)throws Exception;
   
   
   /**
    * 根据订单ID查询行程信息
    * @param orderId
    * @return
    * @throws Exception
    */
   public List<TicketInfoVo> queryTicketInfoByOrderId(Long orderId)throws Exception;
   
   /**
    * 获得不同状态的订单数量
    * @ITrainOrderService
    * @Map<String,Integer>
    */
   public  Map<String,Integer> queryDifferentStatusOrderNum(String orderStatus,String payStatus)throws Exception;
   
   /**
    * 更新订单基本信息
    * @ITrainOrderService
    * @void
    */
   public  void updateOrderBasicInfo(OrderDetailBean orderDetail)throws Exception;
   
   public void updateOrderPaymethod(Order order);
   
   /**
    * 修改订单状态
    * @param order
    * @throws Exception
    */
   public void updateOrderStatus(Order order);
   
   public List<OrderItem> queryOrderIetmsByOrderId(Long orderId);
   
   
   /**
    * 查询订单及票信息
    * @param orderCn
    * @return
    */
   public OrderTicketPaInfoVo queryOrderTicketByOrderCn(String orderCn)throws Exception;
   
   /**
    * 根据订单号查订单信息
    * @param orderCn
    * @return
    */
   public Order findOrderByOrderCn(String orderCn);
   
   /**
    * 查询退票订单列表
    * @param pageOrderParameter
    * @return
    * @throws Exception
    */
   public PageQueryResult<OrderBasisVo> queryRefundOrderBasisListByPage(PageOrderParameter pageOrderParameter) throws Exception;
   
   /**
	 * 获取退票订单状态数
	 * @return
	 * @throws Exception
	 */
   public Map<String, Integer> queryDifferentStatusRefundOrderNum(String status) throws Exception;
   
}
