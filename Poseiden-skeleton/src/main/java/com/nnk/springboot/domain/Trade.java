package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


@Entity
@Table(name = "trade")
public class Trade {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TradeId")
    private Integer id;
	
    @NotNull
    @Column(name = "account")
    private String account;
    
    @NotNull
    @Column(name = "type")
    private String type;
    
    @Column(name = "buyQuantity")
    private Double buyQuantity;
    
    @Column(name = "sellQuantity")
    private Double sellQuantity;
    
    @Column(name = "buyPrice")
    private Double buyPrice;
    
    @Column(name = "sellPrice")
    private Double sellPrice;
    
    @Column(name = "tradeDate")
    private Date tradeDate;
    
    @Column(name = "security")
    private String security;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "trader")
    private String trader;
    
    @Column(name = "benchmark")
    private String benchmark;
    
    @Column(name = "book")
    private String book;
    
    @Column(name = "creationName")
    private String creationName;
    
    @Column(name = "creationDate")
    private Date creationDate;
    
    @Column(name = "revisionName")
    private String revisionName;
    
    @Column(name = "revisionDate")
    private Date revisionDate;
    
    @Column(name = "dealName")
    private String dealName;
    
    @Column(name = "dealType")
    private String dealType;
    
    @Column(name = "sourceListId")
    private String sourceListId;
    
    @Column(name = "side")
    private String side;
    
    public Trade() { }
    public Trade(String account, String type) {
        this.type = type;
        this.account = account;
    }
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(Double buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	public Double getSellQuantity() {
		return sellQuantity;
	}
	public void setSellQuantity(Double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrader() {
		return trader;
	}
	public void setTrader(String trader) {
		this.trader = trader;
	}
	public String getBenchmark() {
		return benchmark;
	}
	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getCreationName() {
		return creationName;
	}
	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getRevisionName() {
		return revisionName;
	}
	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}
	public Date getRevisionDate() {
		return revisionDate;
	}
	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}
	public String getDealName() {
		return dealName;
	}
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getSourceListId() {
		return sourceListId;
	}
	public void setSourceListId(String sourceListId) {
		this.sourceListId = sourceListId;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
}
