package com.mangocity.order.adpater.service.impl;

import com.mangocity.btms.approval.adapter.service.OrderAdapterService;
import com.mangocity.btms.approval.adapter.vo.OrderQry;
import com.mangocity.btms.approval.adapter.vo.OrderVO;
import com.mangocity.order.adpater.service.OrderServiceAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Date: 12-8-21
 * Time: 下午3:45
 *
 * @since: the version
 */
public class OrderServiceAdapterImpl implements OrderServiceAdapter{
    private OrderAdapterService orderAdapterService;
    public List<OrderVO> retrieveOrdersByShipCd(String shipCd) {
        OrderQry orderQry = new OrderQry();
        orderQry.setShipCd(shipCd);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(new Date());
        orderQry.setBeginDate(today+" 00:00:00");
        orderQry.setEndDate(today+" 23:59:59");
        return orderAdapterService.retrieveTicketOrderByQry(orderQry);
    }

    public OrderVO retrieveOrderByOrderCd(String orderCd) {
        return orderAdapterService.retrieveTicketOrderByOrderCd(orderCd);
    }

    public void setOrderAdapterService(OrderAdapterService orderAdapterService) {
        this.orderAdapterService = orderAdapterService;
    }
}
