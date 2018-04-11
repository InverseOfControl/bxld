package com.ymkj.bxld.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.dao.user.InsurUserRoleDao;
import com.ymkj.bxld.domain.user.InsurUserRole;

@Repository
public class InsurUserRoleDaoImpl extends BaseDaoImpl<InsurUserRole> implements InsurUserRoleDao {

	@Override
	public int deleteByLoginAccount(String loginAccount) {
		return this.getSessionTemplate().delete("deleteByLoginAccount", loginAccount);
	}

}
