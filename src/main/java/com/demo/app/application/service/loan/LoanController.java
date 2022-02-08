package com.demo.app.application.service.loan;

import com.demo.app.application.service.loan.dto.LoanDto;
import com.demo.app.application.service.loan.dto.LoanDtoMapper;
import com.demo.app.application.service.loan.dto.LoanRequestDto;
import com.demo.app.domain.customer.CustomerService;
import com.demo.app.domain.loan.Loan;
import com.demo.app.domain.loan.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {
    private final LoanService loanService;
    private final CustomerService customerService;
    private final LoanDtoMapper loanDtoMapper;

    @GetMapping(value = "/api/loans")
    public List<LoanDto> getLoans() {
        return loanService.getAllLoans().stream()
                .map(loanDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/customers/{customerId}/loans")
    public List<LoanDto> getCustomerLoans(@PathVariable Long customerId) {
        return loanService.getAllCustomerLoans(customerId).stream()
                .map(loanDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/api/customers/{customerId}/loans")
    public LoanDto createLoan(@PathVariable Long customerId,
                              @Valid @RequestBody LoanRequestDto requestDto) {
        Loan loan = loanDtoMapper.toLoan(requestDto, customerId);
        customerService.checkCustomerExists(customerId);
        loan.setCustomerId(customerId);
        Loan createdLoan = loanService.createLoan(loan);
        return loanDtoMapper.toDto(createdLoan);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/api/customers/{customerId}/loans/{loanId}")
    public void deleteCustomerLoan(@PathVariable Long customerId, @PathVariable Long loanId) {
        loanService.deleteCustomerLoan(customerId, loanId);
    }
}
