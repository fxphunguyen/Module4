package com.example.banking_manager.controller;

import com.example.banking_manager.model.dto.ITransferDTO;
import com.example.banking_manager.model.dto.SumFeesAmountDTO;
import com.example.banking_manager.service.transfer.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private ITransferService transferService;

    @GetMapping
    public ModelAndView listCustomers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/transfer/list");

        Iterable<ITransferDTO> iTransferDTOS = transferService.findAllByITransferDTO();
        Optional<SumFeesAmountDTO> sumFeesAmount = transferService.sumFeesAmount();

        modelAndView.addObject("iTransferDTOS", iTransferDTOS);
        modelAndView.addObject("sumAmount", sumFeesAmount.get());

        return modelAndView;
    }
}
