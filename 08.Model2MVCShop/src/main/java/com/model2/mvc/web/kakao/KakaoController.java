package com.model2.mvc.web.kakao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model2.mvc.service.domain.User;

//==> 회원관리 Controller
@Controller
@RequestMapping("/kakao/*")
public class KakaoController {
	
	///Field
	//private final KakaoTokenJsonData kakaoTokenJsonData;
    //private final KakaoUserInfo kakaoUserInfo;

	public KakaoController(){
		System.out.println(this.getClass());
	}
	
	
	
	
	@GetMapping(value="oauth")
	public String kakaoLogin(@RequestBody String code) {
		System.out.println("kakao login 실행됨");
		User user = new User();
		String kakaoEmail = "";
		user.setUserId(kakaoEmail);
		return "redirect:/index.jsp";
	}
}