package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
    private Integer id;
	
	@NotNull(message = "must not be null")
	@Column(name = "CurveId")
    private Integer curveId;
	
	@Column(name = "asOfDate")
    private Date asOfDate;
	
	@NotNull(message = "must not be null")
	@Column(name = "term")
    private Double term;

	@NotNull(message = "must not be null")
	@Column(name = "value")
    private Double value;
	
	@Column(name = "creationDate")
    private Date creationDate;
    
    
    public CurvePoint() {}
    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCurveId() {
		return curveId;
	}
	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}
	public Date getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}
	public Double getTerm() {
		return term;
	}
	public void setTerm(Double term) {
		this.term = term;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
    
    
}
