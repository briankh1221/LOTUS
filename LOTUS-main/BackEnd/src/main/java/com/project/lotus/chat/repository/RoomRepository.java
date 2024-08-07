package com.project.lotus.chat.repository;

import com.project.lotus.chat.entity.Room;
import com.project.lotus.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByBuyer_EmailAndProduct_ProductIdx(String buyerEmail, Long productIdx);

    Optional<List<Room>> findByBuyer_EmailOrProduct_User_Email(String buyerEmail, String productUserEmail);

    Optional<Room> findByRoomIdx(Long RoomIdx);

    Optional<Room> findByProduct(Product product);
}
