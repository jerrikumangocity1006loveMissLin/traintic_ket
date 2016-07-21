package com.mangocity.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mangocity.api.IPurchaseService;
import com.mangocity.enums.GoodsType;
import com.mangocity.mapper.ChargeMapper;
import com.mangocity.mapper.DeliveryMapper;
import com.mangocity.mapper.PurchaseDetailInfoMapper;
import com.mangocity.mapper.PurchaseItemMapper;
import com.mangocity.mapper.PurchaseMapper;
import com.mangocity.mapper.TrainOrderItemMapper;
import com.mangocity.mapper.TrainTicketMapper;
import com.mangocity.model.Charge;
import com.mangocity.model.Delivery;
import com.mangocity.model.Insurance;
import com.mangocity.model.OrderItem;
import com.mangocity.model.Purchase;
import com.mangocity.model.PurchaseDetailInfo;
import com.mangocity.model.PurchaseItem;
import com.mangocity.response.ResponseMessage;
import com.mangocity.service.base.BaseServiceImpl;
import com.mangocity.vo.TicketVo;

public class PurchaseServiceImpl extends BaseServiceImpl<Purchase> implements IPurchaseService{
	
	Logger logger = Logger.getLogger(PurchaseServiceImpl.class);
	
	@Autowired
	private PurchaseMapper purchaseMapper;
	
	@Autowired
	private PurchaseItemMapper purchaseItemMapper;
	
	@Autowired
	private PurchaseDetailInfoMapper purchaseDetailInfoMapper;
	
	@Autowired
	private TrainOrderItemMapper trainOrderItemMapper;
	
	@Autowired
	private ChargeMapper chargeMapper;
	
	@Autowired
	private DeliveryMapper deliveryMapper;
	
	@Autowired
	private TrainTicketMapper trainTicketMapper;
	
	/**
	 * 创建采购信息
	 * @param orderCn订单号
	 * @param orderItemIds订购项ID
	 * @param hbOrderId号百订单号
	 * type 类型 0-订票 1-改签 2-退票
	 * @throws Exception
	 */
	@Override
	public ResponseMessage createPurchase(String orderCn,List<Long> orderItemIds,String type) throws Exception{
		logger.info("采购接口createPurchase参数orderCn" + orderCn + ",orderItemIds=" + orderItemIds);
		ResponseMessage responseMessage = new ResponseMessage();
		if(StringUtils.isNotEmpty(orderCn) && null != orderItemIds && orderItemIds.size() > 0){
			try {
				long currentTime = System.currentTimeMillis();
				Date date = new Date(currentTime);
				Date endDate = new Date(currentTime + 25 * 60 * 1000);//25分钟
				//采购
				Purchase purchase = null;
				List<Purchase> pList = purchaseMapper.findPurchaseByOrderCn(orderCn);
				BigDecimal amount = new BigDecimal("0");
				if(null != pList && pList.size() > 0){
					purchase = pList.get(0);
				}else{
					purchase = new Purchase();
					purchase.setOrderCn(orderCn);
					purchase.setAmount(amount);
					purchase.setStatus("0");//采购状态：0采购中
					purchase.setCreateTime(date);
					purchase.setEndTime(endDate);
					purchaseMapper.save(purchase);
				}
				
				//采购项
				List<OrderItem> orderItemList = trainOrderItemMapper.findOrderItemListById(orderItemIds);
				if(null != orderItemList && orderItemList.size() > 0){
					for(OrderItem orderItem : orderItemList){
						amount = amount.add(orderItem.getPrice() != null ? orderItem.getPrice() : new BigDecimal("0"));
						String goodsType = orderItem.getGoodsType();
						Long orderItemId = orderItem.getId();
						//采购价格明细
					    if(goodsType.equalsIgnoreCase(GoodsType.CHARGE.toString())){//服务费
					    	List<Charge> charge = chargeMapper.findByOrderItemId(orderItemId);
					    	if(null != charge && charge.size() > 0){
					    		for(Charge c : charge){
					    			BigDecimal cPrice = new BigDecimal("0");
					    			cPrice.add(c.getPrice() != null ? c.getPrice() : new BigDecimal("0"));
					    			//采购项
					    			PurchaseItem item = new PurchaseItem();
									item.setPurchaseId(purchase.getId());
									item.setGoodsId(c.getId());
									item.setGoodsType(goodsType);
									item.setStatus("0");//采购状态：0采购中
									item.setAmount(cPrice);
									item.setOrderNo(orderCn);
									item.setCreateTime(date);
									purchaseItemMapper.save(item);
									//采购价格明细
					    			PurchaseDetailInfo info = new PurchaseDetailInfo();
									info.setPurchaseItemId(item.getId());
									info.setType(goodsType);
									info.setPriceType("1");//价格类型：0销售价格类型，1服务费，2配送费，3保险费
									info.setAmount(cPrice);
									info.setAcount(1);
									purchaseDetailInfoMapper.save(info);
					    		}
					    	}
						}else if(goodsType.equalsIgnoreCase(GoodsType.DELIVERY.toString())){//配送
							List<Delivery> delivery = deliveryMapper.findDeliveryByOrderItemId(orderItemId);
							if(null != delivery && delivery.size() > 0){
								for(Delivery d : delivery){
									BigDecimal dPrice = new BigDecimal("0");
									dPrice.add(d.getFee() != null ? d.getFee() : new BigDecimal("0"));
									//采购项
									PurchaseItem item = new PurchaseItem();
									item.setPurchaseId(purchase.getId());
									item.setGoodsId(d.getId());
									item.setGoodsType(goodsType);
									item.setStatus("0");//采购状态：0采购中
									item.setAmount(dPrice);
									item.setOrderNo(orderCn);
									item.setCreateTime(date);
									purchaseItemMapper.save(item);
									//采购价格明细
									PurchaseDetailInfo info = new PurchaseDetailInfo();
									info.setPurchaseItemId(item.getId());
									info.setType(goodsType);
									info.setPriceType("2");//价格类型：0销售价格类型，1服务费，2配送费，3保险费
									info.setAmount(dPrice);
									info.setAcount(1);
									purchaseDetailInfoMapper.save(info);
								}
							}
						}else if(goodsType.equalsIgnoreCase(GoodsType.TICKET.toString())){//火车票
							List<TicketVo> ticketList = trainTicketMapper.findTicketByOrderItemId(orderItemId,type);
							if(null != ticketList && ticketList.size() > 0){
								for(TicketVo ticketVo : ticketList){
									if(ticketVo.getTicket() != null){
										//采购项
										PurchaseItem item = new PurchaseItem();
										item.setPurchaseId(purchase.getId());
										item.setGoodsId(ticketVo.getTicket().getId());
										item.setGoodsType(goodsType);
										item.setStatus("0");//采购状态：0采购中
										BigDecimal salePrice = new BigDecimal("0");
										salePrice = salePrice.add(ticketVo.getTicket().getSalePrice() != null ? ticketVo.getTicket().getSalePrice() : new BigDecimal("0"));
										salePrice = salePrice.add(ticketVo.getTicket().getFee() != null ? ticketVo.getTicket().getFee() : new BigDecimal("0"));
										item.setAmount(salePrice);
										item.setSupplier("TMC-HB");//号百供应商
										item.setHbOrderId(ticketVo.getTicket().getHbOrderId());
										item.setOrderNo(orderCn);
										item.setCreateTime(date);
										purchaseItemMapper.save(item);
										//采购价格明细
										PurchaseDetailInfo info = new PurchaseDetailInfo();
										info.setPurchaseItemId(item.getId());
										info.setType(goodsType);
										info.setPriceType("0");//价格类型：0销售价格类型，1服务费，2配送费，3保险费
										info.setAmount(ticketVo.getTicket().getSalePrice() != null ? ticketVo.getTicket().getSalePrice() : new BigDecimal("0"));
										info.setAcount(1);
										purchaseDetailInfoMapper.save(info);
										//服务费
										PurchaseDetailInfo infoFee = new PurchaseDetailInfo();
										infoFee.setPurchaseItemId(item.getId());
										infoFee.setType(goodsType);
										infoFee.setPriceType("1");//价格类型：0销售价格类型，1服务费，2配送费，3保险费
										infoFee.setAmount(ticketVo.getTicket().getFee() != null ? ticketVo.getTicket().getFee() : new BigDecimal("0"));
										infoFee.setAcount(1);
										purchaseDetailInfoMapper.save(infoFee);
										
										//保险
										BigDecimal iPrice = new BigDecimal("0");
										List<Insurance> insuranceList = ticketVo.getInsurances();
										if(null != insuranceList && insuranceList.size() > 0){
											for(Insurance ins : insuranceList){
												iPrice = iPrice.add(ins.getPrice() != null ? ins.getPrice() : new BigDecimal("0"));
												PurchaseDetailInfo detailIfn = new PurchaseDetailInfo();
												detailIfn.setPurchaseItemId(item.getId());
												detailIfn.setType(goodsType);
												detailIfn.setPriceType("3");//价格类型：0销售价格类型，1服务费，2配送费，3保险费
												detailIfn.setAmount(ins.getPrice() != null ? ins.getPrice() : new BigDecimal("0"));
												detailIfn.setAcount(1);
												purchaseDetailInfoMapper.save(detailIfn);
											}
										}
										//更新采购项价格
										PurchaseItem purchaseItem = new PurchaseItem();
										purchaseItem.setId(item.getId());
										purchaseItem.setAmount(salePrice.add(iPrice));
										purchaseItemMapper.update(purchaseItem);
									}
								}
							}
						}
					}
					//更新采购总价
					Purchase upPurchase = new Purchase();
					upPurchase.setId(purchase.getId());
					upPurchase.setAmount(amount);
					purchaseMapper.update(upPurchase);
					responseMessage.setCode(ResponseMessage.SUCCESS);
		        	responseMessage.setMsg("createPurchase接口成功！");
				}else{
					responseMessage.setCode(ResponseMessage.ERROR);
		        	responseMessage.setMsg("createPurchase接口采购项orderItemList为空！");
				}
			} catch (Exception e) {
				responseMessage.setCode(ResponseMessage.ERROR);
	        	responseMessage.setMsg("createPurchase接口异常！");
				logger.error("createPurchase异常:", e);
			}
		}
		
		return responseMessage;
	}
	
	
}
