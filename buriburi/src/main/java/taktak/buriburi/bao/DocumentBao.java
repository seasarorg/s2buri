package taktak.buriburi.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriAction;
import org.escafe.buri.annotation.BuriActivity;
import org.escafe.buri.annotation.BuriUserInfo;

import taktak.buriburi.dto.DocumentDto;

@Buri(dtoClass = DocumentDto.class, process = "文書管理パッケージ.公開審査プロセス")
@BuriUserInfo("role")
public interface DocumentBao {

	// [文書管理担当者]---------------------------------------------------------------------------------------//

	@BuriActivity("審査中")
	public List<DocumentDto> getReviewing();

	@BuriActivity("審査中")
	@BuriAction("認可")
	public void startReviewAuthorize(DocumentDto dto);

	@BuriActivity("審査中")
	@BuriAction("不認可")
	public void startReviewNotAuthorize(DocumentDto dto);

	@BuriActivity("認可")
	public List<DocumentDto> getReviewAuthorize();

	@BuriActivity("不認可")
	public List<DocumentDto> getReviewNotAuthorize();

	// [依頼者]---------------------------------------------------------------------------------------//

	@BuriActivity("審査依頼")
	public void startReview(DocumentDto dto);

	@BuriActivity("審査待ち")
	public List<DocumentDto> getReviewWating();

	@BuriActivity("認可済")
	public void startReviewAuthorizeOK(DocumentDto dto);

	@BuriActivity("認可済")
	public List<DocumentDto> getReviewAuthorized();

	@BuriActivity("不認可済")
	public void startReviewAuthorizeNG(DocumentDto dto);

	@BuriActivity("不認可済")
	public List<DocumentDto> getReviewNotAuthorized();

	// ---------------------------------------------------------------------------------------//

}
