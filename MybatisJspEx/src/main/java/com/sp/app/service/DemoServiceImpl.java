package com.sp.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sp.app.mapper.DemoMapper;
import com.sp.app.model.Demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 	@Transactional  > 테이블이 여러 개 있는 것에 쓰는 것이 효과적이라고 함
 	 :기본적으로 메소드 실행 전 트랜잭션을 시작하고 메소드가 성공적으로 실행되면 크랜잭션 commit
 	 :예외가 발생하면 RollBack
 	 -readOnly 속성
 	  :true 설정하면 트랜잭션이 읽기 전용이 됨
 	  :DB 내용을 INSERT, UPDATE, DELETE 하면 예외 발생
 	  :조회만 가능하다는 것
 	 -전파규칙
 	  :기본-Propagation.REQUIRED
 	   :이미 존재하는 트랜잭션이 있으면 그 트랜잭션을 사용하고 없으면 새로 만듦
 	 -RollBackFor
 	  :예외 발생 시 롤백 대상 명시
 	 -클래스 레벨과 메소드 레벨을 동시에 설정하면 메소드 레벨이 우선 순위가 높음
 	   
*/

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class DemoServiceImpl implements DemoService {
	private final DemoMapper mapper;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	@Override
	public void insertDemo(Demo dto) throws Exception {
		try {
			mapper.insertDemo1(dto);
			mapper.insertDemo2(dto);
			mapper.insertDemo3(dto);
		} catch (Exception e) {
			log.info("insertDemo : ", e);
			
			throw e;
		}
	}
}
