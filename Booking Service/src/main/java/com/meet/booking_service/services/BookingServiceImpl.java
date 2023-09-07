package com.meet.booking_service.services;

import com.meet.booking_service.dao.BookingDao;
import com.meet.booking_service.dto.TransactionDTO;
import com.meet.booking_service.entities.BookingInfoEntity;
import com.meet.booking_service.entities.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


@Service
public class BookingServiceImpl implements BookingService{

    @Value("${paymentApp.url}")
    private String paymentAppUrl;

    @Autowired
    BookingDao bookingDao;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public BookingInfoEntity acceptBookingDetails(BookingInfoEntity bookingInfoEntity) {
        // Generating random room numbers
        bookingInfoEntity.setRoomNumbers(getStringRandomNumbers(getRandomNumbers(bookingInfoEntity.getNumOfRooms())));
        bookingInfoEntity.setRoomPrice(calculatingRoomPrice(bookingInfoEntity.getNumOfRooms(),
                calculatingTotalNumberOfDays(bookingInfoEntity.getFromDate(), bookingInfoEntity.getToDate())));

        bookingInfoEntity.setBookedOn(new Date());

        return bookingDao.save(bookingInfoEntity);
    }

    @Override
    public BookingInfoEntity bookRoom(int bookingId, TransactionDetailsEntity transactionDetailsEntity) {
        transactionDetailsEntity.setBookingId(bookingId);
        BookingInfoEntity bookingCreated = bookingDao.findById(bookingId).get();

        //Setting bookingId for a transaction
        transactionDetailsEntity.setBookingId(bookingId);

        System.out.println("Transaction entity after setting booking id : " + transactionDetailsEntity);
        System.out.println("Payment mode sent : " + transactionDetailsEntity.getPaymentMode());
        int transactionIdCreated = restTemplate.postForObject(paymentAppUrl, transactionDetailsEntity, Integer.class);

        //Setting transactionId for the booking created
        bookingCreated.setTransactionId(transactionIdCreated);

        String message = "Booking confirmed for user with aadhaar number: "
                + bookingCreated.getAadharNumber()
                + "    |    "
                + "Here are the booking details:    " + bookingCreated;
        System.out.println(message);

        return bookingDao.save(bookingCreated);

    }

    @Override
    public boolean checkBookingIdExistsOrNot(int bookingId) {
        BookingInfoEntity bookingInfoEntity = bookingDao.findById(bookingId).get();
        if (bookingInfoEntity == null) {
            return false;
        }
        return true;
    }

    private static ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }

    private static String getStringRandomNumbers(ArrayList<String> arrayList) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            s.append(arrayList.get(i));
            if (i < arrayList.size() - 1) {
                s.append(",");
            }
        }
        return s.toString();
    }

    private static int calculatingRoomPrice(int totalNumberOfRooms, int totalDays) {
        return 1000 * totalNumberOfRooms * totalDays;
    }


    private static int calculatingTotalNumberOfDays(Date fromDate, Date toDate) {
        // Calculate the number of days between the dates
        return (int) ChronoUnit.DAYS.between(fromDate.toInstant(), toDate.toInstant());
    }

}
