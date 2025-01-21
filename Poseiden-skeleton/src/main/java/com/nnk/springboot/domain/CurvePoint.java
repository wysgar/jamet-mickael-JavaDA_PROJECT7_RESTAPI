package com.nnk.springboot.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
    private int id;
	
	@Column(name = "CurveId")
    private int curveId;
	
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
	public int getCurveId() {
		return curveId;
	}
	public void setCurveId(int curveId) {
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
