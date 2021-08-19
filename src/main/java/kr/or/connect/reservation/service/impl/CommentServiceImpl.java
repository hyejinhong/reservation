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
	public List<ReservationUserComment> getComments(Integer productId) {
		return commentDao.findByProductId(productId);
	}

	@Override
	public int deleteDisplayInfo(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReservationUserComment addComment(ReservationUserComment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount(Integer productId, Integer start) {
		return commentDao.getTotalCount(productId, start);
	}

	@Override
	public int getCommentCount(Integer productId, Integer start) {
		return commentDao.getCommentCount(productId, start);
	}

}
