package kr.or.connect.reservation.dto;

public class DisplayInfoImage {
	private Integer id;
	private Integer diaplayInfoId;
	private Integer fileId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDiaplayInfoId() {
		return diaplayInfoId;
	}
	public void setDiaplayInfoId(Integer diaplayInfoId) {
		this.diaplayInfoId = diaplayInfoId;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	@Override
	public String toString() {
		return "DisplayInfoImage [id=" + id + ", diaplayInfoId=" + diaplayInfoId + ", fileId=" + fileId + "]";
	}
	
	
}
