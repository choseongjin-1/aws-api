package com.aws.api.comment;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	/**
	 * 리스트 댓글조회
	 * @param userSrno
	 * @param listSrno
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/selectComment/{userSrno}/{listSrno}")
    public Map<String, Object> selectComment(@PathVariable String userSrno, @PathVariable String listSrno) throws Exception {
		return commentService.selectComment(userSrno, listSrno);
    }
	
	/**
	 * 댓글 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/insertComment")
	public Map<String, Object> insertComment(@RequestBody Map<String, Object> param) throws Exception {
		return commentService.insertComment(param);
    }

}
