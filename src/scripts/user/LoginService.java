package com.ibm.iga.reminder.service.user;


import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.iga.reminder.service.inter.ILoginService;

@Service
public class LoginService implements ILoginService {

	@Override
	public boolean validate(String user, String password) {
		JSONObject request = new JSONObject();
		request.put("username", user);
		request.put("password", password);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic " + new String(Base64.encodeBase64((user + ":" + password).getBytes(Charset.forName("US-ASCII")))));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		ResponseEntity<String> response = restTemplate.exchange("https://lmc2.watson.ibm.com:15013/newsletter/ws/login", 
				HttpMethod.POST, entity, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			JSONObject res = new JSONObject(response.getBody());
			if ((res.get("resultCode").equals("1") && ((JSONObject)res.get("result")).get("loginValidateResult").equals("success"))){
				return true;
			} 
		} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			//wrong password
		}
		return false;
	}

}
