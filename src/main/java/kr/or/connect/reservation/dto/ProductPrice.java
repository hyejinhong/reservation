package kr.or.connect.reservation.dto;

import java.util.Date;

public class ProductPrice {
	private Integer id;
	private Integer productId;
	private String priceTypeName;
	private Integer price;
	private Double discountRate;
	private Date createDate;
	private Date modifyDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getPriceTypeName() {
		return priceTypeName;
	}
	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "ProductPrice [id=" + id + ", productId=" + productId + ", priceTypeName=" + priceTypeName + ", price="
				+ price + ", discountRate=" + discountRate + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ "]";
	}
	
	
}
