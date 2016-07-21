package com.mangocity.btms.api;

import java.util.List;

import com.mangocity.member.adapter.model.Member;
import com.mangocity.member.adapter.model.MemberShipInfo;
import com.mangocity.member.adapter.model.SecretaryType;
import com.mangocity.vo.LinkmanInfo;

/**
 * 会员系统服务系统
 * 
 * @author hongxiaodong
 *
 */
public interface IMemberManageService {
	
	/**
	 * 根据会员信息获取联系人
	 * @return
	 */
	public LinkmanInfo retrieveLinkManInfoByMbrShipCd(String mbrShipCd);

	public MemberShipInfo retrieveMbrShipByMbrshipCd(String shipCd);

	public MemberShipInfo retrieveMbrShipByEmpNoAndCorpId(String empNo, long corpId);

	public MemberShipInfo retrieveMbrShipByEmpNoAndDeptId(String empNo, long deptId);

	public Member retrieveMemberByMbrId(long mbrId);

	public Member retrieveMemberByMbrShipCd(String mbrShipCd);

	public Member retrieveMemberByMobilOrEmail(String mobil, String email);
	
	public List<Member> retrieveMemberSecretary(SecretaryType secretaryType, String mbrShipCd);

}
