package com.meet.booking_service.controller;

import com.meet.booking_service.dto.BookingDTO;
import com.meet.booking_service.dto.TransactionDTO;
import com.meet.booking_service.entities.BookingInfoEntity;
import com.meet.booking_service.entities.TransactionDetailsEntity;
import com.meet.booking_service.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/hotel")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<BookingDTO> bookRoom(@RequestBody BookingDTO bookingDTO) {
        BookingInfoEntity newBooking = modelMapper.map(bookingDTO, BookingInfoEntity.class);
        System.out.println(newBooking);
        BookingInfoEntity savedBooking = bookingService.acceptBookingDetails(newBooking);
        System.out.println(savedBooking);
        BookingDTO savedBookingDTO = modelMapper.map(savedBooking, BookingDTO.class);
        System.out.println(savedBookingDTO);
        return new ResponseEntity(savedBookingDTO, HttpStatus.CREATED);
    }


    @PostMapping(value = "booking/{bookingId}/transaction")
    private ResponseEntity acceptPayment(@PathVariable(name = "bookingId") int bookingId, @RequestBody TransactionDTO transactionDTO) {
        if ((!transactionDTO.getPaymentMode().equals("CARD")) && (!transactionDTO.getPaymentMode().equals("UPI"))) {
            return new ResponseEntity("Invalid mode of payment", HttpStatus.BAD_REQUEST);
        }
        if (!bookingService.checkBookingIdExistsOrNot(bookingId)) {
            return new ResponseEntity("Invalid Booking Id", HttpStatus.BAD_REQUEST);
        }
        TransactionDetailsEntity newPayment = modelMapper.map(transactionDTO, TransactionDetailsEntity.class);
        BookingInfoEntity confirmedBooking = bookingService.bookRoom(bookingId, newPayment);
        BookingDTO confirmedBookingDTO = modelMapper.map(confirmedBooking, BookingDTO.class);

        return new ResponseEntity(confirmedBookingDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/greet")
    private ResponseEntity greet() {
        return new ResponseEntity("Hello", HttpStatus.OK);
    }
}