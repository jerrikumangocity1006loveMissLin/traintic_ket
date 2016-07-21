package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.TrainInvoice;

public interface TrainInvoiceMapper extends BaseMapper<TrainInvoice> {
	public void savebatchTrainInvoice(@Param("list")List<TrainInvoice> trainInvoices);
	
	/**
	 * 根据订购项ID查找发票
	 * @param orderItemId
	 * @return
	 */
	public List<TrainInvoice> findInvoiceByOrderItemId(Long orderItemId);
}
