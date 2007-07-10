package taktak.buriburi.dto;

import java.sql.Timestamp;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;
import org.seasar.dao.annotation.tiger.Id;
import org.seasar.dao.annotation.tiger.IdType;

@Bean(table = "Document", timeStampProperty = "processDate")
public class DocumentDto {

	private long id;

	private String title;

	private String contents;

	private Timestamp processDate;

	public long getId() {
		return id;
	}

	@Id(value = IdType.SEQUENCE, sequenceName = "sequence,sequenceName=DocumentID")
	@Column("id")
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	@Column("title")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	@Column("contents")
	public void setContents(String contents) {
		this.contents = contents;
	}

	public Timestamp getProcessDate() {
		return processDate;
	}

	@Column("processDate")
	public void setProcessDate(Timestamp processDate) {
		this.processDate = processDate;
	}

	public String toString() {
		System.out.println("\n");
		System.out.println("★------------------------------------------------------------------[DocumentDto]");
		return "\n" + "ID:=" + id + "/" + "Title:=" + title + "/" + "Contents:=" + contents + "/" + "ProcessDate:=" + processDate;
	}

}
