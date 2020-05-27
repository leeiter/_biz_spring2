package com.biz.shop.persistence.sql;

public class CreateTableSQL {

	// 테이블 생성 부분을 코딩하기 위한 방법
	public static String create_user_table = " CREATE TABLE IF NOT EXISTS tbl_users ("
			+ "	id bigint  PRIMARY KEY AUTO_INCREMENT, " 
			+ "	user_name varchar(50) UNIQUE, "
			+ "	user_pass varchar(125), " 
			+ " enabled boolean default true, "
			+ "	nickname varchar(50), "
			+ "	email varchar(50), "
			+ "	phone varchar(20), " 
			+ "	address varchar(125) " + " ) ";

	public static String create_auth_table = " CREATE TABLE IF NOT EXISTS authorities ("
			+ "	id bigint PRIMARY KEY AUTO_INCREMENT," 
			+ "    username varchar(50)," + "    authority varchar(50)"
			+ " ) ";
	
	public static String create_product_table 
		= "CREATE TABLE IF NOT EXISTS tbl_product "
				+ "( p_code VARCHAR(6) PRIMARY KEY, "
				+	"p_name VARCHAR(125),"
				+	"p_bcode VARCHAR(6),"
				+	"p_dcode VARCHAR(6),"
				+	"p_iprice int,"
				+	"p_oprice int,"
				+	"p_vat boolean default true, "
				+	"p_file VARCHAR(255) )" ;

	public static String create_pro_color_table 
	= "CREATE TABLE IF NOT EXISTS tbl_pro_color "
			+ "( c_seq bigint AUTO_INCREMENT PRIMARY KEY, "
			+ " size_seq bigint NOT NULL, "  // size table s_seq와 연동
			+ " c_color VARCHAR(20) NOT NULL, "
			+ " c_qty int default 0 )";
	
	
	public static String drop_options_table 
	= "DROP TABLE IF EXISTS tbl_options ";
	
	public static String create_options_table 
	= "CREATE TABLE IF NOT EXISTS tbl_options "
			+ "( o_seq bigint AUTO_INCREMENT PRIMARY KEY,"
			+ " o_division VARCHAR(6) NOT NULL, " // 상품 테이블과 연동
			+ " o_standard VARCHAR(6) NOT NULL, " // 상품 테이블과 연동
			+ " o_name VARCHAR(20) NOT NULL ) ";
	
	/*
	 * Transactional 에서는 for() 문을 통해서 insert 하는 것은 최악의 코드이다.
	 * 그리고 Transactional도 작동이 하지 않는다.
	 * 그래서 밑에 있는 방법처럼 동적쿼리를 사용해서 하는 것이 괜찮은 코드이고,
	 * Transaction도 작동이 가능하다.
	 */
	public static String insert_options_table 
	= "INSERT INTO tbl_options ( o_division, o_standard, o_name ) "
			+ " VALUES ( 'SIZE', 'S', 'Small' ), "
			+ " ( 'SIZE', 'M', 'Middle' ), "
			+ " ( 'SIZE', 'L', 'Large' ), "
			+ " ( 'SIZE', 'XL', 'Extra Large' ), "
			+ " ( 'SIZE', '2XL', '2Extra Large' ), "
			+ " ( 'SIZE', '3XL', '3Extra Large' ), "
			
			+ " ( 'COLOR', 'WHITE', '흰색' ), "
			+ " ( 'COLOR', 'BLACK', '검정색' ), "
			+ " ( 'COLOR', 'GRAY', '회색' ), "
			+ " ( 'COLOR', 'RED', '빨간색' ), "
			+ " ( 'COLOR', 'GREEN', '초록색' ), "
			+ " ( 'COLOR', 'YELLOW', '노란색' ) ";
	
	
	
	public static String create_pro_size_table 
	= "CREATE TABLE IF NOT EXISTS tbl_pro_size "
			+ "( s_seq bigint AUTO_INCREMENT PRIMARY KEY,"
			+ " p_code VARCHAR(6) NOT NULL, " // 상품 테이블과 연동
			+ " s_size VARCHAR(20) NOT NULL ) ";
	
	public static String create_fileup_table 
		= "CREATE TABLE IF NOT EXISTS tbl_profile( "
				+ " id bigint AUTO_INCREMENT PRIMARY KEY, "
				+ " file_pcode VARCHAR(6),"
				+ " file_origin_name VARCHAR(255),"
				+ " file_upload_name VARCHAR(255) )";
	
}