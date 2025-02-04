package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

@Entity
@Table(name = "bidlist")
public class BidList {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BidListId")
    private Integer id;
	
    @NotNull
    @NotBlank(message = "Account is mandatory")
	@Column(name = "account")
    private String account;
    
    @NotNull
    @NotBlank(message = "type is mandatory")
	@Column(name = "type")
    private String type;
    
    @NotNull(message = "must not be null")
	@Column(name = "bidQuantity")
    private Double bidQuantity;
	
	@Column(name = "askQuantity")
    private Double askQuantity;
	
	@Column(name = "bid")
    private Double bid;
	
	@Column(name = "ask")
    private Double ask;
	
	@Column(name = "benchmark")
    private String benchmark;
	
	@Column(name = "bidListDate")
    private Date bidListDate;
	
	@Column(name = "commentary")
    private String commentary;
	
	@Column(name = "security")
    private String security;
	
	@Column(name = "status")
    private String status;
	
	@Column(name = "trader")
    private String trader;
	
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

    public BidList() { }

    public BidList(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
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

	public Double getBidQuantity() {
		return bidQuantity;
	}

	public void setBidQuantity(Double bidQuantity) {
		this.bidQuantity = bidQuantity;
	}

	public Double getAskQuantity() {
		return askQuantity;
	}

	public void setAskQuantity(Double askQuantity) {
		this.askQuantity = askQuantity;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public Date getBidListDate() {
		return bidListDate;
	}

	public void setBidListDate(Date bidListDate) {
		this.bidListDate = bidListDate;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
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
