package com.mangocity.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mangocity.api.IPurchaseItemService;
import com.mangocity.mapper.PurchaseItemMapper;
import com.mangocity.model.PurchaseItem;
import com.mangocity.service.base.BaseServiceImpl;

public class PurchaseItemServiceImpl extends BaseServiceImpl<PurchaseItem> implements IPurchaseItemService {

	@Autowired
	private PurchaseItemMapper purchaseItemMapper;

	@Override
	public void updatePurchaseItemByGoodsId(PurchaseItem purchaseItem) {
		purchaseItemMapper.updatePurchaseItemByGoodsId(purchaseItem);
	}

}
