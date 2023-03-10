package com.acorn.project.lecture.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.project.lecture.dto.LectureDto;
import com.acorn.project.lecture.dto.LectureReviewDto;
import com.acorn.project.lecture.dto.LectureStudentDto;
import com.acorn.project.lecture.service.LectureService;



@Controller
public class LectureController {

	@Autowired private LectureService service;
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping(
			value="/lecture/images/{imageName}",
			produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE}
		)
	@ResponseBody
	public byte[] galleryImage(@PathVariable("imageName") String imageName) throws IOException {
		
		String absolutePath=fileLocation+File.separator+imageName;
		//파일에서 읽어들일 InputStream
		InputStream is=new FileInputStream(absolutePath);
		//이미지 데이터(byte)를 읽어서 배열에 담아서 클라이언트에게 응답한다.
		return IOUtils.toByteArray(is);
	}	

	//프론트엔드 리스트
	@RequestMapping(value = "/lecture/list")
	public String lectureList(HttpServletRequest request) {
		//view 페이지에 사용될 데이터는 request 영역에 담는다.
		service.LectureList(request);
		
		return "lecture/list";
	}
	
	@RequestMapping(value = "/lecture/upload_form")
	public String uploadForm() {
		
		return "lecture/upload_form";
	}
	
	@RequestMapping(value = "/lecture/upload")
	public String upload(LectureDto dto, HttpServletRequest request) {
		service.saveImage(dto, request);
		
		return "lecture/upload";
	}
	
	@RequestMapping("/lecture/insert")
	public String insert(LectureDto dto, HttpServletRequest request) {
		service.insert(dto, request);
		
		return "lecture/upload";
	}
	
	@RequestMapping("/lecture/detail")
	public String detail(HttpServletRequest request) {
		
		service.getDetail(request);
		
		return "lecture/detail";
	}
	
	@RequestMapping(value = "/lecture/lecture_view", method = RequestMethod.GET)
	public String request(HttpServletRequest request) {
		service.getDetail(request);
		return "lecture/lecture_view";
	}
	
	
	
	@RequestMapping("/lecture/delete")
	public String delete(int num, HttpServletRequest request) {
		service.deleteContent(num, request);
		return "redirect:/";
	}

	@RequestMapping("/lecture/updateform")
	public String updateForm(HttpServletRequest request) {
		service.getData(request);
		return "lecture/updateform";
	}
	
	@RequestMapping("/lecture/update")
	public String update(LectureDto dto,HttpServletRequest request) {		
		service.updateContent(dto,request);
		return "lecture/update";
	}
	
	
	//새로운 댓글 저장 요청 처리
	@RequestMapping("/lecture/lectureReview_insert")
	public String commentInsert(HttpServletRequest request, int ref_group) {
      
      service.saveReview(request);
   
      return "redirect:/lecture/detail?num="+ref_group;
	}
	
	//댓글 더보기 요청 처리
	@RequestMapping("/lecture/ajax_review_list")
	public String commentList(HttpServletRequest request) {
      		
		//테스트를 위해 시간 지연시키기
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		service.moreReviewList(request);
      
		return "lecture/ajax_review_list";
	}
	
	//댓글 삭제 요청 처리
	@RequestMapping("/lecture/lectureReview_delete")
	@ResponseBody
	public Map<String, Object> commentDelete(HttpServletRequest request) {
      service.deleteReview(request);
      Map<String, Object> map=new HashMap<String, Object>();
      map.put("isSuccess", true);
      // {"isSuccess":true} 형식의 JSON 문자열이 응답되도록 한다. 
      return map;
	}
	//댓글 수정 요청처리 (JSON 으로 응답하도록 한다)
	@RequestMapping("/lecture/lectureReview_update")
	@ResponseBody
	public Map<String, Object> commentUpdate(LectureReviewDto dto, HttpServletRequest request){
      service.updateReview(dto);
      Map<String, Object> map=new HashMap<String, Object>();
      map.put("isSuccess", true);
      // {"isSuccess":true} 형식의 JSON 문자열이 응답되도록 한다. 
	      return map;
		}
	
	//수강 신청 전체 리스트
	@RequestMapping(value = "/studentLecture/list")
	public String studentLIst(HttpServletRequest request) {
		//view 페이지에 사용될 데이터는 request 영역에 담는다.
		service.studentList(request);	
		return "studentLecture/list";
	}
	
	//수강 신청 처리
	@RequestMapping("/lecture/lectureSignup")
	public String lectureSignup(LectureStudentDto dto, HttpServletRequest request) {
		service.lectureSignup(dto, request);
		return "lecture/lectureSignup";
	}
		
	@RequestMapping("/studentLecture/lectureComplete")
	public String lectureComplete(LectureStudentDto dto, HttpServletRequest request) {
		service.lectureComplete(dto, request);
		return "studentLecture/lectureComplete";
	}
	


}
