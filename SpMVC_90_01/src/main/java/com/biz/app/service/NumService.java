package com.biz.app.service;

import org.springframework.stereotype.Service;

import com.biz.app.ScoreVO;

/*
 * Service 클래스
 * @Service Annotation을 설정한 클래스
 * 
 * Controller가 사용자의 요청을 받았는데
 * 단순한 연산을 
 */

// @Component : 사용이 가능하지만 Controller, service를 통틀어서 사용하는 Annotation라
// 이걸 사용하게 되면 정확하게 어떤 클래스를 작업하는지 구별이 어려워서 사용하지 않는다.
@Service
public class NumService {
	
	public int add(int num1, int num2) {
		int sum = 0;
		sum = num1 + num2;
		return sum;
	}

	public int even(int start, int end) {
		int sum = 0;
		for(int i = start ; i <= end ; i++) {
			if(i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}

	public int score(int kor, int eng, int math, int sc, int music) {
		
		int sum = 0;
		
		sum = kor + eng + math + sc + music;

		return sum;
	}

	public int scorea(int kor, int eng, int math, int sc, int music) {
		
		int avg = 0;
		avg = (kor + eng + math + sc + music) / 5;
		
		// TODO Auto-generated method stub
		return avg;
	}

	public void score(ScoreVO scoreVO) {
		
		int sum = scoreVO.getKor();
		sum += scoreVO.getEng();
		sum += scoreVO.getMath();
		sum += scoreVO.getSc();
		sum += scoreVO.getMusic();
		
		scoreVO.setSum(sum);
		scoreVO.setAvg(sum / 5);
		// TODO Auto-generated method stub
		
	}

}
