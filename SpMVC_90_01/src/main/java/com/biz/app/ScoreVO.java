package com.biz.app;

public class ScoreVO {
	
	private int kor;
	private int eng;
	private int math;
	private int sc;
	private int music;

	private int sum;
	private int avg;
	
	public ScoreVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ScoreVO(int kor, int eng, int math, int sc, int music, int sum, int avg) {
		super();
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sc = sc;
		this.music = music;
		this.sum = sum;
		this.avg = avg;
	}
	
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getSc() {
		return sc;
	}
	public void setSc(int sc) {
		this.sc = sc;
	}
	public int getMusic() {
		return music;
	}
	public void setMusic(int music) {
		this.music = music;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getAvg() {
		return avg;
	}
	public void setAvg(int avg) {
		this.avg = avg;
	}
	
	@Override
	public String toString() {
		return "ScoreVO [kor=" + kor + ", eng=" + eng + ", math=" + math + ", sc=" + sc + ", music=" + music + ", sum="
				+ sum + ", avg=" + avg + "]";
	}
		
}
