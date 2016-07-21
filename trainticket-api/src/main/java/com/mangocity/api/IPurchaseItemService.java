package com.mangocity.api;

import com.mangocity.base.IBaseService;
import com.mangocity.model.PurchaseItem;

/**
 * @author lizhi
 *
 * @date 2016年6月1日
 */
public interface IPurchaseItemService extends IBaseService<PurchaseItem> {
    public void updatePurchaseItemByGoodsId(PurchaseItem purchaseItem);
}
