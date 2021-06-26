package com.example.spring02.model.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.spring02.model.memo.dto.MemoDTO;

public interface MemoDAO {
	String list_memo = "select * from memo order by idx desc";	// 메모 내림차순 select 쿼리
	String insert_memo = "insert into memo (idx, writer, memo) values "
			+ "((select nvl(max(idx) + 1, 1) from memo), #{writer}, #{memo})";	// 메모 삽입 쿼리
	
	// 메모 리스트
	@Select(list_memo)
	public List<MemoDTO> list();
	
	// 메모 추가
	@Insert(insert_memo)
	public void insert(@Param("writer") String writer, @Param("memo") String memo);
	
	// 메모 선택 
	@Select("select * from memo where idx=#{idx}")
	public MemoDTO memo_view(@Param("idx") int idx);
	
	// 메모 수정
	@Update("update memo set writer=#{writer}, memo=#{memo} where idx=#{idx}")
	public void memo_update(MemoDTO dto);
	
	// 메모 삭제
	@Delete("delete from memo where idx=#{idx}")
	public void memo_delete(@Param("idx") int idx);
}
