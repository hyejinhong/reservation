package kr.or.connect.reservation.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservationInfo {
	private Integer id;
	private Integer productId;
	private String productDescription;
	private String productContent;
	private int sumPrice;
	private Integer cancelFlag;
	private Integer displayInfoId;
	private Integer userId;
	private Date reservationDate;
	private Date createDate;
	private Date modifyDate;
//	private List<ReservationInfoPrice> prices;
	
	public ReservationInfo(Integer productId, Integer displayInfoId, Integer userId, String reservationDate) throws ParseException {
//		this.id = null;
		this.productId = productId;
		this.displayInfoId = displayInfoId;
		this.userId = userId;
		SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd");
		this.reservationDate = format.parse(reservationDate);
		
		this.cancelFlag = 0;
		this.createDate = new Date();
		this.modifyDate = new Date();
	}
	
	// default constructor
	public ReservationInfo() {
	}

	
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

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
	public Integer getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Integer getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(Integer cancelFlag) {
		this.cancelFlag = cancelFlag;
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
		return "ReservationInfo [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
				+ ", userId=" + userId + ", reservationDate=" + reservationDate + ", cancelFlag=" + cancelFlag
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
}
