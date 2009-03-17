package org.escafe.buri.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.seasar.nadejako.annotation.NakoProperties;
import org.seasar.nadejako.annotation.NakoProperty;

@NakoProperties( {
    @NakoProperty(name = "あいでぃー", property = "furnitureId"),
    @NakoProperty(name = "種類", property = "type"),
    @NakoProperty(name = "名前", property = "name"),
    @NakoProperty(name = "取得日", property = "acquisition"),
    @NakoProperty(name = "取得タイプ", property = "acquisitionType") })
@Entity
@Table(name = "FURNITURE_ITEM")
public class FurnitureItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FURNITURE_GEN")
	@SequenceGenerator(name = "FURNITURE_GEN", sequenceName = "FURNITURE_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long furnitureId;

	@Column(length = 100, nullable = false, unique = false)
	public String type = "";

	@Column(length = 100, nullable = false, unique = false)
	public String name = "";

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date acquisition;

	@Column(precision = 19, nullable = false, unique = false)
	public AcquisitionType acquisitionType;

	@Version
	@Column(precision = 19, nullable = false, unique = false)
	public Long versionNo;
}
