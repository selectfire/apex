package com.selectfire.apex.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.selectfire.apex.model.User;
import com.selectfire.apex.model.apisender.ResponseDataVo;
import com.selectfire.apex.service.UserService;

@RestController
public class ApiController {

	private UserService userService;

	@Autowired
	public ApiController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * rest template sample
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public ResponseDataVo hello() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.add("TRN-Api-Key", "4533a56a-2f8b-4b82-86bc-6a2c1e1caf87");

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResponseDataVo> responseObj = restTemplate.exchange(
				"https://public-api.tracker.gg/apex/v1/standard/profile/5/DOMANGCHUR", HttpMethod.GET, entity,
				ResponseDataVo.class);

		System.out.println(responseObj);

		return null;
	}

	/**
	 * 검색버튼 api rest api Response to client
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "users/{name}", method = RequestMethod.GET)
	public User getDataToDb(@PathVariable("name") String name) {
		User user = userService.get(name);
		return user;
	}
}
