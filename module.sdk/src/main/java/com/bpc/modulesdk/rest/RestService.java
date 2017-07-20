package com.bpc.modulesdk.rest;

import android.support.annotation.Nullable;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerAccountEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationProvidedDataEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.PaymentInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.RepaymentInfoEntry;
import com.bpc.modulesdk.rest.dto.request.AgentAcctToAcctTransferCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.AgentAcctToAcctTransferSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerAccountToCashTransferSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerBalanceCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerBalanceSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCardPinChangeSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashDepositSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashToAccountTransferSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashToCashTransferSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashWithdrawalSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerNewAccountSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerNewCardCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerNewCardSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerPaymentByCashSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerPaymentFromAccountSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerRepaymentCashToLoanSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerRepaymentFromAccountSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.DeviceDetachRequest;
import com.bpc.modulesdk.rest.dto.request.NewCustomerSupplyRequest;
import com.bpc.modulesdk.rest.dto.response.AccountDetailResponse;
import com.bpc.modulesdk.rest.dto.response.AccountHistoryResponse;
import com.bpc.modulesdk.rest.dto.response.AccountsResponse;
import com.bpc.modulesdk.rest.dto.response.AgentAcctToAcctTransferResponse;
import com.bpc.modulesdk.rest.dto.response.AgentAcctToAcctTransferSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.AgentStatisticsResponse;
import com.bpc.modulesdk.rest.dto.response.CapabilitiesResponse;
import com.bpc.modulesdk.rest.dto.response.CardDetailResponse;
import com.bpc.modulesdk.rest.dto.response.CardHistoryResponse;
import com.bpc.modulesdk.rest.dto.response.CardsResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerAccountToCashTransferResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerAccountToCashTransferSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerAcctToAcctSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerAcctToAcctTransferResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerBalanceCompleteResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerBalanceResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerBalanceSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCardPinChangeResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCardPinChangeSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCashDepositResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCashDepositSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCashReceiveResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCashToAccountTransferResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCashToAccountTransferSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCashToCashTransferResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCashToCashTransferSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCashWithdrawalResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerCashWithdrawalSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerMinistatementResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerMinistatementSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerNewAccountResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerNewAccountSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerNewCardResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerNewCardSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerOperationsHistoryResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerPaymentByCashResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerPaymentByCashSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerPaymentFromAccountResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerPaymentFromAccountSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerRepaymentCashToLoanResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerRepaymentCashToLoanSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerRepaymentFromAccountResponse;
import com.bpc.modulesdk.rest.dto.response.CustomerRepaymentFromAccountSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.DevicesResponse;
import com.bpc.modulesdk.rest.dto.response.ImageUploadResponse;
import com.bpc.modulesdk.rest.dto.response.MainResponse;
import com.bpc.modulesdk.rest.dto.response.NewCustomerResponse;
import com.bpc.modulesdk.rest.dto.response.NewCustomerSupplyResponse;
import com.bpc.modulesdk.rest.dto.response.OperationCompleteResponse;
import com.bpc.modulesdk.rest.dto.response.PaymentCategoriesResponse;
import com.bpc.modulesdk.rest.dto.response.PaymentProvidersResponse;
import com.bpc.modulesdk.rest.dto.response.ProfileResponse;
import com.bpc.modulesdk.rest.dto.response.RatesResponse;

import java.io.File;
import java.util.Date;
import java.util.List;

import rx.Observable;


public interface RestService {

    Observable<MainResponse> login(String login, String password, String deviceId);

    Observable<MainResponse> login(String login, String password, String deviceId, LocationEntry locationEntry);

    Observable<MainResponse> logout();

    Observable<MainResponse> bindDevice(String deviceName, String deviceId, String deviceKey);

    Observable<MainResponse> changePassword(String pwd);

    Observable<MainResponse> changePassword(String oldpwd, String newpwd);

    @Nullable
    Observable<CustomerBalanceResponse> execCustomerBalance(CustomerCardEntry cardEntry, LocationEntry locationEntry);

    Observable<CustomerBalanceSupplyResponse> supplyCustomerBalance(String transRef, CustomerBalanceSupplyRequest.DataResponse dataResponse);

    Observable<CustomerBalanceCompleteResponse> completeCustomerBalance(String transRef, CustomerBalanceCompleteRequest.DataResponse dataResponse);

    Observable<CustomerAcctToAcctTransferResponse> execCustomerAccToAccTransfer(CustomerCardEntry sourceCard, String targetCard,
                                                                                MoneyEntry operationAmount, String mode, LocationEntry location);

    //Observable<CustomerAcctToAcctSupplyResponse> supplyCustomerAccToAccTransfer(CustomerAccountEntry sourceAccount, CustomerAccountEntry targetAccount, MoneyEntry operationAmount);
    Observable<CustomerAcctToAcctSupplyResponse> supplyCustomerAccToAccTransfer(CustomerAccountEntry sourceAccount, @Nullable CustomerAccountEntry targetAccount, @Nullable String targetCard,
                                                                                MoneyEntry operationAmount, String transRef);

    Observable<OperationCompleteResponse> completeCustomerAccToAccTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse);


    Observable<CustomerCashReceiveResponse> initCustomerCashReceive(String remittanceCode, String receiverPhoneNumber, MoneyEntry operationAmount, LocationEntry location);

    Observable<CustomerCashReceiveResponse> supplyCustomerCashReceive(String agentAccount, @Nullable MoneyEntry operationAmount, String transRef);

    Observable<OperationCompleteResponse> completeCustomerCashReceive(@Nullable String agentPass, String transRef);


    Observable<CustomerCashWithdrawalResponse> getCustomerCashWithdrawal(CustomerCardEntry cardEntry, LocationEntry locationEntry);

    Observable<CustomerCashWithdrawalSupplyResponse> supplyCustomerCashWithdrawal(String transRef, CustomerCashWithdrawalSupplyRequest.ProvidedData data);

    Observable<OperationCompleteResponse> completeCustomerCashWithdrawal(String transRef, OperationConfirmationProvidedDataEntry data);

    Observable<CustomerCashDepositResponse> getCustomerCashDeposit(CustomerCardEntry cardEntry, LocationEntry locationEntry);

    Observable<CustomerCashDepositSupplyResponse> supplyCustomerCashDeposit(String transRef, CustomerCashDepositSupplyRequest.ProvidedData data);

    Observable<OperationCompleteResponse> completeCustomerCashDeposit(String transRef, OperationConfirmationProvidedDataEntry data);

    Observable<AgentAcctToAcctTransferResponse> getAgentAccountToAccountTransfer(String sourceId, String targetId, MoneyEntry amount, LocationEntry locationEntry);

    Observable<AgentAcctToAcctTransferSupplyResponse> supplyAgentAccountToAccountTransfer(String transRef, AgentAcctToAcctTransferSupplyRequest.DataResponse data);

    Observable<OperationCompleteResponse> completeAgentAccountToAccountTransfer(String transRef, AgentAcctToAcctTransferCompleteRequest.DataResponse data);

    Observable<AgentStatisticsResponse> getAgentStatistics(Date dateFrom, Date dateTill, boolean details);

    Observable<CustomerAccountToCashTransferResponse> getCustomerAccountToCashTransfer(CustomerCardEntry sourceCard, MoneyEntry operationAmount, LocationEntry location);

    Observable<CustomerAccountToCashTransferSupplyResponse> supplyCustomerAccountToCashTransfer(String transRef, CustomerAccountToCashTransferSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> completeCustomerAccountToCashTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse);

    Observable<CustomerCashToAccountTransferResponse> getCustomerCashToAccountTransfer(String targetCard, MoneyEntry operationAmount, LocationEntry location);

    Observable<CustomerCashToAccountTransferSupplyResponse> supplyCustomerCashToAccountTransfer(String transRef, CustomerCashToAccountTransferSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> completeCustomerCashToAccountTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse);

    Observable<CustomerCashToCashTransferResponse> getCustomerCashToCashTransfer(String senderPhoneNumber, String receiverPhoneNumber, MoneyEntry operationAmount, LocationEntry location);

    Observable<CustomerCashToCashTransferSupplyResponse> supplyCustomerCashToCashTransfer(String transRef, CustomerCashToCashTransferSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> completeCustomerCashToCashTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse);

    Observable<CustomerNewAccountResponse> getCustomerNewAccount(CustomerCardEntry cardEntry, LocationEntry location);

    Observable<CustomerNewAccountSupplyResponse> getCustomerNewAccount(String transRef, CustomerNewAccountSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> getCustomerNewAccount(String transRef, OperationConfirmationProvidedDataEntry dataResponse);

    Observable<CustomerNewCardResponse> getCustomerNewCard(CustomerCardEntry cardEntry, LocationEntry location);

    Observable<CustomerNewCardSupplyResponse> supplyCustomerNewCard(String transRef, CustomerNewCardSupplyRequest.DataResponse dataResponse);

    Observable<CustomerNewCardSupplyResponse> supplyCustomerNewCard2(String transRef, CustomerNewCardSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> completeCustomerNewCard(String transRef, CustomerNewCardCompleteRequest.DataResponse dataResponse);

    Observable<NewCustomerResponse> createNewCustomer(CustomerCardEntry newCard, LocationEntry location);

    Observable<NewCustomerSupplyResponse> createNewCustomer(String transRef, NewCustomerSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> createNewCustomer(String transRef, OperationConfirmationProvidedDataEntry data);

    Observable<CustomerPaymentByCashResponse> getCustomerPaymentByCash(PaymentInfoEntry paymentInfo, LocationEntry location);

    Observable<CustomerPaymentByCashSupplyResponse> supplyCustomerPaymentByCash(String transRef, CustomerPaymentByCashSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> completeCustomerPaymentByCash(String transRef, OperationConfirmationProvidedDataEntry dataResponse);

    Observable<CustomerPaymentFromAccountResponse> getCustomerPaymentFromAccount(CustomerCardEntry card, LocationEntry location);

    Observable<CustomerPaymentFromAccountSupplyResponse> supplyCustomerPaymentFromAccount(String transRef, CustomerPaymentFromAccountSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> completeCustomerPaymentFromAccount(String transRef, OperationConfirmationProvidedDataEntry dataResponse);

    Observable<CustomerCardPinChangeResponse> initCustomerCardPinChange(CustomerCardEntry card, LocationEntry location);

    Observable<CustomerCardPinChangeSupplyResponse> supplyCustomerCardPinChange(String transRef, CustomerCardPinChangeSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> completeCustomerCardPinChange(String transRef);

    Observable<CustomerRepaymentCashToLoanResponse> initCustomerRepaymentCashToLoan(LocationEntry location, RepaymentInfoEntry repaymentInfo);

    Observable<CustomerRepaymentCashToLoanSupplyResponse> supplyCustomerRepaymentCashToLoan(String transRef, CustomerRepaymentCashToLoanSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> completeCustomerRepaymentCashToLoan(String transRef, OperationConfirmationProvidedDataEntry dataResponse);
    //
    Observable<CustomerRepaymentFromAccountResponse> initCustomerLoanPaymentFromAccount(CustomerCardEntry sourceCard, LocationEntry location);

    Observable<CustomerRepaymentFromAccountSupplyResponse> supplyCustomerLoanPaymentFromAccount(String transRef, CustomerRepaymentFromAccountSupplyRequest.DataResponse dataResponse);

    Observable<OperationCompleteResponse> completeCustomerLoanPaymentFromAccount(String transRef, OperationConfirmationProvidedDataEntry dataResponse);

    Observable<RatesResponse> getRates();

    Observable<CapabilitiesResponse> getServerCapabilities();

    Observable<ProfileResponse> getAgentProfile();

    Observable<MainResponse> postDeviceInfo();

    Observable<CardsResponse> getCards();

    Observable<CardDetailResponse> getCard(String id);

    Observable<AccountDetailResponse> getAccount(String id);

    Observable<CardHistoryResponse> getCardHistory(String id, int page, String startDate, String endDate);

    Observable<AccountHistoryResponse> getAccountHistory(String number, int page, Date from, Date to);

    Observable<CustomerMinistatementResponse> initCustomerMinistatement(CustomerCardEntry card, LocationEntry locationEntry);

    Observable<CustomerMinistatementSupplyResponse> supplyCustomerMinistatement(String transRef, String sourceAccount);

    Observable<OperationCompleteResponse> completeCustomerMinistatement(String transRef, OperationConfirmationProvidedDataEntry dataResponse);

    Observable<MainResponse> cardRename(String id, String newName);

    Observable<MainResponse> getAccountRenameRequest(String id, String newName);

    void removeAllDataFromCache();

    void removeDataFromCache(Class clazz);

    void removeDataFromCache(Class clazz, String cacheTag);

    Observable<AccountsResponse> getAccounts();

    Observable<DevicesResponse> getDevices();

    Observable<MainResponse> deviceDetach(DeviceDetachRequest request);

    Observable<ImageUploadResponse> uploadImage(File imageFile);

    Observable<PaymentCategoriesResponse> getPaymentCategories(List<String> cids, String nameFilter, LocationEntry location);

    Observable<PaymentProvidersResponse> getPaymentProviders(List<String> pids, List<String> cids, String nameFilter, LocationEntry location);

    Observable<CustomerOperationsHistoryResponse> getCustomerOperationsHistory(CustomerCardEntry card, List<OperationInfoEntry.OperationType> operationTypes);
}
