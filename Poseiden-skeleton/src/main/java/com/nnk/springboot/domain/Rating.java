package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rating")
public class Rating {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
    private int id;

    @NotBlank(message = "MoodysRating is mandatory")
	@Column(name = "moodysRating")
    private String moodysRating;

    @NotBlank(message = "SandPRating is mandatory")
	@Column(name = "sandPRating")
    private String sandPRating;

    @NotBlank(message = "FitchRating is mandatory")
	@Column(name = "fitchRating")
    private String fitchRating;
	
	@Column(name = "orderNumber")
    private Integer order;
    
    public Rating() {}
    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer order) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.order = order;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMoodysRating() {
		return moodysRating;
	}
	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}
	public String getSandPRating() {
		return sandPRating;
	}
	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}
	public String getFitchRating() {
		return fitchRating;
	}
	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}
