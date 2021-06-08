package kr.or.connect.reservation.dto;

public class ReservationInfoPrice {
	private Integer id;
	private Integer reservationInfoId;
	private Integer productPriceId;
	private Integer count;
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
	public Integer getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(Integer productPriceId) {
		this.productPriceId = productPriceId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "ReservationInfoPrice [id=" + id + ", reservationInfoId=" + reservationInfoId + ", productPriceId="
				+ productPriceId + ", count=" + count + "]";
	}
	
	
}
