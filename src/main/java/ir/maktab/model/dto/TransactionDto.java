package ir.maktab.model.dto;

import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Order;
import ir.maktab.model.enumaration.TransactionType;

import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class TransactionDto {
    private TransactionType transactionType;
    private Date transactionDate;
    private Client client;
    private Expert expert;
    private Order order;
}
