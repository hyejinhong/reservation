package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ReservationUserComment;

public interface CommentService {
	public List<ReservationUserComment> getComments(Integer productId);
	public int deleteDisplayInfo(Integer id);
	public ReservationUserComment addComment(ReservationUserComment comment);
	public int getTotalCount(Integer productId);
	public int getCommentCount(Integer productId);
}
