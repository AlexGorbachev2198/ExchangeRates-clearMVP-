package com.bpc.modulesdk.rest.dto.pojo.entries;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dzmitrystrupinski on 3/20/17.
 */

public class OperationDetailsEntry implements Serializable {

    private String timestamp;
    private CustomerAccountEntry cardAccount;
    private CustomerAccountEntry agentAccount;
    private MoneyEntry operationAmount;
    private List<FeeEntry> feeInfo;
    private CurrencyConversionInfoEntry conversionInfo;
    private String conversionInfoString;
    private MoneyEntry totalAmount;
    private String cardNumber;
    private String accountNumber;
    private String agentAccountNumber;
    private String cardAccountNumber;
    private String targetCardNumber;
    private String sourceAccountNumber;
    private String sourceAccountCurrency;
    private String targetAccountNumber;
    private String accountType;
    private String accountCurrency;
    private MoneyEntry agentFee;
    private MoneyEntry customerFee;
    private MoneyEntry customerOriginalBalance;
    private MoneyEntry customerFinalBalance;
    private MoneyEntry agentOriginalBalance;
    private MoneyEntry agentFinalBalance;
    private String subagent;
    private String agentName;
    private String agentPhone;
    private String authId;
    private String additionalInfo;
    private CustomerAccountEntry sourceAccount;
    private CustomerAccountEntry targetAccount;
    private PaymentInfoEntry paymentInfo;
    private RepaymentInfoEntry repaymentInfo;

    private MoneyEntry customerTotalBalance;
    private MoneyEntry customerAvailableBalance;

    private String sourceCardNumber;
    private String sourceCardholderName;

    private String senderPhoneNumber;
    private String customerPhoneNumber;
    private String receiverPhoneNumber;
    private String remittanceCode;
    private String authid;
    private CustomerInfoEntry customerInfo;
    private IdentityDocumentInfoEntry idocInfo;
    private List<MinistatementRecord> ministatement;

    public OperationDetailsEntry(CustomerAccountEntry sourceAccount, CustomerAccountEntry targetAccount, MoneyEntry operationAmount) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.operationAmount = operationAmount;
    }

    public OperationDetailsEntry() {
    }

    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardAccountNumber() {
        return cardAccountNumber;
    }

    public void setCardAccountNumber(String cardAccountNumber) {
        this.cardAccountNumber = cardAccountNumber;
    }

    public String getTargetCardNumber() {
        return targetCardNumber;
    }

    public void setTargetCardNumber(String targetCardNumber) {
        this.targetCardNumber = targetCardNumber;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getSourceAccountCurrency() {
        return sourceAccountCurrency;
    }

    public void setSourceAccountCurrency(String sourceAccountCurrency) {
        this.sourceAccountCurrency = sourceAccountCurrency;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public void setTargetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAgentAccountNumber() {
        return agentAccountNumber;
    }

    public void setAgentAccountNumber(String agentAccountNumber) {
        this.agentAccountNumber = agentAccountNumber;
    }

    public MoneyEntry getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(MoneyEntry agentFee) {
        this.agentFee = agentFee;
    }

    public MoneyEntry getCustomerFee() {
        return customerFee;
    }

    public void setCustomerFee(MoneyEntry customerFee) {
        this.customerFee = customerFee;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public String getSubagent() {
        return subagent;
    }

    public void setSubagent(String subagent) {
        this.subagent = subagent;
    }

    public MoneyEntry getAgentOriginalBalance() {
        return agentOriginalBalance;
    }

    public void setAgentOriginalBalance(MoneyEntry agentOriginalBalance) {
        this.agentOriginalBalance = agentOriginalBalance;
    }

    public MoneyEntry getAgentFinalBalance() {
        return agentFinalBalance;
    }

    public void setAgentFinalBalance(MoneyEntry agentFinalBalance) {
        this.agentFinalBalance = agentFinalBalance;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setCardAccount(CustomerAccountEntry cardAccount) {
        this.cardAccount = cardAccount;
    }

    public void setAgentAccount(CustomerAccountEntry agentAccount) {
        this.agentAccount = agentAccount;
    }

    public void setOperationAmount(MoneyEntry operationAmount) {
        this.operationAmount = operationAmount;
    }

    public void setFeeInfo(List<FeeEntry> feeInfo) {
        this.feeInfo = feeInfo;
    }

    public void setConversionInfo(CurrencyConversionInfoEntry conversionInfo) {
        this.conversionInfo = conversionInfo;
    }

    @JsonSetter("conversionInfo")
    public void setConversionInfoInternal(JsonNode conversionInfoInternal) {
        if (conversionInfoInternal != null) {
            if (conversionInfoInternal.isTextual()) {
                conversionInfoString = conversionInfoInternal.asText();
            } else if (conversionInfoInternal.isObject()) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    conversionInfo = mapper.treeToValue(conversionInfoInternal, CurrencyConversionInfoEntry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setTotalAmount(MoneyEntry totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CustomerAccountEntry getCardAccount() {
        return cardAccount;
    }

    public CustomerAccountEntry getAgentAccount() {
        return agentAccount;
    }

    public MoneyEntry getOperationAmount() {
        return operationAmount;
    }

    public List<FeeEntry> getFeeInfo() {
        return feeInfo;
    }

    public CurrencyConversionInfoEntry getConversionInfo() {
        return conversionInfo;
    }

    public String getConversionInfoString() {
        return conversionInfoString;
    }

    public void setConversionInfoString(String conversionInfo) {
        this.conversionInfoString = conversionInfo;
    }

    public MoneyEntry getTotalAmount() {
        return totalAmount;
    }

    public String getSourceCardNumber() {
        return sourceCardNumber;
    }

    public void setSourceCardNumber(String sourceCardNumber) {
        this.sourceCardNumber = sourceCardNumber;
    }

    public String getSourceCardholderName() {
        return sourceCardholderName;
    }

    public void setSourceCardholderName(String sourceCardholderName) {
        this.sourceCardholderName = sourceCardholderName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getRemittanceCode() {
        return remittanceCode;
    }

    public void setRemittanceCode(String remittanceCode) {
        this.remittanceCode = remittanceCode;
    }

    public CustomerAccountEntry getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(CustomerAccountEntry sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public CustomerAccountEntry getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(CustomerAccountEntry targetAccount) {
        this.targetAccount = targetAccount;
    }

    public CustomerInfoEntry getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfoEntry customerInfo) {
        this.customerInfo = customerInfo;
    }

    public IdentityDocumentInfoEntry getIdocInfo() {
        return idocInfo;
    }

    public void setIdocInfo(IdentityDocumentInfoEntry idocInfo) {
        this.idocInfo = idocInfo;
    }

    public List<MinistatementRecord> getMinistatement() {
        return ministatement;
    }

    public void setMinistatement(List<MinistatementRecord> ministatement) {
        this.ministatement = ministatement;
    }

    public PaymentInfoEntry getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoEntry paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public RepaymentInfoEntry getRepaymentInfo() {
        return repaymentInfo;
    }

    public void setRepaymentInfo(RepaymentInfoEntry repaymentInfo) {
        this.repaymentInfo = repaymentInfo;
    }

    public MoneyEntry getCustomerOriginalBalance() {
        return customerOriginalBalance;
    }

    public void setCustomerOriginalBalance(MoneyEntry customerOriginalBalance) {
        this.customerOriginalBalance = customerOriginalBalance;
    }

    public MoneyEntry getCustomerFinalBalance() {
        return customerFinalBalance;
    }

    public void setCustomerFinalBalance(MoneyEntry customerFinalBalance) {
        this.customerFinalBalance = customerFinalBalance;
    }

    public MoneyEntry getCustomerTotalBalance() {
        return customerTotalBalance;
    }

    public void setCustomerTotalBalance(MoneyEntry customerTotalBalance) {
        this.customerTotalBalance = customerTotalBalance;
    }

    public MoneyEntry getCustomerAvailableBalance() {
        return customerAvailableBalance;
    }

    public void setCustomerAvailableBalance(MoneyEntry customerAvailableBalance) {
        this.customerAvailableBalance = customerAvailableBalance;
    }
}
