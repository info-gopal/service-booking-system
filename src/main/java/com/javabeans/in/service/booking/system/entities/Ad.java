package com.javabeans.in.service.booking.system.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.javabeans.in.service.booking.system.DTO.AdDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ads")
public class Ad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adId;
	private String serviceName;
	private Double price;
	private String description;
		
	@Lob
	private byte[] img;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	
	
	public AdDTO getDto() {
		AdDTO dto=new AdDTO();		
		dto.setAdId(adId);
		dto.setDescription(description);
		dto.setImg(img);
		dto.setPrice(price);
		dto.setServiceName(serviceName);
		dto.setUserId(user.getUserId());
		dto.setCompanyName(user.getUserName());	
		return dto;
	}
	
	

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
