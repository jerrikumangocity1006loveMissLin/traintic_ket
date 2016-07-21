package com.mangocity.vo;

import java.io.Serializable;
import java.util.List;

import com.mangocity.model.Account;
import com.mangocity.model.FrequentTraveller;

public class AccountVo implements Serializable {

	private static final long serialVersionUID = -3039794512640821056L;
	private Account account;
	private List<FrequentTraveller> frequentravellers;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<FrequentTraveller> getFrequentravellers() {
		return frequentravellers;
	}

	public void setFrequentravellers(List<FrequentTraveller> frequentravellers) {
		this.frequentravellers = frequentravellers;
	}

}
