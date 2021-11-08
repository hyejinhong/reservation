package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.service.CommentService;

@RestController
@RequestMapping(path="/api/comments")
public class CommentApiController {
	@Autowired
	CommentService commentService;	

	@ApiOperation(value = "댓글 목록")
	@ApiResponses({
		@ApiResponse(code=200, message="OK"),
		@ApiResponse(code=500, message="Exception")
	})
	@GetMapping
	public Map<String, Object> getComments(
			@RequestParam(defaultValue="0") int productId, @RequestParam(defaultValue="0") int start) {
		
		// 목록 가져오기
		List<ReservationUserComment> comments = commentService.getComments(productId, start);
		int totalCount = commentService.getTotalCount(productId);
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("reservationUserComments", comments);
		map.put("totalCount", totalCount);
		map.put("commentCount", comments.size());
		
		return map;
	}
}
