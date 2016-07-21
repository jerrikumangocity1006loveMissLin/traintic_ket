package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 火车票改签单详情
 * @author lanlonghui
 *
 */
public class TrainGqDetailVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1954856466618426562L;
	
	private TrainGqVo trainGqVo;//改签单信息
	
	private List<TrainGqMxVo> TrainGqMxVo;//改签单明细

	public TrainGqVo getTrainGqVo() {
		return trainGqVo;
	}

	@JSONField(name="tgq")
	public void setTrainGqVo(TrainGqVo trainGqVo) {
		this.trainGqVo = trainGqVo;
	}

	public List<TrainGqMxVo> getTrainGqMxVo() {
		return TrainGqMxVo;
	}

	@JSONField(name="trainGqMxs")
	public void setTrainGqMxVo(List<TrainGqMxVo> trainGqMxVo) {
		TrainGqMxVo = trainGqMxVo;
	}

	@Override
	public String toString() {
		return "TrainGqDetailVo [trainGqVo=" + trainGqVo + ", TrainGqMxVo="
				+ TrainGqMxVo + "]";
	}
	
}
