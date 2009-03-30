package org.escafe.buri.s2dao.dao;

import java.util.List;

import org.escafe.buri.dto.BuriTestUserDto;
import org.escafe.buri.dto.BuriTestUserFindDto;

public interface BuriTestUserDao {
	public Class<?> BEAN = BuriTestUserDto.class;

	public List<BuriTestUserDto> getAllBuriTestUser();

	public String getBuriTestUser_QUERY = "USER_ID = ?";

	public BuriTestUserDto getBuriTestUser(Long userId);

	public String getBuriTestUserByIds_ARGS = "userIds";

	public String getBuriTestUserByIds_QUERY = "USER_ID in /*userIds*/(1)";

	public List<BuriTestUserDto> getBuriTestUserByIds(List<Long> userIds);

	public String find_ARGS = "dto,paths";

	public List<BuriTestUserDto> find(BuriTestUserFindDto dto,
	        List<String> paths);

	public String findAndUser_ARGS = "dto,paths,userIds";

	public List<BuriTestUserDto> findAndUser(BuriTestUserFindDto dto,
	        List<String> paths, List<Long> userIds);

	public String getUserListByParentUserId_ARGS = "parentUserId,roleName";

	public List<BuriTestUserDto> getUserListByParentUserId(Long userId,
	        String roleName);

	public String getUserListByUserId_ARGS = "userId,roleName";

	public List<BuriTestUserDto> getUserListByUserId(Long userId,
	        String roleName);

	public void insert(BuriTestUserDto entity);

	public void update(BuriTestUserDto entity);

	public void delete(BuriTestUserDto entity);
}
