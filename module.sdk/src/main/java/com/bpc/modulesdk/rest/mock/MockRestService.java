package com.bpc.modulesdk.rest.mock;

import android.support.annotation.Nullable;
import android.util.Log;

import com.bpc.modulesdk.errors.ErrorCode;
import com.bpc.modulesdk.rest.DtoObjectMapper;
import com.bpc.modulesdk.rest.RestService;
import com.bpc.modulesdk.rest.dto.pojo.AccountOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.CardOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerAccountEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationProvidedDataEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationRequestEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.PaymentInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.RepaymentInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.TransferRequiredData;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.bpc.modulesdk.errors.ErrorCode.ADDITIONAL_PARAMS_REQUIRED;
import static com.bpc.modulesdk.errors.ErrorCode.PARAMS_CONFIRMATION_REQUIRED;
import static com.bpc.modulesdk.net.ssl.CertificateUnknownHandler.funcCertificateDialog;

/**
 * Created by Masloed on 14.12.2016.
 */

public class MockRestService implements RestService {

    private static final String TAG = "MockRestService";
    public static final int DELAY = 1;
    private static ExecutorService webRequestsExecutor = Executors.newFixedThreadPool(5);

    private static MockRestService instance;

    public static MockRestService create() {
        if (instance == null) instance = new MockRestService();
        return instance;
    }

    private <T> Observable.Transformer<T, T> applyTransforms() {
        return observable -> observable
                .subscribeOn(Schedulers.from(webRequestsExecutor))
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(funcCertificateDialog);
    }

    @Override
    public Observable<MainResponse> login(String login, String password, String deviceId) {

        MainResponse response = new MainResponse();
        if (login.equals("user1")) response.setResult(ErrorCode.DEVICE_BINDING_REQUIRED);
        if (login.equals("user2"))
            response.setResult(ErrorCode.DEVICE_BINDING_CONFIRMATION_EXCPECTED);
        if (login.equals("user3")) response.setResult(ErrorCode.DEVICE_BINDING_REJECTED);
        return Observable.fromCallable(() -> response).delay(2, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<MainResponse> login(String login, String password, String deviceId, LocationEntry locationEntry) {
        return login(login, password, deviceId);
    }

    @Override
    public Observable<MainResponse> logout() {
        return Observable.fromCallable(() -> new MainResponse()).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<MainResponse> bindDevice(String deviceName, String deviceId, String deviceKey) {
        return Observable.fromCallable(() -> new MainResponse()).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<MainResponse> changePassword(String pwd) {
        return Observable.fromCallable(() -> new MainResponse()).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<MainResponse> changePassword(String oldpwd, String newpwd) {
        MainResponse response = new MainResponse();
        response.setResult(ErrorCode.SUCCESS);
        return Observable.fromCallable(() -> response).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerBalanceResponse> execCustomerBalance(CustomerCardEntry cardEntry, LocationEntry locationEntry) {
        CustomerBalanceResponse response = new CustomerBalanceResponse();
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_BALANCE_INIT_ADDITIONAL_PARAMS_REQUIRED, CustomerBalanceResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "initCustomerBalance: ", e);
        }
        CustomerBalanceResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());

    }


    @Override
    public Observable<CustomerBalanceSupplyResponse> supplyCustomerBalance(String transRef, CustomerBalanceSupplyRequest.DataResponse dataResponse) {
        CustomerBalanceSupplyResponse response = new CustomerBalanceSupplyResponse();
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_BALANCE_SUPPLY, CustomerBalanceSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerBalance: ", e);
        }
        CustomerBalanceSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerBalanceCompleteResponse> completeCustomerBalance(String transRef, CustomerBalanceCompleteRequest.DataResponse dataResponse) {
        CustomerBalanceCompleteResponse response = new CustomerBalanceCompleteResponse();
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_BALANCE_COMPLETE, CustomerBalanceCompleteResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "completeCustomerBalance: ", e);
        }
        CustomerBalanceCompleteResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerAcctToAcctTransferResponse> execCustomerAccToAccTransfer(CustomerCardEntry sourceCard,
                                                                                       String targetCard, MoneyEntry operationAmount, String mode, LocationEntry location) {
        CustomerAcctToAcctTransferResponse response = new CustomerAcctToAcctTransferResponse();
        TransferRequiredData requiredData = new TransferRequiredData();
        requiredData.setSelectSourceAccount(MockRestServiceDataStorage.getInstance().getCustomerSourceAccounts());
        requiredData.setSelectTargetAccount(MockRestServiceDataStorage.getInstance().getCustomerTargetAccounts());
        response.setDataRequest(requiredData);
        response.setResult(ADDITIONAL_PARAMS_REQUIRED);
        return Observable.fromCallable(() -> response).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerAcctToAcctSupplyResponse> supplyCustomerAccToAccTransfer(CustomerAccountEntry sourceAccount, CustomerAccountEntry targetAccount,
                                                                                       String targetCard, MoneyEntry operationAmount, String transRef) {
        CustomerAcctToAcctSupplyResponse response = new CustomerAcctToAcctSupplyResponse();
        response.setConfirmationRequest(new OperationConfirmationRequestEntry(true, true));

        CustomerAcctToAcctSupplyResponse.RequiredData data = new CustomerAcctToAcctSupplyResponse.RequiredData();
        data.setSelectTargetAccount(MockRestServiceDataStorage.getInstance().getCustomerTargetAccounts());
        response.setDataRequest(data);

        OperationDetailsEntry operationDetails = new OperationDetailsEntry(sourceAccount, targetAccount, operationAmount);
        response.setOperationDetails(MockRestServiceDataStorage.getInstance().initOperationDetails(operationDetails));

        if (targetAccount == null) {
            response.setResult(ADDITIONAL_PARAMS_REQUIRED);
        } else response.setResult(PARAMS_CONFIRMATION_REQUIRED);

        return Observable.fromCallable(() -> response).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerAccToAccTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_ACC_TO_ACC_COMPLETE);
    }

    @Override
    public Observable<CustomerCashReceiveResponse> initCustomerCashReceive(String remittanceCode,
                                                                           String receiverPhoneNumber, MoneyEntry operationAmount, LocationEntry location) {
        CustomerCashReceiveResponse response = new CustomerCashReceiveResponse();
        response.setResult(ADDITIONAL_PARAMS_REQUIRED);

        OperationDetailsEntry detailsEntry = MockRestServiceDataStorage.getInstance().initCashReceiveOperationDetails(new CustomerAccountEntry(), operationAmount);
        CustomerCashReceiveResponse.RequiredData data = new CustomerCashReceiveResponse.RequiredData();
        data.setSelectAgentAccount(MockRestServiceDataStorage.getInstance().getCustomerTargetAccounts());
        response.setDataRequest(data);
        response.setOperationDetails(detailsEntry);
        response.setDataRequest(MockRestServiceDataStorage.getInstance().getCashReceiveRequiredData());
        return Observable.fromCallable(() -> response).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerCashReceiveResponse> supplyCustomerCashReceive(String agentAccount, MoneyEntry operationAmount, String transRef) {

        CustomerCashReceiveResponse response = new CustomerCashReceiveResponse();
        response.setResult(ErrorCode.PARAMS_CONFIRMATION_REQUIRED);

        CustomerAccountEntry account = null;
        for (CustomerAccountEntry account1 : MockRestServiceDataStorage.getInstance().getCustomerTargetAccounts()) {
            if (agentAccount.equals(account1.getId())) {
                account = account1;
                break;
            }
        }

        OperationDetailsEntry detailsEntry = MockRestServiceDataStorage.getInstance().initCashReceiveOperationDetails(account, operationAmount);
        //detailsEntry.setOperationAmount(operationAmount);
        response.setOperationDetails(detailsEntry);

        return Observable.fromCallable(() -> response).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCashReceive(@Nullable String agentPass, String transRef) {
        return completeOperation(JsonStore.JSON_CASH_RECEIVE_COMPLETE);
    }

    @Override
    public Observable<CustomerCashWithdrawalResponse> getCustomerCashWithdrawal(CustomerCardEntry cardEntry, LocationEntry locationEntry) {
        CustomerCashWithdrawalResponse response = new CustomerCashWithdrawalResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CASH_WITHDRAWAL, CustomerCashWithdrawalResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerCashWithdrawal: ", e);
        }
        CustomerCashWithdrawalResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerCashWithdrawalSupplyResponse> supplyCustomerCashWithdrawal(String transRef, CustomerCashWithdrawalSupplyRequest.ProvidedData data) {
        CustomerCashWithdrawalSupplyResponse response = new CustomerCashWithdrawalSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CASH_WITHDRAWAL_SUPPLY, CustomerCashWithdrawalSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerCashWithdrawal: ", e);
        }
        CustomerCashWithdrawalSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCashWithdrawal(String transRef, OperationConfirmationProvidedDataEntry data) {
        return completeOperation(JsonStore.JSON_CASH_WITHDRAWAL_COMPLETE);
    }

    @Override
    public Observable<CustomerCashDepositResponse> getCustomerCashDeposit(CustomerCardEntry cardEntry, LocationEntry locationEntry) {
        CustomerCashDepositResponse response = new CustomerCashDepositResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CASH_DEPOSIT, CustomerCashDepositResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerCashDeposit: ", e);
        }
        CustomerCashDepositResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerCashDepositSupplyResponse> supplyCustomerCashDeposit(String transRef, CustomerCashDepositSupplyRequest.ProvidedData data) {
        CustomerCashDepositSupplyResponse response = new CustomerCashDepositSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CASH_DEPOSIT_SUPPLY, CustomerCashDepositSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerCashDeposit: ", e);
        }
        CustomerCashDepositSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCashDeposit(String transRef, OperationConfirmationProvidedDataEntry data) {
        return completeOperation(JsonStore.JSON_CASH_DEPOSIT_COMPLETE);
    }

    @Override
    public Observable<AgentAcctToAcctTransferResponse> getAgentAccountToAccountTransfer(String sourceId, String targetId, MoneyEntry amount, LocationEntry locationEntry) {
        AgentAcctToAcctTransferResponse response = new AgentAcctToAcctTransferResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_AGENT_ACCT_TO_ACCT, AgentAcctToAcctTransferResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getAgentAccountToAccountTransfer: ", e);
        }
        AgentAcctToAcctTransferResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<AgentAcctToAcctTransferSupplyResponse> supplyAgentAccountToAccountTransfer(String transRef, AgentAcctToAcctTransferSupplyRequest.DataResponse data) {
        AgentAcctToAcctTransferSupplyResponse response = new AgentAcctToAcctTransferSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_AGENT_ACCT_TO_ACCT_SUPPLY, AgentAcctToAcctTransferSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyAgentAccountToAccountTransfer: ", e);
        }
        AgentAcctToAcctTransferSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeAgentAccountToAccountTransfer(String transRef, AgentAcctToAcctTransferCompleteRequest.DataResponse data) {
        return completeOperation(JsonStore.JSON_AGENT_ACCT_TO_ACCT_COMPLETE);
    }

    @Override
    public Observable<AgentStatisticsResponse> getAgentStatistics(Date dateFrom, Date dateTill, boolean details) {
        AgentStatisticsResponse response = new AgentStatisticsResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_AGENT_STATISTICS, AgentStatisticsResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getAgentStatistics: ", e);
        }
        AgentStatisticsResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerAccountToCashTransferResponse> getCustomerAccountToCashTransfer(CustomerCardEntry sourceCard, MoneyEntry operationAmount, LocationEntry location) {
        CustomerAccountToCashTransferResponse response = new CustomerAccountToCashTransferResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_ACCOUNT_TO_CASH, CustomerAccountToCashTransferResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerAccountToCashTransfer: ", e);
        }
        CustomerAccountToCashTransferResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerAccountToCashTransferSupplyResponse> supplyCustomerAccountToCashTransfer(String transRef, CustomerAccountToCashTransferSupplyRequest.DataResponse dataResponse) {
        CustomerAccountToCashTransferSupplyResponse response = new CustomerAccountToCashTransferSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_ACCOUNT_TO_CASH_SUPPLY, CustomerAccountToCashTransferSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerAccountToCashTransfer: ", e);
        }
        CustomerAccountToCashTransferSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerAccountToCashTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_ACCOUNT_TO_CASH_COMPLETE);
    }

    @Override
    public Observable<CustomerCashToAccountTransferResponse> getCustomerCashToAccountTransfer(String targetCard, MoneyEntry operationAmount, LocationEntry location) {
        CustomerCashToAccountTransferResponse response = new CustomerCashToAccountTransferResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_CASH_TO_ACCOUNT, CustomerCashToAccountTransferResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerCashToAccountTransfer: ", e);
        }
        CustomerCashToAccountTransferResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerCashToAccountTransferSupplyResponse> supplyCustomerCashToAccountTransfer(String transRef, CustomerCashToAccountTransferSupplyRequest.DataResponse dataResponse) {
        CustomerCashToAccountTransferSupplyResponse response = new CustomerCashToAccountTransferSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_CASH_TO_ACCOUNT_SUPPLY, CustomerCashToAccountTransferSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerCashToAccountTransfer: ", e);
        }
        CustomerCashToAccountTransferSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCashToAccountTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_CASH_TO_ACCOUNT_COMPLETE);
    }

    @Override
    public Observable<CustomerCashToCashTransferResponse> getCustomerCashToCashTransfer(String senderPhoneNumber, String receiverPhoneNumber, MoneyEntry operationAmount, LocationEntry location) {
        CustomerCashToCashTransferResponse response = new CustomerCashToCashTransferResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_CASH_TO_CASH, CustomerCashToCashTransferResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerCashToCashTransfer: ", e);
        }
        CustomerCashToCashTransferResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerCashToCashTransferSupplyResponse> supplyCustomerCashToCashTransfer(String transRef, CustomerCashToCashTransferSupplyRequest.DataResponse dataResponse) {
        CustomerCashToCashTransferSupplyResponse response = new CustomerCashToCashTransferSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_CASH_TO_CASH_SUPPLY, CustomerCashToCashTransferSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerCashToCashTransfer: ", e);
        }
        CustomerCashToCashTransferSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCashToCashTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_CASH_TO_CASH_COMPLETE);
    }

    @Override
    public Observable<CustomerNewAccountResponse> getCustomerNewAccount(CustomerCardEntry cardEntry, LocationEntry location) {
        CustomerNewAccountResponse response = new CustomerNewAccountResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_NEW_ACCOUNT, CustomerNewAccountResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerNewAccount: ", e);
        }
        CustomerNewAccountResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerNewAccountSupplyResponse> getCustomerNewAccount(String transRef, CustomerNewAccountSupplyRequest.DataResponse dataResponse) {
        CustomerNewAccountSupplyResponse response = new CustomerNewAccountSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_NEW_ACCOUNT_SUPPLY, CustomerNewAccountSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerNewAccount: ", e);
        }
        CustomerNewAccountSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> getCustomerNewAccount(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_NEW_ACCOUNT_COMPLETE);
    }

    @Override
    public Observable<CustomerNewCardResponse> getCustomerNewCard(CustomerCardEntry cardEntry, LocationEntry location) {
        CustomerNewCardResponse response = new CustomerNewCardResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_NEW_CARD_1, CustomerNewCardResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerNewCard: ", e);
        }
        CustomerNewCardResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerNewCardSupplyResponse> supplyCustomerNewCard(String transRef, CustomerNewCardSupplyRequest.DataResponse dataResponse) {
        CustomerNewCardSupplyResponse response = new CustomerNewCardSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_NEW_CARD_SUPPLY_1, CustomerNewCardSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerNewCard: ", e);
        }
        CustomerNewCardSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerNewCardSupplyResponse> supplyCustomerNewCard2(String transRef, CustomerNewCardSupplyRequest.DataResponse dataResponse) {
        CustomerNewCardSupplyResponse response = new CustomerNewCardSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_NEW_CARD_SUPPLY_2, CustomerNewCardSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerNewCard2: ", e);
        }
        CustomerNewCardSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerNewCard(String transRef, CustomerNewCardCompleteRequest.DataResponse dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_NEW_CARD_COMPLETE);
    }

    @Override
    public Observable<NewCustomerResponse> createNewCustomer(CustomerCardEntry newCard, LocationEntry location) {
        NewCustomerResponse response = new NewCustomerResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_NEW_CUSTOMER, NewCustomerResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "createNewCustomer: ", e);
        }
        NewCustomerResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<NewCustomerSupplyResponse> createNewCustomer(String transRef, NewCustomerSupplyRequest.DataResponse dataResponse) {
        NewCustomerSupplyResponse response = new NewCustomerSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_NEW_CUSTOMER_SUPPLY, NewCustomerSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "createNewCustomer: ", e);
        }
        NewCustomerSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> createNewCustomer(String transRef, OperationConfirmationProvidedDataEntry data) {
        return completeOperation(JsonStore.JSON_NEW_CUSTOMER_COMPLETE);
    }

    @Override
    public Observable<CustomerPaymentByCashResponse> getCustomerPaymentByCash(PaymentInfoEntry paymentInfo, LocationEntry location) {
        CustomerPaymentByCashResponse response = new CustomerPaymentByCashResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_PAYMENT_BY_CASH, CustomerPaymentByCashResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerPaymentByCash: ", e);
        }
        CustomerPaymentByCashResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerPaymentByCashSupplyResponse> supplyCustomerPaymentByCash(String transRef, CustomerPaymentByCashSupplyRequest.DataResponse dataResponse) {
        CustomerPaymentByCashSupplyResponse response = new CustomerPaymentByCashSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_PAYMENT_BY_CASH_SUPPLY, CustomerPaymentByCashSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerPaymentByCash: ", e);
        }
        CustomerPaymentByCashSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerPaymentByCash(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_PAYMENT_BY_CASH_COMPLETE);
    }

    @Override
    public Observable<CustomerPaymentFromAccountResponse> getCustomerPaymentFromAccount(CustomerCardEntry card, LocationEntry location) {
        CustomerPaymentFromAccountResponse response = new CustomerPaymentFromAccountResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_PAYMENT_FROM_ACC, CustomerPaymentFromAccountResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerPaymentFromAccount: ", e);
        }
        CustomerPaymentFromAccountResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerPaymentFromAccountSupplyResponse> supplyCustomerPaymentFromAccount(String transRef, CustomerPaymentFromAccountSupplyRequest.DataResponse dataResponse) {
        CustomerPaymentFromAccountSupplyResponse response = new CustomerPaymentFromAccountSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_PAYMENT_FROM_ACC_SUPPLY, CustomerPaymentFromAccountSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerPaymentFromAccount: ", e);
        }
        CustomerPaymentFromAccountSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerPaymentFromAccount(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_PAYMENT_FROM_ACC_COMPLETE);
    }

    @Override
    public Observable<CustomerCardPinChangeResponse> initCustomerCardPinChange(CustomerCardEntry card, LocationEntry location) {
        CustomerCardPinChangeResponse response = new CustomerCardPinChangeResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_CARD_PIN_CHANGE, CustomerCardPinChangeResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerCardPinChange: ", e);
        }
        CustomerCardPinChangeResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerCardPinChangeSupplyResponse> supplyCustomerCardPinChange(String transRef, CustomerCardPinChangeSupplyRequest.DataResponse dataResponse) {
        CustomerCardPinChangeSupplyResponse response = new CustomerCardPinChangeSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_CARD_PIN_CHANGE_SUPPLY, CustomerCardPinChangeSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerCardPinChange: ", e);
        }
        CustomerCardPinChangeSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCardPinChange(String transRef) {
        OperationCompleteResponse response = new OperationCompleteResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_CARD_PIN_CHANGE_COMPLETE, OperationCompleteResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "completeCustomerCardPinChange: ", e);
        }
        OperationCompleteResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerRepaymentCashToLoanResponse> initCustomerRepaymentCashToLoan(LocationEntry location, RepaymentInfoEntry repaymentInfo) {
        CustomerRepaymentCashToLoanResponse response = new CustomerRepaymentCashToLoanResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_REPAYMENT_BY_CASH_INIT, CustomerRepaymentCashToLoanResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "initCustomerRepaymentCashToLoan: ", e);
        }
        CustomerRepaymentCashToLoanResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerRepaymentCashToLoanSupplyResponse> supplyCustomerRepaymentCashToLoan(String transRef, CustomerRepaymentCashToLoanSupplyRequest.DataResponse dataResponse) {
        CustomerRepaymentCashToLoanSupplyResponse response = new CustomerRepaymentCashToLoanSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_REPAYMENT_BY_CASH_SUPPLY, CustomerRepaymentCashToLoanSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerRepaymentCashToLoan: ", e);
        }
        CustomerRepaymentCashToLoanSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerRepaymentCashToLoan(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_REPAYMENT_BY_CASH_COMPLETE);
    }

    @Override
    public Observable<CustomerRepaymentFromAccountResponse> initCustomerLoanPaymentFromAccount(CustomerCardEntry sourceCard, LocationEntry location) {
        CustomerRepaymentFromAccountResponse response = new CustomerRepaymentFromAccountResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_REPAYMENT_FROM_ACC_INIT, CustomerRepaymentFromAccountResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "initCustomerLoanPaymentFromAccount: ", e);
        }
        CustomerRepaymentFromAccountResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerRepaymentFromAccountSupplyResponse> supplyCustomerLoanPaymentFromAccount(String transRef, CustomerRepaymentFromAccountSupplyRequest.DataResponse dataResponse) {
        CustomerRepaymentFromAccountSupplyResponse response = new CustomerRepaymentFromAccountSupplyResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_REPAYMENT_FROM_ACC_SUPPLY, CustomerRepaymentFromAccountSupplyResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "supplyCustomerLoanPaymentFromAccount: ", e);
        }
        CustomerRepaymentFromAccountSupplyResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerLoanPaymentFromAccount(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_REPAYMENT_FROM_ACC_COMPLETE);
    }

    @Override
    public Observable<RatesResponse> getRates() {
        RatesResponse ratesResponse = new RatesResponse();
        ratesResponse.setRates(MockRestServiceDataStorage.getInstance().getRates());
        ratesResponse.setResult(0);
        return Observable.fromCallable(() -> ratesResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CapabilitiesResponse> getServerCapabilities() {
        CapabilitiesResponse capabilitiesResponse = new CapabilitiesResponse();
        MockRestServiceDataStorage storage = MockRestServiceDataStorage.getInstance();
        capabilitiesResponse.setSupportedCurrencies(storage.getSupportedCurrencies());
        capabilitiesResponse.setSupportedFunctions(storage.getSupportedFunctions().get(0));
       /* capabilitiesResponse.setSupportedRegions(storage.getSupportedRegions());
        capabilitiesResponse.setSupportedFunctions(storage.getSupportedFunctions());
        capabilitiesResponse.setAdditionalParameters(null);*/
        capabilitiesResponse.setResult(0);
        return Observable.fromCallable(() -> capabilitiesResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<ProfileResponse> getAgentProfile() {
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setGeoRestrictionEnabled(1);
        profileResponse.setResult(0);
        return Observable.fromCallable(() -> profileResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<MainResponse> postDeviceInfo() {
        return Observable.fromCallable(() -> new MainResponse()).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<AccountsResponse> getAccounts() {
        AccountsResponse accountsResponse = new AccountsResponse();
        accountsResponse.setAccounts(MockRestServiceDataStorage.getInstance().getAccounts());
        accountsResponse.setResult(0);
        return Observable.fromCallable(() -> accountsResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<DevicesResponse> getDevices() {
        DevicesResponse response = new DevicesResponse();
        response.setDevices(MockRestServiceDataStorage.getInstance().getDevices());
        return Observable.fromCallable(() -> response).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<MainResponse> deviceDetach(DeviceDetachRequest request) {
        MockRestServiceDataStorage.getInstance().deviceDetach(request.devids);
        return Observable.fromCallable(() -> new MainResponse()).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<ImageUploadResponse> uploadImage(File imageFile) {
        return Observable.fromCallable(() -> new ImageUploadResponse()).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<PaymentCategoriesResponse> getPaymentCategories(List<String> cids, String nameFilter, LocationEntry location) {
        PaymentCategoriesResponse response = new PaymentCategoriesResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_PAYMENT_CATEGORIES, PaymentCategoriesResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getPaymentCategories: ", e);
        }
        PaymentCategoriesResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<PaymentProvidersResponse> getPaymentProviders(List<String> pids, List<String> cids, String nameFilter, LocationEntry location) {
        PaymentProvidersResponse response = new PaymentProvidersResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_PAYMENT_PROVIDERS, PaymentProvidersResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getPaymentProviders: ", e);
        }
        PaymentProvidersResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerOperationsHistoryResponse> getCustomerOperationsHistory(CustomerCardEntry card, List<OperationInfoEntry.OperationType> operationTypes) {
        CustomerOperationsHistoryResponse response = new CustomerOperationsHistoryResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(JsonStore.JSON_CUSTOMER_OPERATIONS_HISTORY, CustomerOperationsHistoryResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "getCustomerOperationsHistory: ", e);
        }
        CustomerOperationsHistoryResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CardsResponse> getCards() {
        CardsResponse response = new CardsResponse();
        response.cards = MockRestServiceDataStorage.getInstance().getCards();
        return Observable.fromCallable(() -> DtoObjectMapper.create().copyObject(response)).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CardDetailResponse> getCard(String id) {
        CardDetailResponse response = new CardDetailResponse();
        response.card = MockRestServiceDataStorage.getInstance().getCard(id);
        return Observable.fromCallable(() -> DtoObjectMapper.create().copyObject(response)).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<AccountDetailResponse> getAccount(String id) {
        AccountDetailResponse response = new AccountDetailResponse();
        response.account = MockRestServiceDataStorage.getInstance().getAccount(id);
        return Observable.fromCallable(() -> DtoObjectMapper.create().copyObject(response)).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CardHistoryResponse> getCardHistory(String id, int page, String startDate, String endDate) {
        CardHistoryResponse response = new CardHistoryResponse();
        response.setHistory((List<CardOperationRecord>) MockRestServiceDataStorage.getInstance().getCardHistory(id));
        return Observable.fromCallable(() -> response).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }


    @Override
    public Observable<AccountHistoryResponse> getAccountHistory(String number, int page, Date from, Date to) {
        AccountHistoryResponse response = new AccountHistoryResponse();
        response.setHistory((List<AccountOperationRecord>) MockRestServiceDataStorage.getInstance().getAccountHistory(number));
        return Observable.fromCallable(() -> response).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerMinistatementResponse> initCustomerMinistatement(CustomerCardEntry card, LocationEntry locationEntry) {

        CustomerMinistatementResponse response = new CustomerMinistatementResponse();
        response.setResult(ErrorCode.ADDITIONAL_PARAMS_REQUIRED);
        response.setTransRef("11111");
        CustomerMinistatementResponse.RequiredData data = new CustomerMinistatementResponse.RequiredData();
        data.setSelectSourceAccount(MockRestServiceDataStorage.getInstance().getCustomerSourceAccounts());
        response.setDataRequest(data);
        return Observable.fromCallable(() -> response).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<CustomerMinistatementSupplyResponse> supplyCustomerMinistatement(String transRef, String sourceAccount) {

        CustomerMinistatementSupplyResponse response = new CustomerMinistatementSupplyResponse();
        response.setResult(ErrorCode.PARAMS_CONFIRMATION_REQUIRED);

        CustomerAccountEntry sourceAccountEntry = MockRestServiceDataStorage.getInstance().getCustomerSourceAccounts().get(0);
        CustomerAccountEntry targetAccountEntry = MockRestServiceDataStorage.getInstance().getCustomerTargetAccounts().get(1);
        MoneyEntry moneyEntry = new MoneyEntry();
        moneyEntry.setCurrency("RUB");
        moneyEntry.setAmount(BigDecimal.valueOf(1000));

        OperationDetailsEntry operationDetailsEntry = new OperationDetailsEntry(sourceAccountEntry, targetAccountEntry, moneyEntry);
        OperationDetailsEntry operationDetails = MockRestServiceDataStorage.getInstance().initOperationDetails(operationDetailsEntry);
        response.setOperationDetails(operationDetails);

        OperationConfirmationRequestEntry confEntry = new OperationConfirmationRequestEntry();
        confEntry.setAgentPassRequired(true);
        confEntry.setCardPinRequired(true);

        response.setConfirmationRequest(confEntry);

        return Observable.fromCallable(() -> response).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    public Observable<OperationCompleteResponse> completeCustomerMinistatement(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return completeOperation(JsonStore.JSON_CUSTOMER_MINISTATEMENT_COMPLETE);
    }

    @Override
    public Observable<MainResponse> cardRename(String id, String newName) {
        MockRestServiceDataStorage.getInstance().changeCard(id, newName, null, null, null);
        return Observable.fromCallable(() -> new MainResponse()).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public Observable<MainResponse> getAccountRenameRequest(String id, String newName) {
        MockRestServiceDataStorage.getInstance().changeAccount(id, newName, null, null, null);
        return Observable.fromCallable(() -> new MainResponse()).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }

    @Override
    public void removeAllDataFromCache() {
    }

    @Override
    public void removeDataFromCache(Class clazz) {
    }

    @Override
    public void removeDataFromCache(Class clazz, String cacheTag) {
    }

    public Observable<OperationCompleteResponse> completeOperation(String json) {
        OperationCompleteResponse response = new OperationCompleteResponse();
        response.setResult(0);
        try {
            response = DtoObjectMapper.create().readValue(json, OperationCompleteResponse.class);
        } catch (IOException e) {
            Log.e(TAG, "completeOperation: ", e);
        }
        OperationCompleteResponse finalResponse = response;
        return Observable.fromCallable(() -> finalResponse).delay(DELAY, TimeUnit.SECONDS).compose(applyTransforms());
    }
}
