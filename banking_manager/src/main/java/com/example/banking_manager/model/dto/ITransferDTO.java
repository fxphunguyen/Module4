package com.example.banking_manager.model.dto;

import java.math.BigDecimal;

public interface ITransferDTO {
    int getId();
    int getSenderId();
    String getSenderName();
    int getRecipientId();
    String getRecipientName();
    BigDecimal getTransferAmount();
    int getFees();
    BigDecimal getFeesAmount();
}
