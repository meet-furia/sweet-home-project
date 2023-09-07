package com.meet.payment_service.services;

import com.meet.payment_service.dao.TransactionDAO;
import com.meet.payment_service.entity.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    TransactionDAO transactionDAO;

    public TransactionDetailsEntity performTransaction(TransactionDetailsEntity transactionDetailsEntity){
        return transactionDAO.save(transactionDetailsEntity);
    }

    public TransactionDetailsEntity getTransaction(int id){
        return transactionDAO.findById(id).get();
    }
}
