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
    private int id;
	
	@NotNull(message = "must not be null")
	@Column(name = "CurveId")
    private Integer curveId;
	
	@Column(name = "asOfDate")
    private Date asOfDate;

	@Column(name = "term")
    private double term;

	@Column(name = "value")
    private double value;
	
	@Column(name = "creationDate")
    private Date creationDate;
    
    
    public CurvePoint() {}
    public CurvePoint(int curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public double getTerm() {
		return term;
	}
	public void setTerm(double term) {
		this.term = term;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
    
    
}
