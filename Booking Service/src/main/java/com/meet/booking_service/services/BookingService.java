package com.meet.booking_service.services;

import com.meet.booking_service.entities.BookingInfoEntity;
import com.meet.booking_service.entities.TransactionDetailsEntity;

public interface BookingService {
    public BookingInfoEntity acceptBookingDetails(BookingInfoEntity bookingInfoEntity);

    public boolean checkBookingIdExistsOrNot(int bookingId);

    public BookingInfoEntity bookRoom(int bookingId, TransactionDetailsEntity transactionDetailsEntity);
}