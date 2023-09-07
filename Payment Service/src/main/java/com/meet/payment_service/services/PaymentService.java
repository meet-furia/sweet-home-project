package com.meet.payment_service.services;


import com.meet.payment_service.entity.TransactionDetailsEntity;

public interface PaymentService {
    TransactionDetailsEntity performTransaction(TransactionDetailsEntity transactionDetailsEntity);
    TransactionDetailsEntity getTransaction(int id);
}
