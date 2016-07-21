/**
 * 
 */
package com.mangocity.api;

import java.util.List;

import com.mangocity.base.IBaseService;
import com.mangocity.model.Delivery;

/**
 * @author lizhi
 *
 */
public interface IDeliverySevice  extends IBaseService<Delivery>{
   public void batchSaveDelevery(List<Delivery> deliverys);
}
