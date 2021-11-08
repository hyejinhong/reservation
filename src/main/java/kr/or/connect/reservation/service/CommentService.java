package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ReservationUserComment;

public interface CommentService {
	List<ReservationUserComment> getComments(Integer productId, Integer start);
	int getTotalCount(Integer productId);
}
