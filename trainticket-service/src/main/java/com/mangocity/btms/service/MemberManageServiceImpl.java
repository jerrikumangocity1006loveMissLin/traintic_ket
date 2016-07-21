package com.mangocity.btms.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.mangocity.btms.api.IMemberManageService;
import com.mangocity.member.adapter.model.Member;
import com.mangocity.member.adapter.model.MemberShipInfo;
import com.mangocity.member.adapter.model.NaturalPerson;
import com.mangocity.member.adapter.model.PersonEmail;
import com.mangocity.member.adapter.model.SecretaryType;
import com.mangocity.member.adapter.service.MemberAdapterService;
import com.mangocity.util.CnToSpellUtil;
import com.mangocity.vo.LinkmanInfo;

/**
 * 会员系统
 * 
 * @author hongxiaodong
 *
 */
public class MemberManageServiceImpl implements IMemberManageService {

	Logger log = Logger.getLogger(MemberManageServiceImpl.class);
	
	private MemberAdapterService memberAdapterService;
	
	@Override
	public List<Member> retrieveMemberSecretary(SecretaryType secretaryType, String mbrShipCd) {
		
		return memberAdapterService.retrieveMemberSecretary(secretaryType, mbrShipCd);
	}

	@Override
	public MemberShipInfo retrieveMbrShipByMbrshipCd(String shipCd) {

		return memberAdapterService.retrieveMbrShipByMbrshipCd(shipCd);
	}

	@Override
	public MemberShipInfo retrieveMbrShipByEmpNoAndCorpId(String empNo, long corpId) {

		return memberAdapterService.retrieveMbrShipByEmpNoAndCorpId(empNo, corpId);
	}

	@Override
	public MemberShipInfo retrieveMbrShipByEmpNoAndDeptId(String empNo, long deptId) {

		return memberAdapterService.retrieveMbrShipByEmpNoAndDeptId(empNo, deptId);
	}

	@Override
	public Member retrieveMemberByMbrId(long mbrId) {

		return memberAdapterService.retrieveMemberByMbrId(mbrId);
	}

	@Override
	public Member retrieveMemberByMbrShipCd(String mbrShipCd) {

		return memberAdapterService.retrieveMemberByMbrShipCd(mbrShipCd);
	}

	@Override
	public Member retrieveMemberByMobilOrEmail(String mobil, String email) {

		return memberAdapterService.retrieveMemberByMobilOrEmail(mobil, email);
	}

	@Override
	public LinkmanInfo retrieveLinkManInfoByMbrShipCd(String mbrShipCd) {

		LinkmanInfo linkmanInfo = new LinkmanInfo();

		Member tmcMember = retrieveMemberByMbrShipCd(mbrShipCd);
		
		

		NaturalPerson person = tmcMember.getPerson();

		MemberShipInfo memberShipInfo = tmcMember.getMemberShipInfoList().get(0);

		if (tmcMember != null) {

			linkmanInfo.setName(getMemberFullName(person));
			linkmanInfo.setMobile(
					CnToSpellUtil.changMobileAndEmail(memberShipInfo.getMemberShipCode(), person.getMobileNo()));
			linkmanInfo.setFax(person.getFax());
			List<PersonEmail> personEmailList = person.getPersonEmailList();
			String email = null;
			for (PersonEmail personEmail : personEmailList) {
				if (PersonEmail.EmailStatus.VALID.equals(personEmail.getEmailStatus())) {
					email = personEmail.getEmail();
					break;
				}
			}
			linkmanInfo.setEmail(CnToSpellUtil.changMobileAndEmail(memberShipInfo.getMemberShipCode(), email));
		}

		log.info("查询会员会消息为:" + linkmanInfo);

		return linkmanInfo;
	}

	public String getMemberFullName(NaturalPerson person) {
		String fullName = person.getFamilyName() + person.getName();
		if (!StringUtils.isEmpty(fullName))
			return fullName;
		String engName = person.getLastName();
		String engmidname = person.getMiddleName();
		String engsurname = person.getFirstName();
		if (engName != null && !"".equals(engName)) {
			fullName = engName;
		}
		if (engsurname != null && !"".equals(engsurname)) {
			if ("".equals(fullName)) {
				fullName = engsurname;
			} else {
				fullName = fullName + "/" + engsurname;
			}
		}
		if (engmidname != null && !"".equals(engmidname)) {
			if ("".equals(fullName)) {
				fullName = engmidname;
			} else {
				fullName = fullName + " " + engmidname;
			}
		}

		return fullName;
	}

	public String getPersonEmail(List<PersonEmail> personEmailList) {
		if (personEmailList == null || personEmailList.isEmpty())
			return null;
		String personEmailResult = personEmailList.get(0).getEmail();
		for (PersonEmail personEmail : personEmailList) {
			if (!PersonEmail.EmailStatus.VALID.equals(personEmail.getEmailStatus()))
				continue;
			personEmailResult = personEmail.getEmail();
		}
		return personEmailResult;
	}

	public MemberAdapterService getMemberAdapterService() {
		return memberAdapterService;
	}

	public void setMemberAdapterService(MemberAdapterService memberAdapterService) {
		this.memberAdapterService = memberAdapterService;
	}

	

}
