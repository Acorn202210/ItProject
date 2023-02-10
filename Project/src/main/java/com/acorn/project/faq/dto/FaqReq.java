package com.acorn.project.faq.dto;

import org.apache.ibatis.type.Alias;

@Alias("faqReq")
public class FaqReq {
	private int num;
	private String question;
	private String content;
	
	public FaqReq() {}

	public FaqReq(int num, String question, String content) {
		super();
		this.num = num;
		this.question = question;
		this.content = content;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
