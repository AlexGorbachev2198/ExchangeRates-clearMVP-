package com.bpc.modulesdk.rest.dto.pojo;

/**
 * Created on 18.01.2017.
 */

public class SupportedFunctionsRecord {

    private int balanceInquiry = 1;//+
    private int agentCommissions = 1;//+
    private int ownAgentAccToAcc = 1;//+

    private int transferAcctToCash = 1;//+
    private int cashToAccountTransfer = 1;//+
    private int transferCashToCash = 1;//+
    private int accountToAccountTransfer = 1; //+

    private int paymentByCash = 1; //обрабатывается только пункт меню, не сам итем перевода
    private int paymentByAccount = 1;//обрабатывается только пункт меню, не сам итем перевода

    private int receiveCash = 1;//+
    private int cashDeposit = 1;//+
    private int cashWithdrawal = 1;//+

    private int newCustomer = 1; //+
    private int newAccForNewCard = 1;//+
    private int customerOpenAccount = 1;//+

    private int miniStatementForCustomer = 1;//+
    private int customerPinChange = 1; //+

    // to be defined in API spec
    private int repaymentCashToLoan = 1;
    private int repaymentLoanFromAccount = 1;


    public int getBalanceInquiry() {
        return balanceInquiry;
    }

    public void setBalanceInquiry(int balanceInquiry) {
        this.balanceInquiry = balanceInquiry;
    }


    public int getCashDeposit() {
        return cashDeposit;
    }

    public void setCashDeposit(int cashDeposit) {
        this.cashDeposit = cashDeposit;
    }


    public int getCashWithdrawal() {
        return cashWithdrawal;
    }

    public void setCashWithdrawal(int cashWithdrawal) {
        this.cashWithdrawal = cashWithdrawal;
    }

    public int getPaymentByAccount() {
        return paymentByAccount;
    }

    public void setPaymentByAccount(int paymentByAccount) {
        this.paymentByAccount = paymentByAccount;
    }

    public int getAgentCommissions() {
        return agentCommissions;
    }

    public void setAgentCommissions(int agentCommissions) {
        this.agentCommissions = agentCommissions;
    }

    public int getOwnAgentAccToAcc() {
        return ownAgentAccToAcc;
    }

    public void setOwnAgentAccToAcc(int ownAgentAccToAcc) {
        this.ownAgentAccToAcc = ownAgentAccToAcc;
    }

    public int getTransferAcctToCash() {
        return transferAcctToCash;
    }

    public void setTransferAcctToCash(int transferAcctToCash) {
        this.transferAcctToCash = transferAcctToCash;
    }

    public int getPaymentByCash() {
        return paymentByCash;
    }

    public void setPaymentByCash(int paymentByCash) {
        this.paymentByCash = paymentByCash;
    }

    public int getReceiveCash() {
        return receiveCash;
    }

    public void setReceiveCash(int receiveCash) {
        this.receiveCash = receiveCash;
    }

    public int getCashToAccountTransfer() {
        return cashToAccountTransfer;
    }

    public void setCashToAccountTransfer(int cashToAccountTransfer) {
        this.cashToAccountTransfer = cashToAccountTransfer;
    }

    public int getTransferCashToCash() {
        return transferCashToCash;
    }

    public void setTransferCashToCash(int transferCashToCash) {
        this.transferCashToCash = transferCashToCash;
    }

    public int getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(int newCustomer) {
        this.newCustomer = newCustomer;
    }

    public int getAccountToAccountTransfer() {
        return accountToAccountTransfer;
    }

    public void setAccountToAccountTransfer(int accountToAccountTransfer) {
        this.accountToAccountTransfer = accountToAccountTransfer;
    }

    public int getNewAccForNewCard() {
        return newAccForNewCard;
    }

    public void setNewAccForNewCard(int newAccForNewCard) {
        this.newAccForNewCard = newAccForNewCard;
    }

    public int getCustomerOpenAccount() {
        return customerOpenAccount;
    }

    public void setCustomerOpenAccount(int customerOpenAccount) {
        this.customerOpenAccount = customerOpenAccount;
    }

    public int getMiniStatementForCustomer() {
        return miniStatementForCustomer;
    }

    public void setMiniStatementForCustomer(int miniStatementForCustomer) {
        this.miniStatementForCustomer = miniStatementForCustomer;
    }

    public int getCustomerPinChange() {
        return customerPinChange;
    }

    public void setCustomerPinChange(int customerPinChange) {
        this.customerPinChange = customerPinChange;
    }

    public int getRepaymentCashToLoan() {
        return repaymentCashToLoan;
    }

    public void setRepaymentCashToLoan(int repaymentCashToLoan) {
        this.repaymentCashToLoan = repaymentCashToLoan;
    }

    public int getRepaymentLoanFromAccount() {
        return repaymentLoanFromAccount;
    }

    public void setRepaymentLoanFromAccount(int repaymentLoanFromAccount) {
        this.repaymentLoanFromAccount = repaymentLoanFromAccount;
    }
}
