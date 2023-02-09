package com.acorn.project.notice.dto;

import org.apache.ibatis.type.Alias;



@Alias("noticeRes")
public class NoticeRes {
	private String title;
	private String content;
	
	public NoticeRes(){}
	
	public NoticeRes(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
