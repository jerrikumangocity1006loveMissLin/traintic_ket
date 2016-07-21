package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 火车票退单详情
 * @author lanlonghui
 *
 */
public class TrainReturnTicketDetailVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5575152315637140656L;

	private TrainReturnVo trainReturnVo;//退票单信息
	
	private List<TrainReturnMxVo> trainReturnMxVoList;//退单明细

	public TrainReturnVo getTrainReturnVo() {
		return trainReturnVo;
	}

	@JSONField(name="trt")
	public void setTrainReturnVo(TrainReturnVo trainReturnVo) {
		this.trainReturnVo = trainReturnVo;
	}

	public List<TrainReturnMxVo> getTrainReturnMxVoList() {
		return trainReturnMxVoList;
	}

	@JSONField(name="rmx")
	public void setTrainReturnMxVoList(List<TrainReturnMxVo> trainReturnMxVoList) {
		this.trainReturnMxVoList = trainReturnMxVoList;
	}

	@Override
	public String toString() {
		return "TrainReturnTicketDetailVo [trainReturnVo=" + trainReturnVo
				+ ", trainReturnMxVoList=" + trainReturnMxVoList + "]";
	}
	
	
}
