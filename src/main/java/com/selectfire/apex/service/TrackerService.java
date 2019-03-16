package com.selectfire.apex.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.selectfire.apex.model.User;
import com.selectfire.apex.model.apisender.ResponseDataVo;

@Service
public class TrackerService {

	public User get(String name) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.add("TRN-Api-Key", "4533a56a-2f8b-4b82-86bc-6a2c1e1caf87");

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		RestTemplate restTemplate = new RestTemplate();

		String url = String.format("https://public-api.tracker.gg/apex/v1/standard/profile/5/{}", name);

		ResponseEntity<ResponseDataVo> responseObj = restTemplate.exchange(url, HttpMethod.GET, entity,
				ResponseDataVo.class);

		return new User(responseObj.getBody());
	}
}
