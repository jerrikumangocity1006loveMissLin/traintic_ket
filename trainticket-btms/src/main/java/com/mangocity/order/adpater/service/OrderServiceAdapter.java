package com.mangocity.order.adpater.service;

import com.mangocity.btms.approval.adapter.vo.OrderVO;

import java.util.List;

/**
 * Date: 12-8-21
 * Time: 下午3:43
 *
 * @since: the version
 */
public interface OrderServiceAdapter {
    public List<OrderVO> retrieveOrdersByShipCd(String shipCd);

    public OrderVO retrieveOrderByOrderCd(String orderCd);
}
