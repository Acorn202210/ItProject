//
//package com.acorn.project.faq.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.acorn.project.faq.dto.FaqDto;
//import com.acorn.project.faq.service.FaqService;
//
//@Controller
//public class FaqController {
//
//	@Autowired
//	private FaqService service;
//	
//	@RequestMapping("/faq/list")
//	public String list(HttpServletRequest request) {
//		service.getList(request);
//		return "faq/list";
//	}
//	
//	@RequestMapping("/faq/insertform")
//	public String insertform() {
//		return "faq/insertform";
//	}
//	
//	@RequestMapping("/faq/insert")
//	public String insert(FaqDto dto, HttpSession session) {
//		service.saveContent(dto);
//		return "faq/insert";
//	}
//	
//	@RequestMapping("/faq/detail")
//	public String detail(HttpServletRequest request) {
//		
//		service.getDetail(request);
//		
//		return "faq/detail";
//	}
//	
//	@RequestMapping("/faq/delete")
//	public String delete(int num, HttpServletRequest request) {
//		service.deleteContent(num);
//		return "redirect:/faq/list";
//	}
//	
//	@RequestMapping("/faq/updateform")
//	public String updateForm(int request) {
//		service.getData(request);
//		return "faq/updateform";
//	}
//	
//	@RequestMapping("/faq/update")
//	public String update(FaqDto dto) {
//		service.updateContent(dto);
//		return "faq/update";
//	}
//}