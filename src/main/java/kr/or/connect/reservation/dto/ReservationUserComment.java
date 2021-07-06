package kr.or.connect.reservation.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationUserComment {
	private Integer id;
	private Integer productId;
	private Integer reservationInfoId;
//	private Integer userId;
	private Double score;
	private String comment;
	private Date createDate;
	private Date modifyDate;

	private String reservationEmail;
	
	private List<ReservationUserCommentImage> reservationUserCommentImages;
	
	public ReservationUserComment() {
		this.reservationUserCommentImages = new ArrayList<>();
	}
	
	public List<ReservationUserCommentImage> getReservationUserCommentImages() {
		return reservationUserCommentImages;
	}
	public void setReservationUserCommentImages(List<ReservationUserCommentImage> reservationUserCommentImages) {
		if(reservationUserCommentImages == null) {
			this.reservationUserCommentImages = null;
		}
		else {
			this.reservationUserCommentImages.addAll(reservationUserCommentImages);
		}
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
	public Integer getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	
	
}
