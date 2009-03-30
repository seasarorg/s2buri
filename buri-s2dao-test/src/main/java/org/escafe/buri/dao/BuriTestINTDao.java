package org.escafe.buri.dao;

import java.util.List;

import org.escafe.buri.dto.BuriTestINTDto;
import org.escafe.buri.dto.BuriTestINTFindDto;

public interface BuriTestINTDao {
	public Class<?> BEAN = BuriTestINTDto.class;

	public List<BuriTestINTDto> getAllBuriTestINT();

	public String getBuriTestINT_QUERY = "TEST_ID = ?";

	public BuriTestINTDto getBuriTestINT(Long testId);

	public String getBuriTestINTByIds_ARGS = "testIds";

	public String getBuriTestINTByIds_QUERY = "TEST_ID in /*testIds*/(1)";

	public List<BuriTestINTDto> getBuriTestINTByIds(List<Long> testIds);

	public String find_ARGS = "dto,paths";

	public List<BuriTestINTDto> find(BuriTestINTFindDto dto, List<String> paths);

	public String findAndUser_ARGS = "dto,paths,userIds";

	public List<BuriTestINTDto> findAndUser(BuriTestINTFindDto dto,
	        List<String> paths, List<Long> userIds);

	public String getBuriTestINTByIdAndDto_ARGS = "testIds,dto";

	public String getBuriTestINTByIdAndDto_QUERY =
	    "TEST_ID IN /*testIds*/(1) and value = /*dto.value*/'hoge'";

	public List<BuriTestINTDto> getBuriTestINTByIdAndDto(List<Long> testIds,
	        BuriTestINTDto dto);

	public void insert(BuriTestINTDto dto);

	public void update(BuriTestINTDto dto);

	public void delete(BuriTestINTDto dto);
}
