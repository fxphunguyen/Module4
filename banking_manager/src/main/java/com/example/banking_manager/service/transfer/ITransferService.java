package com.example.banking_manager.service.transfer;

import com.example.banking_manager.model.Transfer;
import com.example.banking_manager.model.dto.ITransferDTO;
import com.example.banking_manager.model.dto.SumFeesAmountDTO;
import com.example.banking_manager.model.dto.TransferDTO;
import com.example.banking_manager.service.IGeneralService;

import java.util.Optional;

public interface ITransferService extends IGeneralService<Transfer> {
    Iterable<ITransferDTO> findAllByITransferDTO();

    Optional<TransferDTO> findByIdWithTransferDTO(Long id);

    Optional<SumFeesAmountDTO> sumFeesAmount();
}
