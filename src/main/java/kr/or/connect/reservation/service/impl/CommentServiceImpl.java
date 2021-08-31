package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<ReservationUserComment> getComments(Integer productId, Integer start) {
		return commentDao.findByProductId(productId, start);
	}

	@Override
	public int getTotalCount(Integer productId) {
		return commentDao.getTotalCount(productId);
	}

	@Override
	public int getCommentCount(Integer productId, Integer start) {
		return commentDao.getCommentCount(productId, start);
	}

}
