package com.meet.payment_service.dao;


import com.meet.payment_service.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<TransactionDetailsEntity,Integer> {
}
