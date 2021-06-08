package kr.or.connect.reservation.dto;

public class ReservationUserCommentImage {
	private Integer id;
	private Integer reservationInfoId;
	private Integer reservationUserCommentId;
	private Integer fileId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public Integer getReservationUserCommentId() {
		return reservationUserCommentId;
	}
	public void setReservationUserCommentId(Integer reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	@Override
	public String toString() {
		return "ReservationUserCommentImage [id=" + id + ", reservationInfoId=" + reservationInfoId
				+ ", reservationUserCommentId=" + reservationUserCommentId + ", fileId=" + fileId + "]";
	}
	
	
}
