package taktak.buriburi.role;

import java.sql.Timestamp;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;
import org.seasar.dao.annotation.tiger.Id;
import org.seasar.dao.annotation.tiger.IdType;

@Bean(table = "Role", timeStampProperty = "processDate")
public class RoleDto {

	private long id;

	private String name;

	private Timestamp processDate;

	public long getId() {
		return id;
	}

	// @Id(value = IdType.SEQUENCE, sequenceName = "sequence,sequenceName=RoleID")
	@Id(value = IdType.ASSIGNED)
	@Column("id")
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@Column("name")
	public void setName(String name) {
		this.name = name;
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
		System.out.println("★------------------------------------------------------------------[RoleDto]");
		return "\n" + "ID:=" + id + "/" + "Name:=" + name + "/" + "ProcessDate:=" + processDate;
	}

}
