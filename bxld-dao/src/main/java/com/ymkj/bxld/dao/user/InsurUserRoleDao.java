package com.ymkj.bxld.dao.user;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.bxld.domain.user.InsurUserRole;

public interface InsurUserRoleDao extends BaseDao<InsurUserRole>{

	int deleteByLoginAccount(String loginAccount);

}
