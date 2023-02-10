package com.acorn.project.faq.dto;

import org.apache.ibatis.type.Alias;

@Alias("faqRes")
public class FaqRes {
	private String question;
	private String content;
	
	public FaqRes() {}

	public FaqRes(String question, String content) {
		super();
		this.question = question;
		this.content = content;
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
