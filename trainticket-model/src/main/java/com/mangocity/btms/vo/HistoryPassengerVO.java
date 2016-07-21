package com.mangocity.btms.vo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ��ʷ�˻���
 * 
 * @author fuhoujun
 * 
 */
public class HistoryPassengerVO implements Comparable {

	private Log log = LogFactory.getLog(HistoryPassengerVO.class);

	private long id;

	private String psgname;// 乘机人中文名

	private String psgnamepy;// 乘机人中文名拼音

	private String psgengname;// 乘机人英文名

	private String psgsex;// 性别 M:男F:女

	private String psgtype;// 价格身份成人MAN 儿童CHILD 婴儿BABY

	private String psgnationality;// 乘机人国籍

	private String psgtel;// 电话

	private String psgbirthdate;// 生日

	private String identity;// 身份 儿童=CHILD、男童 = MSTR、女童 = MISS、学生票 = SD、移民 =
							// EM，海员 = DL

	private String meals;// 餐食要求

	private String psgemail;

	private String psgfax;// 传真

	private String level;// 级别

	private Date expiryDate;// 证件有效期

	private List<CertVO> cert = new ArrayList<CertVO>();// 此人的证件

	private List<TravelCard> card = new ArrayList<TravelCard>();// 此人的常旅卡

	private Date createDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<TravelCard> getCard() {
		return card;
	}

	public void setCard(List<TravelCard> card) {
		this.card = card;
	}

	public List<CertVO> getCert() {
		return cert;
	}

	public void setCert(List<CertVO> cert) {
		this.cert = cert;
	}

	public String getPsgemail() {
		return psgemail;
	}

	public void setPsgemail(String psgemail) {
		this.psgemail = psgemail;
	}

	public String getPsgfax() {
		return psgfax;
	}

	public void setPsgfax(String psgfax) {
		this.psgfax = psgfax;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getMeals() {
		return meals;
	}

	public void setMeals(String meals) {
		this.meals = meals;
	}

	public String getPsgbirthdate() {
		return psgbirthdate;
	}

	public void setPsgbirthdate(String psgbirthdate) {
		this.psgbirthdate = psgbirthdate;
	}

	public String getPsgengname() {
		return psgengname;
	}

	public void setPsgengname(String psgengname) {
		this.psgengname = psgengname;
	}

	public String getPsgname() {
		return psgname;
	}

	public void setPsgname(String psgname) {
		this.psgname = psgname;
	}

	public String getPsgnamepy() {
		return psgnamepy;
	}

	public void setPsgnamepy(String psgnamepy) {
		this.psgnamepy = psgnamepy;
	}

	public String getPsgnationality() {
		return psgnationality;
	}

	public void setPsgnationality(String psgnationality) {
		this.psgnationality = psgnationality;
	}

	public String getPsgsex() {
		return psgsex;
	}

	public void setPsgsex(String psgsex) {
		this.psgsex = psgsex;
	}

	public String getPsgtel() {
		return psgtel;
	}

	public void setPsgtel(String psgtel) {
		this.psgtel = psgtel;
	}

	public String getPsgtype() {
		return psgtype;
	}

	public void setPsgtype(String psgtype) {
		this.psgtype = psgtype;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int compareTo(Object o) {
		HistoryPassengerVO vo = (HistoryPassengerVO) o;
		// ��ʱ��Ľ������У�������ǰ��
		return vo.getCreateDate().before(this.createDate) ? -1
				: (vo.getCreateDate().after(this.createDate) ? 1
						: (vo.getId() > this.id ? -1 : (vo.getId() < this.id ? 1 : 0)));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((cert == null) ? 0 : cert.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((log == null) ? 0 : log.hashCode());
		result = prime * result + ((meals == null) ? 0 : meals.hashCode());
		result = prime * result + ((psgbirthdate == null) ? 0 : psgbirthdate.hashCode());
		result = prime * result + ((psgemail == null) ? 0 : psgemail.hashCode());
		result = prime * result + ((psgengname == null) ? 0 : psgengname.hashCode());
		result = prime * result + ((psgfax == null) ? 0 : psgfax.hashCode());
		result = prime * result + ((psgname == null) ? 0 : psgname.hashCode());
		result = prime * result + ((psgnationality == null) ? 0 : psgnationality.hashCode());
		result = prime * result + ((psgsex == null) ? 0 : psgsex.hashCode());
		result = prime * result + ((psgtel == null) ? 0 : psgtel.hashCode());
		result = prime * result + ((psgtype == null) ? 0 : psgtype.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoryPassengerVO other = (HistoryPassengerVO) obj;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (cert == null) {
			if (other.cert != null)
				return false;
		} else if (!cert.equals(other.cert))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (id != other.id)
			return false;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (log == null) {
			if (other.log != null)
				return false;
		} else if (!log.equals(other.log))
			return false;
		if (meals == null) {
			if (other.meals != null)
				return false;
		} else if (!meals.equals(other.meals))
			return false;
		if (psgbirthdate == null) {
			if (other.psgbirthdate != null)
				return false;
		} else if (!psgbirthdate.equals(other.psgbirthdate))
			return false;
		if (psgemail == null) {
			if (other.psgemail != null)
				return false;
		} else if (!psgemail.equals(other.psgemail))
			return false;
		if (psgengname == null) {
			if (other.psgengname != null)
				return false;
		} else if (!psgengname.equals(other.psgengname))
			return false;
		if (psgfax == null) {
			if (other.psgfax != null)
				return false;
		} else if (!psgfax.equals(other.psgfax))
			return false;
		if (psgname == null) {
			if (other.psgname != null)
				return false;
		} else if (!psgname.equals(other.psgname))
			return false;
		if (psgnationality == null) {
			if (other.psgnationality != null)
				return false;
		} else if (!psgnationality.equals(other.psgnationality))
			return false;
		if (psgsex == null) {
			if (other.psgsex != null)
				return false;
		} else if (!psgsex.equals(other.psgsex))
			return false;
		if (psgtel == null) {
			if (other.psgtel != null)
				return false;
		} else if (!psgtel.equals(other.psgtel))
			return false;
		if (psgtype == null) {
			if (other.psgtype != null)
				return false;
		} else if (!psgtype.equals(other.psgtype))
			return false;
		return true;
	}

}
