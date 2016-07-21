package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.enums.MessageType;
import com.mangocity.model.MessageTemplate;

/**
 * 信息模板处理类
 * @author hongxiaodong
 *
 */
public interface MessageTemplateMapper extends BaseMapper<MessageTemplate> {
	
	/**
	 * 根据消息内容获取所有的消息模板
	 * @param type
	 * @return
	 */
	public List<MessageTemplate> findByType(@Param("type")MessageType type);
	
	/**
	 * 根据模版ID获取模板
	 * @param templateNum
	 * @return
	 */
	public MessageTemplate findBytemplateNum(@Param("templateNum")Integer templateNum);

}
