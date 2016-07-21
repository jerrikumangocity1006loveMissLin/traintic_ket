package com.mangocity.mapper;

import com.mangocity.model.PurchaseItem;

/**
 * @author lizhi
 *
 * @date 2016年6月1日
 */
public interface PurchaseItemMapper extends BaseMapper<PurchaseItem> {
  public void updatePurchaseItemByGoodsId(PurchaseItem purchaseItem);
}
