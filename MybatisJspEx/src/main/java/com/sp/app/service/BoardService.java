package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.Board;

public interface BoardService {
	public void insertBoard(Board dto) throws Exception;
	public void updateBoard(Board dto) throws Exception;
	public void deleteBoard(long num) throws Exception;
	public void updateHitCount(long num) throws Exception;
	
	public int dataCount(Map<String, Object> map);
	public List<Board> listBoard(Map<String, Object> map);
	public Board findById(long num);
	public Board findByPrev(Map<String, Object> map);
	public Board findByNext(Map<String, Object> map);
}
