package com.meet.booking_service.dao;

import com.meet.booking_service.entities.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDao extends JpaRepository<BookingInfoEntity, Integer> {
}
