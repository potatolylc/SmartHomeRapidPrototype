package ioedata.user.repository;

import ioedata.user.model.UserValue;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;

	@Override
	public int insertUser(UserValue userVal) {
		return this.template.insert("user.insertUser", userVal);
	}

	@Override
	public int getUserCountByUserSerialNum(int userSerialNum) {
		return this.template.selectOne("user.getUserCountByUserSerialNum", userSerialNum);
	}

	@Override
	public int getUserCountByUserName(String userName) {
		return this.template.selectOne("user.getUserCountByUserName", userName);
	}

	@Override
	public int getUserCountByUserNameAndSsid(UserValue userVal) {
		return this.template.selectOne("user.getUserCountByUserNameAndSsid", userVal);
	}

	@Override
	public int getUserCountByUserNameAndSsidAndPassword(UserValue userVal) {
		return this.template.selectOne("user.getUserCountByUserNameAndSsidAndPassword", userVal);
	}

	@Override
	public int getUserSerialNumByUserNameAndSsid(UserValue userVal) {
		return this.template.selectOne("user.getUserSerialNumByUserNameAndSsid", userVal);
	}

	@Override
	public int getUserSerialNumByUserNameAndSsidAndPassword(UserValue userVal) {
		return this.template.selectOne("user.getUserSerialNumByUserNameAndSsidAndPassword", userVal);
	}

	@Override
	public UserValue getUserInfoByUserNameAndSsid(UserValue userVal) {
		return this.template.selectOne("user.getUserInfoByUserNameAndSsid", userVal);
	}
}
