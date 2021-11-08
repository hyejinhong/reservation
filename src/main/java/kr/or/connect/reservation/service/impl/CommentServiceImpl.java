package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.dto.ReservationUserCommentImage;
import kr.or.connect.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<ReservationUserComment> getComments(Integer productId, Integer start) {
		
		List<ReservationUserComment> comments;
		
		// 상품 id N
		if(productId == 0) {
			comments = commentDao.findAll(start);
		}
		
		// 상품 id Y
		else {
			comments = commentDao.findByProductId(productId, start);
		}		

		// 댓글 이미지 가져오기
		for(int i=0; i<comments.size(); i++) {
			ReservationUserComment comment = comments.get(i);
			List<ReservationUserCommentImage> images = commentDao.findImages(comment.getId());
			comment.setReservationUserCommentImages(images);
		}

		return comments;
	}

	@Override
	public int getTotalCount(Integer productId) {
		// 상품 id N
		if(productId == 0) {
			return commentDao.getTotalCount();
		}
		
		// 상품 id Y
		else {
			return commentDao.getTotalCountByProductId(productId);
		}
	}
}
