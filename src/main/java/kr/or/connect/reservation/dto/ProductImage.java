package kr.or.connect.reservation.dto;

import java.util.Date;

public class ProductImage {
	private Integer productId;
	private Integer productImageId;
	private String type;
	private Integer fileInfoId;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private Integer deleteFlag;
	private Date createDate;
	private Date modifyDate;
	
	public Integer getProductImageId() {
		return productImageId;
	}
	public void setProductImageId(Integer productImageId) {
		this.productImageId = productImageId;
	}
	public Integer getFileInfoId() {
		return fileInfoId;
	}
	public void setFileInfoId(Integer fileInfoId) {
		this.fileInfoId = fileInfoId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
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
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
}
