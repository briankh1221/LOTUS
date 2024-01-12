package com.project.jpa.data.repository;

import com.project.jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {  // Long 은 pk의 타입
    
    // 매서드를 따로 상속받아 구현하지 않아도 기본적으로 여러 매서드 존재
}
