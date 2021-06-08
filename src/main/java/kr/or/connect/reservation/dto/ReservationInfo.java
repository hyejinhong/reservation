package kr.or.connect.reservation.dto;

import java.util.Date;

public class ReservationInfo {
	private Integer id;
	private Integer productId;
	private Integer displayInfoId;
	private Integer userId;
	private Date reservationDate;
	private Integer cancelFlag;
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
