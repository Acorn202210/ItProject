//package com.acorn.project.notice.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.acorn.project.notice.dto.NoticeDto;
//import com.acorn.project.notice.service.NoticeService;
//
//@Controller
//public class NoticeController {
//	@Autowired private NoticeService service;
//	
//	@RequestMapping("/notice/list")
//	public String list(HttpServletRequest request) {
//		service.getList(request);
//		return "notice/list";
//	}
//	
//	@RequestMapping("/notice/insertform")
//	public String insertform() {
//		return "notice/insertform";
//	}
//	
//	@RequestMapping("/notice/insert")
//	public String insert(NoticeDto dto, HttpSession session) {
//		service.saveContent(dto);
//		return "notice/insert";
//	}
//	
//	@RequestMapping("/notice/detail")
//	public String detail(HttpServletRequest request) {
//		
//		service.getDetail(request);
//		
//		return "notice/detail";
//	}
//	
//	@RequestMapping("/notice/delete")
//	public String delete(int num, HttpServletRequest request) {
//		service.deleteContent(num, request);
//		return "redirect:/notice/list";
//	}
//	
//	@RequestMapping("/notice/updateform")
//	public String updateForm(HttpServletRequest request) {
//		service.getData(request);
//		return "notice/updateform";
//	}
//	
//	@RequestMapping("/notice/update")
//	public String update(NoticeDto dto) {
//		service.updateContent(dto);
//		return "notice/update";
//	}
//}
