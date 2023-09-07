package com.meet.payment_service.controllers;

import com.meet.payment_service.dto.TransactionDTO;
import com.meet.payment_service.entity.TransactionDetailsEntity;
import com.meet.payment_service.services.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createTransaction(@RequestBody TransactionDTO transactionDTO){
        TransactionDetailsEntity transactionDetailsEntity = modelMapper.map(transactionDTO,
                TransactionDetailsEntity.class);
        TransactionDetailsEntity transactionDetailsEntitySaved = paymentService
                .performTransaction(transactionDetailsEntity);
        TransactionDTO transactionDTOResponse = modelMapper.map(transactionDetailsEntitySaved,
                TransactionDTO.class);
        return new ResponseEntity<>(transactionDTOResponse.getTransactionId(), HttpStatus.CREATED);
    }

    /*
     * http://127.0.0.1:8081/payment/transaction/1
     * */
    @GetMapping(value = "/transaction/{transactionId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable(name="transactionId")
                                                         int id){
        TransactionDetailsEntity transactionDetailsEntity = paymentService.getTransaction(id);
        TransactionDTO transactionDTO = modelMapper.map(transactionDetailsEntity, TransactionDTO.class);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }
}
