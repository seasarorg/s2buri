package taktak.buriburi.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;

import taktak.buriburi.dto.DocumentDto;

@S2Dao(bean = DocumentDto.class)
public interface DocumentDao {

	public List<DocumentDto> getAllDocument();

	@Arguments("id")
	@Query("id = /*id*/")
	public DocumentDto getDocumetByID(long id);

	public void insert(DocumentDto dto);

	public void update(DocumentDto dto);

	public void delete(DocumentDto dto);

}
