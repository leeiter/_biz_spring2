package com.biz.naver.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.naver.config.NaverConfig;
import com.biz.naver.domain.NaverSearchCover;
import com.biz.naver.domain.NaverVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverService {
	
	private final String naver_news = "https://openapi.naver.com/v1/search/news.json";
	private final String naver_books = "https://openapi.naver.com/v1/search/book.json";
	private final String naver_movies = "https://openapi.naver.com/v1/search/movie.json";
	
	public List<NaverVO> naverSearch(String cat, String search) throws UnsupportedEncodingException, URISyntaxException {

		String queryString = URLEncoder.encode(search, "UTF-8");
		
		if(cat.equalsIgnoreCase("NEWS")) {
			queryString = naver_news + "?query=" + queryString;
		} else if(cat.equalsIgnoreCase("MOVIE")) {
			queryString = naver_movies + "?query=" + queryString;
		} else {
			queryString = naver_books + "?query=" + queryString;
		}
		
		
		// RestTemplete으로 조회하기 위해 Header 값을 설정
		HttpHeaders header = new HttpHeaders();
		
		header.set("X-Naver-Client-Id", NaverConfig.NAVER_CLIENT_ID);
		header.set("X-Naver-Client-Secret", NaverConfig.NAVER_CLIENT_SEC);
		HttpEntity<String> entity = new HttpEntity<String>(header);
		
		// 주소변환
		URI restURI = new URI(queryString);
		
		RestTemplate restTemp = new RestTemplate();
		
		// 데이터를 받아서 사용할 객체 타입지정
		ResponseEntity<String> strResult = null;
		
		ResponseEntity<NaverSearchCover> restResult = null;
		
		// 문자열형태로 받기
		strResult = restTemp.exchange(restURI, HttpMethod.GET, entity, String.class);
		
		restResult = restTemp.exchange(restURI, HttpMethod.GET, entity, NaverSearchCover.class);
		
		NaverSearchCover nsc = restResult.getBody();
		log.debug("가져온 데이터개수 {} : " + nsc.total);
		if(Integer.valueOf(nsc.total) < 1) return null;
		else return nsc.items;
		// return strResult.getBody();
		
	}
	
}
