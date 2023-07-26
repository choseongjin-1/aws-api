package com.aws.api.rgt;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.aws.api.utils.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Service
public class RGTService {
	
	@Autowired
	ResponseUtils responseUtils;
	
	@Autowired
	RGTMapper rgtMapper;
	
	@Value("${spring.security.oauth2.client.registration.google.client-id}")
	String clientId;
	
	@Value("${spring.security.oauth2.client.registration.google.client-secret}")
	String clientSecret;
	
	@Value("${spring.security.oauth2.client.registration.google.token-uri}")
	String tokenUri;
	
	@Value("${spring.security.oauth2.client.registration.google.resource-uri}")
	String resourceUri;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 데이터 조회
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectRgt() throws Exception {
		List<Map<String, Object>> result = rgtMapper.selectRgt();
		if (ObjectUtils.isEmpty(result)) {
			return responseUtils.makeFailResponse("");
		}
		return responseUtils.makeSuccessResponse(result);
	}
	
	/**
	 * db에 데이터 추가
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertRgt(Map<String, Object> param) throws Exception {
		int result = rgtMapper.insertRgt(param);
		if (result < 1) {
			return responseUtils.makeFailResponse(param);
		}
		return responseUtils.makeSuccessResponse("");
	}
	
	/**
	 * 중복제거
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> deleteRgt() throws Exception {
		rgtMapper.deleteRgt();
		rgtMapper.updateRgt();
		
		return responseUtils.makeSuccessResponse("");
	}
	
	/**
	 * 구글로그인
	 * @return
	 * @throws Exception
	 */
	public String googleLogin(String authCode) throws Exception {
		return getUserInfo(authCode);
	}
	
	/**
	 * 토큰정보 받아오기
	 * @param authorizationCode
	 * @return
	 * @throws Exception
	 */
	private String getToken(String authorizationCode) throws Exception {
		
		OkHttpClient client = new OkHttpClient();
		
		JSONObject params = new JSONObject();
		params.put("code", authorizationCode);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", "http://localhost:8080");
        params.put("grant_type", "authorization_code");
		
		RequestBody formBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), params.toString());
		Request.Builder builder = new Request.Builder().url(tokenUri).post(formBody);
	    builder.addHeader("Content-type", " application/json; charset=utf-8");
	    Request request = builder.build();
	    Response response = client.newCall(request).execute();
	    
	    if (response.isSuccessful()) {
	    	Map<String, String> responseMap = new ObjectMapper().readValue(response.body().string(), Map.class);
	    	String token = responseMap.get("access_token");
	    	response.close();
	    	return token;
	    }
		
		return "";
	}
	
	/**
	 * 유저정보 조회
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private String getUserInfo(String code) throws Exception {
		
		OkHttpClient client = new OkHttpClient();
		
		String token = getToken(code);
		if (token.isEmpty()) {
			return "token not found error";
		}
		
		String url = resourceUri;
		
		Request.Builder builder = new Request.Builder().url(resourceUri).get();
		builder.addHeader("Authorization", "Bearer " + token);
	    builder.addHeader("Content-type", " application/json; charset=utf-8");
	    Request request = builder.build();
	    Response response = client.newCall(request).execute();
		
		LOGGER.debug("response0 =======>>>>>>" + token);
		LOGGER.debug("url =======>>>>>>" + url);
		
		if (response.isSuccessful()) {
	    	return response.body().string();
	    }
		return "";
		
	}

}
