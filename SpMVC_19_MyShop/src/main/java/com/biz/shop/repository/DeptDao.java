package com.biz.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz.shop.domain.DeptVO;

@Repository
public interface DeptDao extends JpaRepository<DeptVO, Long> {

}
