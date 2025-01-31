﻿package com.sp.app.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class MyUtil {
	/**
	* 문자열에서 HTML 태그를 제거하는 메소드
	* 
	* @param str		HTML 태그를 제거할 문자열
	* @return			HTML 태그가 제거된 문자열
	*/
	public String removeHtmlTag(String str) {
		if(str == null || str.length() == 0) {
			return "";
		}

		String regex = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
		String result = str.replaceAll(regex, "");
		
		return result;
    }

	/**
	* HTML 문서에서 img 태그의 src 속성값을 추출하는 메소드
	* 
	* @param html		html 문자열
	* @return			추출된 src 속성값을 가지고 있는 List 객체 
	*/
	public List<String> getImgSrc(String html) {
		List<String> result = new ArrayList<String>();
		
		if(html == null || html.length() == 0) {
			return result;
		}

		String regex = "<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>";
		Pattern nonValidPattern = Pattern.compile(regex);

		Matcher matcher = nonValidPattern.matcher(html);
		while (matcher.find()) {
			result.add(matcher.group(1));
		}
		
		return result;
    }

	/**
	* 특수 문자를 HTML 문자로 변경 및 엔터를 <br>로 변경 하는 메소드
	* 
	* @param str		특수 문자를 HTML 문자로 변경할 문자열
	* @return			특수 문자가 HTML 문자로 변경된 문자열
	*/
	public String htmlSymbols(String str) {
		if(str == null || str.length() == 0) {
			return "";
		}

		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("<", "&lt;");
    	 //아래 순서를 꼬옥 잘 지키라고 하심.. but i can't understand..
		str = str.replaceAll(" ", "&nbsp;"); // \\s를 사용할 경우 \n 아래에서 사용해야 한다.
		str = str.replaceAll("\n", "<br>");
    	 
		return str;
	}
     
	/**
	* 중간 이름 마스킹 처리
	* @param userName		이름
	* @return				마스킹 처리된 이름
	*/
	public String nameMasking(String userName) {
		String result = "";
    	 
		try {
			userName = userName.replaceAll("\\s", "");
			
			if(userName.length() < 2) {
				return userName;
			}
        	 
			String firstName, midName, lastName, s;
        	 
			firstName = userName.substring(0, 1); // 성
			midName = "";
			if(userName.length() > 2) {
				midName = userName.substring(1, userName.length()-1); // 중간이름
			}
			s = "";
			for(int i = 0; i<midName.length(); i++) {
				s += "*";
			}
			if(userName.length() > 2) {
				lastName = userName.substring(userName.length()-1, userName.length()); // 마지막 글자
			} else {
				lastName = "*";
			}
        	 
			result = firstName + s + lastName;
		} catch (Exception e) {
		}
    	 
		return result;
	}

	/**
	* 정규식을 이용하여 E-Mail을 검사하는 메소드
	* 
	* @param email		검사할 E-Mail
	* @return			올바른 E-Mail 이지의 여부
	*/
	public boolean isValidEmail(String email) {
		if (email == null) return false;
		
		boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", 
						email.trim());
		return b;
	}
}
