package taktak.buriburi.bao;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.engine.processor.util.BuriSignal;
import org.seasar.extension.unit.S2TestCase;

import taktak.buriburi.dao.RoleDao;
import taktak.buriburi.dto.DocumentDto;
import taktak.buriburi.role.RoleDto;

public class DocumentBaoTest extends S2TestCase {

	String path = "app.dicon";

	private RoleDao roleDao;
	private DocumentBao documentBao;
	private BuriSignal buriSignal;

	private RoleDto role;

	@Override
	protected void setUp() throws Exception {
		include(path);
	}

	private void getRole(long id) {
		RoleDto roleDto = roleDao.getRoleByID(id);
		role.setId(roleDto.getId());
		role.setName(roleDto.getName());
	}

	private void setUpRoleData() {
		RoleDto roleDto;
		roleDto = new RoleDto();
		roleDto.setId(1);
		roleDto.setName("ユーザー");
		roleDao.insert(roleDto);
		roleDto = new RoleDto();
		roleDto.setId(2);
		roleDto.setName("管理者");
		roleDao.insert(roleDto);
	}

	public void testDocumentBaoTx() throws Exception {
		setUpRoleData();
		DocumentDto dto = new DocumentDto();
		dto.setTitle("文書A");
		dto.setContents("文書Aです。");
		assertNotNull(role);
		System.out.println("★--------------------------------------------------------------[StartReview]");
		getRole(1);
		documentBao.startReview(dto);
		System.out.println("★--------------------------------------------------------------[getReviewWating]");
		getRole(1);
		assertEquals(1, documentBao.getReviewWating().size());
		System.out.println("★--------------------------------------------------------------[getReviewing]");
		getRole(2);
		assertEquals(1, documentBao.getReviewing().size());
		System.out.println("★--------------------------------------------------------------[getReviewAuthorize]");
		getRole(2);
		dto.setContents("文書A認可済");
		documentBao.startReviewAuthorize(dto);
		System.out.println("★--------------------------------------------------------------[Signal Action !!]");
		buriSignal.signal("文書管理パッケージ.公開審査プロセス.審査待ち", dto, "認可");
		getRole(2);
		assertEquals(1, documentBao.getReviewAuthorize().size());
		getRole(1);
		assertEquals(1, documentBao.getReviewAuthorized().size());
	}

	public void testDocumentBao2Tx() throws Exception {
		setUpRoleData();

		assertNotNull(role);
		System.out.println("★--------------------------------------------------------------[StartReview]");
		getRole(1);

		List<DocumentDto> datas = new ArrayList<DocumentDto>();
		for (int i = 0; i < 10; i++) {
			DocumentDto dto = new DocumentDto();
			dto.setTitle("文書" + i);
			dto.setContents("文書" + i + "です");
			datas.add(dto);
		}

		for (DocumentDto dto : datas) {
			documentBao.startReview(dto);
		}

		System.out.println("★--------------------------------------------------------------[getReviewWating]");
		getRole(1);
		assertEquals(10, documentBao.getReviewWating().size());

		System.out.println("★--------------------------------------------------------------[getReviewing]");
		getRole(2);
		assertEquals(10, documentBao.getReviewing().size());

		System.out.println("★--------------------------------------------------------------[getReviewAuthorize]");
		getRole(2);
		for (DocumentDto dto : datas) {
			dto.setContents(dto.getContents() + "認可済");
			documentBao.startReviewAuthorize(dto);
		}

		System.out.println("★--------------------------------------------------------------[Signal Action !!]");
		for (DocumentDto dto : datas) {
			buriSignal.signal("文書管理パッケージ.公開審査プロセス.審査待ち", dto, "認可");
		}

		getRole(2);
		assertEquals(10, documentBao.getReviewAuthorize().size());

		getRole(1);
		assertEquals(10, documentBao.getReviewAuthorized().size());
	}

}
