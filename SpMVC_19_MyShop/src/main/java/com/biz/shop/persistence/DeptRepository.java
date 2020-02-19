package com.biz.shop.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz.shop.domain.DeptVO;

@Repository
public interface DeptRepository extends JpaRepository<DeptVO, Long> {
	
}
