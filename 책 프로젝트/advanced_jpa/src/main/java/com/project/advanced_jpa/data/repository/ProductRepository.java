package com.project.advanced_jpa.data.repository;

import com.project.advanced_jpa.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {  // Long 은 pk의 타입
    
    // 매서드를 따로 상속받아 구현하지 않아도 기본적으로 여러 매서드 존재


    /* JPQL method의 키워드 =========================================================================== */
    // find...By
    Optional<Product> findByNumber(Long number);
    List<Product> findAllByName(String name);
    Product queryByNumber(Long number);



    // exists...By
    boolean existsByNumber(Long number);

    // count...By
    long countByName(String name);

    // delete...By
    void deleteByNumber(Long number);
    long removeByName(String name);

    // ...First<number>..., ...Top<number>...
    List<Product> findFirst5ByName(String name);
    List<Product> findTop10ByName(String name);


    /* JPQL 조건자 키워드 =========================================================================== */
    // Is
    Product findByNumberIs(Long number);
    Product findByNumberEquals(Long number);

    // (is)Not
    Product findByNumberIsNot(Long number);
    Product findByNumberNot(Long number);

    // (is)Null, (is)NotNull
//    List<Product> findByUpdateAtNull();
//    List<Product> findByUpdateAtIsNull();
//    List<Product> findByUpdateAtNotNull();
//    List<Product> findByUpdateAtIsNotNull();

    // (is)True, (is)False
    // 실제 코드 중 boolean 타입이 없어 주석 처리
//    Product findByIsActiveTrue();
//    Product findByIsActiveIsTrue();
//    Product findByIsActiveFalse();
//    Product findByIsActiveIsFalse();

    // And, Or
    Product findByNumberAndName(Long number, String name);
    Product findByNumberOrName(Long number, String name);

    // (is)GreaterThan, (is)LessThan, (is)Between
    List<Product> findByPriceIsGreaterThan(Long price);
    List<Product> findByPriceGreaterThan(Long price);
    List<Product> findByPriceGreaterThanEqual(Long price);
    List<Product> findByPriceIsLessThan(Long price);
    List<Product> findByPriceLessThan(Long price);
    List<Product> findByPriceLessThanEqual(Long price);
    List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

    // (is)Like
    List<Product> findByNameLike(String name);
    List<Product> findByNameIsLike(String name);

    // (is)Containing(==Conains)
    List<Product> findByNameContains(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameIsContaining(String name);

    //(is)StartingWith(==StartsWith)
    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameIsStartingWith(String name);

    // (is)EndingWith(==endsWith)
    List<Product> findByNameEndsWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameIsEndingWith(String name);


    /* 정렬 =========================================================================== */
    // Asc, Desc
    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findAllByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);
    List<Product> findAllByNameOrderByNumberDesc(String name);

    // 기준 여러개시 And 붙이지 않음
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

    // 매개변수 활용한 정렬
    List<Product> findByName(String name, Sort sort);


    /* 페이징 =========================================================================== */
    // 페이징 메서드
    Page<Product> findByName(String name, Pageable pageable);

    // @Query 이용한 method
    @Query("SELECT p FROM Product AS p WHERE p.name = ?1")
    List<Product> findByName(String name);

    @Query("SELECT p FROM Product AS p WHERE p.name = :name")
    List<Product> findByNameParam(@Param("name") String name);

    @Query("SELECT p.name, p.price, p.stock FROM Product  p WHERE p.name = :name")
    List<List<Object>> findByNameParam2(@Param("name") String name);


}
