package com.javabeans.in.service.booking.system.DTO;

import org.springframework.web.multipart.MultipartFile;

public class AdDTO {
	
	
	
	private Long adId;
	private String serviceName;
	private Double price;
	private String description;
	private byte[] img;
	private MultipartFile file;
	private Long userId;
	private String companyName;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	
	
	}
