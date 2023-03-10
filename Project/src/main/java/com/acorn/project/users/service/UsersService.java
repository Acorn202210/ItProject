package com.acorn.project.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.project.users.dto.UsersDto;



public interface UsersService {
	public Map<String, Object> isExistId(String inputId);
	public void addUser(UsersDto dto);
	public void loginProcess(UsersDto dto, HttpSession session, HttpServletResponse response);
	public void getInfo(HttpSession session, ModelAndView mView);
	public Map<String, Object> saveProfileImage(HttpServletRequest request, 
			MultipartFile mFile);
	public void updateUser(UsersDto dto, HttpSession session, ModelAndView mView);
	public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView mView);
	public void deleteUser(HttpSession session, ModelAndView mView);
	public void forceDelete(String id, HttpServletRequest request);
	public void getList(HttpServletRequest request);
	public void myLectureList(HttpServletRequest request);

}