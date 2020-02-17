package com.biz.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz.shop.domain.Authorities;
import com.biz.shop.domain.DeptVO;
import com.biz.shop.domain.ProductVO;

public interface DeptDao extends JpaRepository<DeptVO, Long> {

}
