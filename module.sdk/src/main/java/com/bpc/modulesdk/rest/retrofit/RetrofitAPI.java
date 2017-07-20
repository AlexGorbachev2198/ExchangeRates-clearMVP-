package com.bpc.modulesdk.rest.retrofit;


import com.bpc.modulesdk.rest.dto.request.AccountChangeRequest;
import com.bpc.modulesdk.rest.dto.request.AccountDetailRequest;
import com.bpc.modulesdk.rest.dto.request.AccountHistoryRequest;
import com.bpc.modulesdk.rest.dto.request.AccountRequest;
import com.bpc.modulesdk.rest.dto.request.AgentAcctToAcctTransferCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.AgentAcctToAcctTransferRequest;
import com.bpc.modulesdk.rest.dto.request.AgentAcctToAcctTransferSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.AgentStatisticsRequest;
import com.bpc.modulesdk.rest.dto.request.CardChangeRequest;
import com.bpc.modulesdk.rest.dto.request.CardDetailRequest;
import com.bpc.modulesdk.rest.dto.request.CardHistoryRequest;
import com.bpc.modulesdk.rest.dto.request.CardRequest;
import com.bpc.modulesdk.rest.dto.request.ChangeAgentPasswordRequest;
import com.bpc.modulesdk.rest.dto.request.ChangePasswordRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerAccountToCashTransferCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerAccountToCashTransferRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerAccountToCashTransferSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerAcctToAcctCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerAcctToAcctSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerAcctToAcctTransferRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerBalanceCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerBalanceRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerBalanceSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCardPinChangeCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCardPinChangeRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCardPinChangeSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashDepositCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashDepositRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashDepositSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashReceiveCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashReceiveRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashReceiveSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashToAccountTransferCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashToAccountTransferRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashToAccountTransferSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashToCashTransferCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashToCashTransferRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashToCashTransferSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashWithdrawalCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashWithdrawalRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCashWithdrawalSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerMinistatementRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerMinistatementSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerNewAccountCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerNewAccountRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerNewAccountSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerNewCardCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerNewCardRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerNewCardSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerOperationsHistoryRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerPaymentByCashCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerPaymentByCashRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerPaymentByCashSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerPaymentFromAccountCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerPaymentFromAccountRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerPaymentFromAccountSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerRepaymentCashToLoanCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerRepaymentCashToLoanRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerRepaymentCashToLoanSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerRepaymentFromAccountCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerRepaymentFromAccountRequest;
import com.bpc.modulesdk.rest.dto.request.CustomerRepaymentFromAccountSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.DeviceBindingRequest;
import com.bpc.modulesdk.rest.dto.request.DeviceDetachRequest;
import com.bpc.modulesdk.rest.dto.request.DeviceInfoRequest;
import com.bpc.modulesdk.rest.dto.request.LoginRequest;
import com.bpc.modulesdk.rest.dto.request.NewCustomerCompleteRequest;
import com.bpc.modulesdk.rest.dto.request.NewCustomerRequest;
import com.bpc.modulesdk.rest.dto.request.NewCustomerSupplyRequest;
import com.bpc.modulesdk.rest.dto.request.PaymentCategoriesRequest;
import com.bpc.modulesdk.rest.dto.request.PaymentProvidersRequest;
import com.bpc.modulesdk.rest.dto.request.StampedRequest;
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

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Masloed on 24.11.2016.
 */

public interface RetrofitAPI {

    @POST("login")
    Observable<MainResponse> login(@Body LoginRequest request);

    @POST("logout")
    Observable<MainResponse> logout();

    @POST("bindDevice")
    Observable<MainResponse> deviceBind(@Body DeviceBindingRequest request);

    @POST("passwordChange")
    Observable<MainResponse> changePassword(@Body ChangePasswordRequest request);

    @POST("agent/password/change")
    Observable<MainResponse> changePassword(@Body ChangeAgentPasswordRequest request);

    //================================================================================
    @POST("customer/balance")
    Observable<CustomerBalanceResponse> getCustomerBalance(@Body CustomerBalanceRequest request);

    @POST("customer/balance/supply")
    Observable<CustomerBalanceSupplyResponse> supplyCustomerBalance(@Body CustomerBalanceSupplyRequest request);

    @POST("customer/balance/complete")
    Observable<CustomerBalanceCompleteResponse> completeCustomerBalance(@Body CustomerBalanceCompleteRequest request);

    //Account to account transfer

    @POST("customer/transfer/acct-to-acct")
    Observable<CustomerAcctToAcctTransferResponse> getCustomerAcctToAcctTransfer(@Body CustomerAcctToAcctTransferRequest request);

    @POST("customer/transfer/acct-to-acct/supply")
    Observable<CustomerAcctToAcctSupplyResponse> getCustomerAcctToAcctSupply(@Body CustomerAcctToAcctSupplyRequest request);

    @POST("customer/transfer/acct-to-acct/complete")
    Observable<OperationCompleteResponse> getCustomerAcctToAcctComplete(@Body CustomerAcctToAcctCompleteRequest request);

    //Cash receive

    @POST("customer/transfer/cash-receive")
    Observable<CustomerCashReceiveResponse> initCustomerCashReceive(@Body CustomerCashReceiveRequest request);

    @POST("customer/transfer/cash-receive/supply")
    Observable<CustomerCashReceiveResponse> supplyCustomerCashReceive(@Body CustomerCashReceiveSupplyRequest request);

    @POST("customer/transfer/cash-receive/complete")
    Observable<OperationCompleteResponse> completeCustomerCashReceive(@Body CustomerCashReceiveCompleteRequest request);

    //Cash withdrawal

    @POST("customer/cashwithdrawal")
    Observable<CustomerCashWithdrawalResponse> getCustomerCashWithdrawal(@Body CustomerCashWithdrawalRequest request);

    @POST("customer/cashwithdrawal/supply")
    Observable<CustomerCashWithdrawalSupplyResponse> supplyCustomerCashWithdrawal(@Body CustomerCashWithdrawalSupplyRequest request);

    @POST("customer/cashwithdrawal/complete")
    Observable<OperationCompleteResponse> completeCustomerCashWithdrawal(@Body CustomerCashWithdrawalCompleteRequest request);

    // Cash deposit

    @POST("customer/cashdeposit")
    Observable<CustomerCashDepositResponse> getCustomerCashDeposit(@Body CustomerCashDepositRequest request);

    @POST("customer/cashdeposit/supply")
    Observable<CustomerCashDepositSupplyResponse> supplyCustomerCashDeposit(@Body CustomerCashDepositSupplyRequest request);

    @POST("customer/cashdeposit/complete")
    Observable<OperationCompleteResponse> completeCustomerCashDeposit(@Body CustomerCashDepositCompleteRequest request);

    // Agent acc to acc

    @POST("agent/transfer/acct-to-acct")
    Observable<AgentAcctToAcctTransferResponse> getAgentAccountToAccountTransfer(@Body AgentAcctToAcctTransferRequest request);

    @POST("agent/transfer/acct-to-acct/supply")
    Observable<AgentAcctToAcctTransferSupplyResponse> supplyAgentAccountToAccountTransfer(@Body AgentAcctToAcctTransferSupplyRequest request);

    @POST("agent/transfer/acct-to-acct/complete")
    Observable<OperationCompleteResponse> completeAgentAccountToAccountTransfer(@Body AgentAcctToAcctTransferCompleteRequest request);

    // Agent statistics

    @POST("agent/statistics")
    Observable<AgentStatisticsResponse> getAgentStatistics(@Body AgentStatisticsRequest request);

    // Customer acc to cash

    @POST("customer/transfer/acct-to-cash")
    Observable<CustomerAccountToCashTransferResponse> getCustomerAccountToCashTransfer(@Body CustomerAccountToCashTransferRequest request);

    @POST("customer/transfer/acct-to-cash/supply")
    Observable<CustomerAccountToCashTransferSupplyResponse> supplyCustomerAccountToCashTransfer(@Body CustomerAccountToCashTransferSupplyRequest request);

    @POST("customer/transfer/acct-to-cash/complete")
    Observable<OperationCompleteResponse> completeCustomerAccountToCashTransfer(@Body CustomerAccountToCashTransferCompleteRequest request);

    // Customer cash to acc

    @POST("customer/transfer/cash-to-acct")
    Observable<CustomerCashToAccountTransferResponse> getCustomerCashToAccountTransfer(@Body CustomerCashToAccountTransferRequest request);

    @POST("customer/transfer/cash-to-acct/supply")
    Observable<CustomerCashToAccountTransferSupplyResponse> supplyCustomerCashToAccountTransfer(@Body CustomerCashToAccountTransferSupplyRequest request);

    @POST("customer/transfer/cash-to-acct/complete")
    Observable<OperationCompleteResponse> completeCustomerCashToAccountTransfer(@Body CustomerCashToAccountTransferCompleteRequest request);

    // customer cash to cash

    @POST("customer/transfer/cash-to-cash")
    Observable<CustomerCashToCashTransferResponse> getCustomerCashToCashTransfer(@Body CustomerCashToCashTransferRequest request);

    @POST("customer/transfer/cash-to-cash/supply")
    Observable<CustomerCashToCashTransferSupplyResponse> supplyCustomerCashToCashTransfer(@Body CustomerCashToCashTransferSupplyRequest request);

    @POST("customer/transfer/cash-to-cash/complete")
    Observable<OperationCompleteResponse> completeCustomerCashToCashTransfer(@Body CustomerCashToCashTransferCompleteRequest request);

    // customer new account with existing card

    @POST("customer/new-acct")
    Observable<CustomerNewAccountResponse> getCustomerNewAccount(@Body CustomerNewAccountRequest request);

    @POST("customer/new-acct/supply")
    Observable<CustomerNewAccountSupplyResponse> supplyCustomerNewAccount(@Body CustomerNewAccountSupplyRequest request);

    @POST("customer/new-acct/complete")
    Observable<OperationCompleteResponse> completeCustomerNewAccount(@Body CustomerNewAccountCompleteRequest request);

    // customer new card

    @POST("customer/new-card")
    Observable<CustomerNewCardResponse> getCustomerNewCard(@Body CustomerNewCardRequest request);

    @POST("customer/new-card/supply")
    Observable<CustomerNewCardSupplyResponse> supplyCustomerNewCard(@Body CustomerNewCardSupplyRequest request);

    @POST("customer/new-card/complete")
    Observable<OperationCompleteResponse> completeCustomerNewCard(@Body CustomerNewCardCompleteRequest request);

    // new customer

    @POST("customer/new-customer")
    Observable<NewCustomerResponse> createNewCustomer(@Body NewCustomerRequest request);

    @POST("customer/new-customer/supply")
    Observable<NewCustomerSupplyResponse> createNewCustomer(@Body NewCustomerSupplyRequest request);

    @POST("customer/new-customer/complete")
    Observable<OperationCompleteResponse> createNewCustomer(@Body NewCustomerCompleteRequest request);

    // customer ministatement

    @POST("customer/ministatement")
    Observable<CustomerMinistatementResponse> initCustomerMinistatement(@Body CustomerMinistatementRequest request);

    @POST("customer/ministatement/supply")
    Observable<CustomerMinistatementSupplyResponse> supplyCustomerMinistatement(@Body CustomerMinistatementSupplyRequest request);

    @POST("customer/ministatement/complete")
    Observable<OperationCompleteResponse> completeCustomerMinistatemnt(@Body CustomerCompleteRequest request);

    // bill payment by cash

    @POST("customer/payment/by-cash")
    Observable<CustomerPaymentByCashResponse> getCustomerPaymentByCash(@Body CustomerPaymentByCashRequest request);

    @POST("customer/payment/by-cash/supply")
    Observable<CustomerPaymentByCashSupplyResponse> supplyCustomerPaymentByCash(@Body CustomerPaymentByCashSupplyRequest request);

    @POST("customer/payment/by-cash/complete")
    Observable<OperationCompleteResponse> completeCustomerPaymentByCash(@Body CustomerPaymentByCashCompleteRequest request);

    // bill payment from account

    @POST("customer/payment/by-acct")
    Observable<CustomerPaymentFromAccountResponse> getCustomerPaymentFromAccount(@Body CustomerPaymentFromAccountRequest request);

    @POST("customer/payment/by-acct/supply")
    Observable<CustomerPaymentFromAccountSupplyResponse> supplyCustomerPaymentFromAccount(@Body CustomerPaymentFromAccountSupplyRequest request);

    @POST("customer/payment/by-acct/complete")
    Observable<OperationCompleteResponse> completeCustomerPaymentFromAccount(@Body CustomerPaymentFromAccountCompleteRequest request);

    // customer card pin change

    @POST("customer/pin-change")
    Observable<CustomerCardPinChangeResponse> initCustomerCardPinChange(@Body CustomerCardPinChangeRequest request);

    @POST("customer/pin-change/supply")
    Observable<CustomerCardPinChangeSupplyResponse> supplyCustomerCardPinChange(@Body CustomerCardPinChangeSupplyRequest request);

    @POST("customer/pin-change/complete")
    Observable<OperationCompleteResponse> completeCustomerCardPinChange(@Body CustomerCardPinChangeCompleteRequest request);

    // repayment cash to loan

    @POST("customer/repayment/cash-to-loan")
    Observable<CustomerRepaymentCashToLoanResponse> initCustomerRepaymentCashToLoan(@Body CustomerRepaymentCashToLoanRequest request);

    @POST("customer/repayment/cash-to-loan/supply")
    Observable<CustomerRepaymentCashToLoanSupplyResponse> supplyCustomerRepaymentCashToLoan(@Body CustomerRepaymentCashToLoanSupplyRequest request);

    @POST("customer/repayment/cash-to-loan/complete")
    Observable<OperationCompleteResponse> completeCustomerRepaymentCashToLoan(@Body CustomerRepaymentCashToLoanCompleteRequest request);

    // loan payment from account

    @POST("customer/repayment/acct-to-loan")
    Observable<CustomerRepaymentFromAccountResponse> initCustomerLoanPaymentFromAccount(@Body CustomerRepaymentFromAccountRequest request);

    @POST("customer/repayment/acct-to-loan/supply")
    Observable<CustomerRepaymentFromAccountSupplyResponse> supplyCustomerLoanPaymentFromAccount(@Body CustomerRepaymentFromAccountSupplyRequest request);

    @POST("customer/repayment/acct-to-loan/complete")
    Observable<OperationCompleteResponse> completeCustomerLoanPaymentFromAccount(@Body CustomerRepaymentFromAccountCompleteRequest request);

    //===============================================================================================
    @GET("rates")
    Observable<RatesResponse> getRates();

    @GET("serverCapabilities")
    Observable<CapabilitiesResponse> getCapabilities();

    @GET("profile")
    Observable<ProfileResponse> getAgentProfile();

    @POST("deviceInfo")
    Observable<MainResponse> postDeviceInfo(@Body DeviceInfoRequest request);

    @POST("devices")
    Observable<DevicesResponse> getDevices(@Body StampedRequest request);

    @POST("deviceDetach")
    Observable<MainResponse> deviceDetach(@Body DeviceDetachRequest request);


    //===================================================================================

    @POST("cards")
    Observable<CardsResponse> getCards(@Body CardRequest request);

    @POST("accounts")
    Observable<AccountsResponse> getAccounts(@Body AccountRequest request);

    @POST("card")
    Observable<CardDetailResponse> getCard(@Body CardDetailRequest request);

    @POST("account")
    Observable<AccountDetailResponse> getAccount(@Body AccountDetailRequest request);

    @POST("cardHistory")
    Observable<CardHistoryResponse> getCardHistory(@Body CardHistoryRequest request);

    @POST("agent/account/history")
    Observable<AccountHistoryResponse> getAccountHistory(@Body AccountHistoryRequest request);

    @POST("cardChange")
    Observable<MainResponse> cardChange(@Body CardChangeRequest request);

    @POST("accountChange")
    Observable<MainResponse> accountChange(@Body AccountChangeRequest request);

    // Images uploading

    @POST("data/image")
    Observable<ImageUploadResponse> uploadDataImage(@Body RequestBody photo);

    // Payment services

    @POST("data/payments/categories")
    Observable<PaymentCategoriesResponse> getPaymentCategories(@Body PaymentCategoriesRequest request);

    @POST("data/payments/providers")
    Observable<PaymentProvidersResponse> getPaymentProviders(@Body PaymentProvidersRequest request);

    // operations history
    @POST("customer/operations/history")
    Observable<CustomerOperationsHistoryResponse> getCustomerOperationsHistory(@Body CustomerOperationsHistoryRequest request);
}
