package com.mangocity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mangocity.model.Insurance;

public interface TrainInsuranceMapper extends BaseMapper<Insurance> {
   public void savebatchInsurance(@Param("list")List<Insurance> Insuranzz);
   
   /**
    * 根据火车票ID查询保险
    * @param ticketId
    * @return
    */
   public List<Insurance> findInsuranceByTicketId(Long ticketId);
}
