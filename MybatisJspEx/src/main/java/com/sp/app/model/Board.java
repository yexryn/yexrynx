package com.sp.app.model;

//웬만한 dto는 다 model이라는 package를 준다고 함 > domain > model
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//getter&setter > annotation 으로 대체
@Getter
@Setter
@NoArgsConstructor //인자 없는 생성자
@AllArgsConstructor //인자 있는 생성자 > 테스트를 위하여 사용 < 없어도 됨
public class Board {
	private long num;
	private String name;
	private String pwd;
	private String subject;
	private String content;
	private String ipAddr;
	private String reg_date;
	private int hitCount;
}
