package taktak.buriburi.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;

import taktak.buriburi.role.RoleDto;

@S2Dao(bean = RoleDto.class)
public interface RoleDao {

	public List<RoleDto> getAllRole();

	@Arguments("id")
	@Query("id = /*id*/")
	public RoleDto getRoleByID(long id);

	public void insert(RoleDto dto);

	public void update(RoleDto dto);

	public void delete(RoleDto dto);

}
