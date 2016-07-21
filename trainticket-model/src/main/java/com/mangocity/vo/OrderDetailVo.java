package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author lizhi
 *
 * @date 2016年6月2日
 */
public class OrderDetailVo implements Serializable {

	private static final long serialVersionUID = -870125542388032863L;

	private List<TrainOrderMXVo> tox;
	private OrderDetailBasic tod;

	public List<TrainOrderMXVo> getTox() {
		return tox;
	}

	public void setTox(List<TrainOrderMXVo> tox) {
		this.tox = tox;
	}

	public OrderDetailBasic getTod() {
		return tod;
	}

	public void setTod(OrderDetailBasic tod) {
		this.tod = tod;
	}

}
