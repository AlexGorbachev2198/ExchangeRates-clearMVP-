package com.bpc.modulesdk.rest.retrofit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.bpc.modulesdk.net.OkHttpClientBuilder;
import com.bpc.modulesdk.net.cache.CacheTime;
import com.bpc.modulesdk.net.cache.WebRequestCache;
import com.bpc.modulesdk.net.cache.WebRequestCacheFactory;
import com.bpc.modulesdk.rest.DtoObjectMapper;
import com.bpc.modulesdk.rest.RestService;
import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerAccountEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationProvidedDataEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.PaymentInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.RepaymentInfoEntry;
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
import com.bpc.modulesdk.rest.dto.response.CommonResponse;
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
import com.bpc.modulesdk.security.SharedPreferencesHelper;
import com.bpc.modulesdk.settings.ApplicationPropertiesProvider;
import com.bpc.modulesdk.utils.DateHelper;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.bpc.modulesdk.net.ssl.CertificateUnknownHandler.funcCertificateDialog;

/**
 * Created by Masloed on 24.11.2016.
 */
public class RetrofitRestService implements RestService {
    private static final String TAG = RetrofitRestService.class.getSimpleName();
    private static RetrofitAPI api;
    private static RetrofitRestService instance;
    private static ExecutorService webRequestsExecutor = Executors.newFixedThreadPool(5);
    private static WebRequestCache webRequestCache = WebRequestCacheFactory.instantiate();

    private RetrofitRestService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        String serverAddress = SharedPreferencesHelper.getServerAddress();
        if (serverAddress.isEmpty()) {
            //serverAddress = ApplicationPropertiesProvider.getServerAddress();
            //SharedPreferencesHelper.setServerAddress(serverAddress);
            serverAddress = "https://t5-web-test2.bt.bpc.in:7002/agencybank/service/";
             //serverAddress = "https://10.7.4.20:7002/mobilebank/service/";
            // serverAddress = "https://raw.githubusercontent.com/AlexGorbachev2198/forFiles/master/rates.json/";

        }
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(serverAddress)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(DtoObjectMapper.create()))
                .client(OkHttpClientBuilder.getClient());
        api = builder.build().create(RetrofitAPI.class);
        instance = this;
    }

    public static RetrofitRestService create() {
        if (instance == null) instance = new RetrofitRestService();
        return instance;
    }

    public static void kill() {
        if (instance != null) instance = null;
    }

    ////==============Transformers====================================
    private <T> Observable.Transformer<T, T> applyTransforms() {
        return observable -> {
            return observable
                    .subscribeOn(Schedulers.from(webRequestsExecutor))
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .retryWhen(funcCertificateDialog);
                    /*.onErrorReturn(new Func1<Throwable, T>() {
                        @Override
                        public T call(Throwable t) {
                            Exceptions.propagate(t);
                            return null;
                        }
                    });*/
        };
    }

    /**
     * Apply default settings for web requests
     *
     * @param <T> result type
     * @return return transformer with default web request settings
     */
    private <T extends CommonResponse> Observable.Transformer<T, T> applyTransforms(final String cacheTag) {
        return observable -> observable
                .subscribeOn(Schedulers.from(webRequestsExecutor))
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(funcCertificateDialog)
                .doOnNext(t -> webRequestCache.add((T) t, cacheTag));
    }

    /**
     * Apply default settings for web requests and cache result setting
     *
     * @param clazz               response class
     * @param cacheTag            any string that identify special request
     * @param cacheExpirationTime how much time cache considered not expired
     * @param <T>                 result type
     * @return transformer with default web request and cache result setting
     */
    private <T extends CommonResponse> Observable.Transformer<T, T> applyCacheTransformer(final Class<T> clazz, final String cacheTag, final long cacheExpirationTime) {
        return observable -> {
            Observable<T> obs = observable.compose(applyTransforms(cacheTag));
            return createGetCacheObservable(clazz, cacheTag, cacheExpirationTime)
                    .concatMap(createReturnCacheFunction(obs));
        };
    }

    ///====================AuthorizationServices==============================
    @Override
    public Observable<MainResponse> login(String login, String password, String deviceId) {
        return api.login(new LoginRequest(login, password, deviceId))
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<MainResponse> login(String login, String password, String deviceId, LocationEntry locationEntry) {
        LoginRequest request = new LoginRequest(login, password, deviceId);
        request.setLocation(locationEntry);
        return api.login(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<MainResponse> logout() {
        return api.logout()
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<MainResponse> bindDevice(String deviceName, String deviceId, String deviceKey) {
        return api.deviceBind(new DeviceBindingRequest(deviceName, deviceId, deviceKey)).compose(this.applyTransforms());
    }

    @Override
    public Observable<MainResponse> changePassword(String pwd) {
        return api.changePassword(Stamper.prepare(new ChangePasswordRequest(pwd))).compose(this.applyTransforms());
    }

    @Override
    public Observable<MainResponse> changePassword(String oldpwd, String newpwd) {
        return api.changePassword(Stamper.prepare(new ChangeAgentPasswordRequest(oldpwd, newpwd))).compose(this.applyTransforms());
    }


    //=========Customer Functional Services =======================================
    @Nullable
    @Override
    public Observable<CustomerBalanceResponse> execCustomerBalance(CustomerCardEntry cardEntry, LocationEntry locationEntry) {
        CustomerBalanceRequest request = Stamper.prepare(new CustomerBalanceRequest(cardEntry, locationEntry));
        return api.getCustomerBalance(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerBalanceSupplyResponse> supplyCustomerBalance(String transRef, CustomerBalanceSupplyRequest.DataResponse dataResponse) {
        CustomerBalanceSupplyRequest request = Stamper.prepare(new CustomerBalanceSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerBalance(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerBalanceCompleteResponse> completeCustomerBalance(String transRef, CustomerBalanceCompleteRequest.DataResponse dataResponse) {
        CustomerBalanceCompleteRequest request = Stamper.prepare(new CustomerBalanceCompleteRequest(transRef, dataResponse));
        return api.completeCustomerBalance(request)
                .compose(applyTransforms());
    }

    @Override
    public Observable<CustomerAcctToAcctTransferResponse> execCustomerAccToAccTransfer(CustomerCardEntry sourceCard, String targetCard, MoneyEntry operationAmount, String mode, LocationEntry location) {
        return api.getCustomerAcctToAcctTransfer(Stamper.prepare(new CustomerAcctToAcctTransferRequest(sourceCard, targetCard, operationAmount, mode, location)))
                .compose(applyTransforms());
    }

    @Override
    public Observable<CustomerAcctToAcctSupplyResponse> supplyCustomerAccToAccTransfer(CustomerAccountEntry sourceAccount, CustomerAccountEntry targetAccount,
                                                                                       String targetCard, MoneyEntry operationAmount, String transRef) {
        String sourceAccNumber = sourceAccount.getId();
        String targetAccNumber = null;
        if (targetAccount != null) {
            targetAccNumber = targetAccount.getId();
        }
        return api.getCustomerAcctToAcctSupply(Stamper.prepare(new CustomerAcctToAcctSupplyRequest(transRef,
                new CustomerAcctToAcctSupplyRequest.DataResponse(sourceAccNumber, targetAccNumber, targetCard, operationAmount))))
                .compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerAccToAccTransfer(String tranfRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return api.getCustomerAcctToAcctComplete(Stamper.prepare(new CustomerAcctToAcctCompleteRequest(tranfRef, dataResponse)))
                .compose(applyTransforms());
    }

    @Override
    public Observable<CustomerCashReceiveResponse> initCustomerCashReceive(String remittanceCode, String receiverPhoneNumber, MoneyEntry operationAmount, LocationEntry location) {
        return api.initCustomerCashReceive(Stamper.prepare(new CustomerCashReceiveRequest(remittanceCode, receiverPhoneNumber, operationAmount, location)))
                .compose(applyTransforms());
    }

    @Override
    public Observable<CustomerCashReceiveResponse> supplyCustomerCashReceive(String agentAccount, @Nullable MoneyEntry operationAmount, String transRef) {
        return api.supplyCustomerCashReceive(Stamper.prepare(new CustomerCashReceiveSupplyRequest(agentAccount, transRef)))
                .compose(applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCashReceive(@Nullable String agentPass, String transRef) {
        return api.completeCustomerCashReceive(Stamper.prepare(new CustomerCashReceiveCompleteRequest(agentPass, transRef)))
                .compose(applyTransforms());
    }

    @Override
    public Observable<CustomerCashWithdrawalResponse> getCustomerCashWithdrawal(CustomerCardEntry cardEntry, LocationEntry locationEntry) {
        CustomerCashWithdrawalRequest request = Stamper.prepare(new CustomerCashWithdrawalRequest(cardEntry));
        request.setLocation(locationEntry);
        return api.getCustomerCashWithdrawal(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerCashWithdrawalSupplyResponse> supplyCustomerCashWithdrawal(String transRef, CustomerCashWithdrawalSupplyRequest.ProvidedData data) {
        CustomerCashWithdrawalSupplyRequest request = Stamper.prepare(new CustomerCashWithdrawalSupplyRequest(transRef, data));
        return api.supplyCustomerCashWithdrawal(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCashWithdrawal(String transRef, OperationConfirmationProvidedDataEntry data) {
        CustomerCashWithdrawalCompleteRequest request = Stamper.prepare(new CustomerCashWithdrawalCompleteRequest(transRef, data));
        return api.completeCustomerCashWithdrawal(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerCashDepositResponse> getCustomerCashDeposit(CustomerCardEntry cardEntry, LocationEntry locationEntry) {
        CustomerCashDepositRequest request = Stamper.prepare(new CustomerCashDepositRequest(cardEntry));
        request.setLocation(locationEntry);
        return api.getCustomerCashDeposit(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerCashDepositSupplyResponse> supplyCustomerCashDeposit(String transRef, CustomerCashDepositSupplyRequest.ProvidedData data) {
        CustomerCashDepositSupplyRequest request = Stamper.prepare(new CustomerCashDepositSupplyRequest(transRef, data));
        return api.supplyCustomerCashDeposit(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCashDeposit(String transRef, OperationConfirmationProvidedDataEntry data) {
        CustomerCashDepositCompleteRequest request = Stamper.prepare(new CustomerCashDepositCompleteRequest(transRef, data));
        return api.completeCustomerCashDeposit(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<AgentAcctToAcctTransferResponse> getAgentAccountToAccountTransfer(String sourceId, String targetId, MoneyEntry amount, LocationEntry locationEntry) {
        AgentAcctToAcctTransferRequest request = Stamper.prepare(new AgentAcctToAcctTransferRequest(sourceId, targetId, amount, locationEntry));
        return api.getAgentAccountToAccountTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<AgentAcctToAcctTransferSupplyResponse> supplyAgentAccountToAccountTransfer(String transRef, AgentAcctToAcctTransferSupplyRequest.DataResponse data) {
        AgentAcctToAcctTransferSupplyRequest request = Stamper.prepare(new AgentAcctToAcctTransferSupplyRequest(transRef, data));
        return api.supplyAgentAccountToAccountTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeAgentAccountToAccountTransfer(String transRef, AgentAcctToAcctTransferCompleteRequest.DataResponse data) {
        AgentAcctToAcctTransferCompleteRequest request = Stamper.prepare(new AgentAcctToAcctTransferCompleteRequest(transRef, data));
        return api.completeAgentAccountToAccountTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<AgentStatisticsResponse> getAgentStatistics(Date dateFrom, Date dateTill, boolean details) {
        String start = DateHelper.formatDate(dateFrom);
        String end = DateHelper.formatDate(dateTill);

        AgentStatisticsRequest request = new AgentStatisticsRequest(start, end, details);
        String cacheTag = String.format("from=%s&to=%s&details=%d", start, end, details ? 1 : 0);
        return api.getAgentStatistics(request)
                .compose(this.applyTransforms());
        //.compose(this.applyCacheTransformer(AgentStatisticsResponse.class, cacheTag, 10*CacheTime.MINUTE));
    }

    @Override
    public Observable<CustomerAccountToCashTransferResponse> getCustomerAccountToCashTransfer(CustomerCardEntry sourceCard, MoneyEntry operationAmount, LocationEntry location) {
        CustomerAccountToCashTransferRequest request = Stamper.prepare(new CustomerAccountToCashTransferRequest(sourceCard, operationAmount, location));
        return api.getCustomerAccountToCashTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerAccountToCashTransferSupplyResponse> supplyCustomerAccountToCashTransfer(String transRef, CustomerAccountToCashTransferSupplyRequest.DataResponse dataResponse) {
        CustomerAccountToCashTransferSupplyRequest request = Stamper.prepare(new CustomerAccountToCashTransferSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerAccountToCashTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerAccountToCashTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        CustomerAccountToCashTransferCompleteRequest request = Stamper.prepare(new CustomerAccountToCashTransferCompleteRequest(transRef, dataResponse));
        return api.completeCustomerAccountToCashTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerCashToAccountTransferResponse> getCustomerCashToAccountTransfer(String targetCard, MoneyEntry operationAmount, LocationEntry location) {
        CustomerCashToAccountTransferRequest request = Stamper.prepare(new CustomerCashToAccountTransferRequest(targetCard, operationAmount, location));
        return api.getCustomerCashToAccountTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerCashToAccountTransferSupplyResponse> supplyCustomerCashToAccountTransfer(String transRef, CustomerCashToAccountTransferSupplyRequest.DataResponse dataResponse) {
        CustomerCashToAccountTransferSupplyRequest request = Stamper.prepare(new CustomerCashToAccountTransferSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerCashToAccountTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCashToAccountTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        CustomerCashToAccountTransferCompleteRequest request = Stamper.prepare(new CustomerCashToAccountTransferCompleteRequest(transRef, dataResponse));
        return api.completeCustomerCashToAccountTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerCashToCashTransferResponse> getCustomerCashToCashTransfer(String senderPhoneNumber, String receiverPhoneNumber, MoneyEntry operationAmount, LocationEntry location) {
        CustomerCashToCashTransferRequest request = Stamper.prepare(new CustomerCashToCashTransferRequest(senderPhoneNumber, receiverPhoneNumber, operationAmount, location));
        return api.getCustomerCashToCashTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerCashToCashTransferSupplyResponse> supplyCustomerCashToCashTransfer(String transRef, CustomerCashToCashTransferSupplyRequest.DataResponse dataResponse) {
        CustomerCashToCashTransferSupplyRequest request = Stamper.prepare(new CustomerCashToCashTransferSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerCashToCashTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCashToCashTransfer(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        CustomerCashToCashTransferCompleteRequest request = Stamper.prepare(new CustomerCashToCashTransferCompleteRequest(transRef, dataResponse));
        return api.completeCustomerCashToCashTransfer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerNewAccountResponse> getCustomerNewAccount(CustomerCardEntry cardEntry, LocationEntry location) {
        CustomerNewAccountRequest request = Stamper.prepare(new CustomerNewAccountRequest(cardEntry, location));
        return api.getCustomerNewAccount(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerNewAccountSupplyResponse> getCustomerNewAccount(String transRef, CustomerNewAccountSupplyRequest.DataResponse dataResponse) {
        CustomerNewAccountSupplyRequest request = Stamper.prepare(new CustomerNewAccountSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerNewAccount(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> getCustomerNewAccount(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        CustomerNewAccountCompleteRequest request = Stamper.prepare(new CustomerNewAccountCompleteRequest(transRef, dataResponse));
        return api.completeCustomerNewAccount(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerNewCardResponse> getCustomerNewCard(CustomerCardEntry cardEntry, LocationEntry location) {
        CustomerNewCardRequest request = Stamper.prepare(new CustomerNewCardRequest(cardEntry, location));
        return api.getCustomerNewCard(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerNewCardSupplyResponse> supplyCustomerNewCard(String transRef, CustomerNewCardSupplyRequest.DataResponse dataResponse) {
        CustomerNewCardSupplyRequest request = Stamper.prepare(new CustomerNewCardSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerNewCard(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerNewCardSupplyResponse> supplyCustomerNewCard2(String transRef, CustomerNewCardSupplyRequest.DataResponse dataResponse) {
        CustomerNewCardSupplyRequest request = Stamper.prepare(new CustomerNewCardSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerNewCard(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerNewCard(String transRef, CustomerNewCardCompleteRequest.DataResponse dataResponse) {
        CustomerNewCardCompleteRequest request = Stamper.prepare(new CustomerNewCardCompleteRequest(transRef, dataResponse));
        return api.completeCustomerNewCard(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<NewCustomerSupplyResponse> createNewCustomer(String transRef, NewCustomerSupplyRequest.DataResponse dataResponse) {
        NewCustomerSupplyRequest request = Stamper.prepare(new NewCustomerSupplyRequest(transRef, dataResponse));
        return api.createNewCustomer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<NewCustomerResponse> createNewCustomer(CustomerCardEntry newCard, LocationEntry location) {
        NewCustomerRequest request = Stamper.prepare(new NewCustomerRequest(newCard, location));
        return api.createNewCustomer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> createNewCustomer(String transRef, OperationConfirmationProvidedDataEntry data) {
        NewCustomerCompleteRequest request = Stamper.prepare(new NewCustomerCompleteRequest(transRef, data));
        return api.createNewCustomer(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerPaymentByCashResponse> getCustomerPaymentByCash(PaymentInfoEntry paymentInfo, LocationEntry location) {
        CustomerPaymentByCashRequest request = Stamper.prepare(new CustomerPaymentByCashRequest(paymentInfo, location));
        return api.getCustomerPaymentByCash(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerPaymentByCashSupplyResponse> supplyCustomerPaymentByCash(String transRef, CustomerPaymentByCashSupplyRequest.DataResponse dataResponse) {
        CustomerPaymentByCashSupplyRequest request = Stamper.prepare(new CustomerPaymentByCashSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerPaymentByCash(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerPaymentByCash(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        CustomerPaymentByCashCompleteRequest request = Stamper.prepare(new CustomerPaymentByCashCompleteRequest(transRef, dataResponse));
        return api.completeCustomerPaymentByCash(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerPaymentFromAccountResponse> getCustomerPaymentFromAccount(CustomerCardEntry card, LocationEntry location) {
        CustomerPaymentFromAccountRequest request = Stamper.prepare(new CustomerPaymentFromAccountRequest(card, location));
        return api.getCustomerPaymentFromAccount(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerPaymentFromAccountSupplyResponse> supplyCustomerPaymentFromAccount(String transRef, CustomerPaymentFromAccountSupplyRequest.DataResponse dataResponse) {
        CustomerPaymentFromAccountSupplyRequest request = Stamper.prepare(new CustomerPaymentFromAccountSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerPaymentFromAccount(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerPaymentFromAccount(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        CustomerPaymentFromAccountCompleteRequest request = Stamper.prepare(new CustomerPaymentFromAccountCompleteRequest(transRef, dataResponse));
        return api.completeCustomerPaymentFromAccount(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerCardPinChangeResponse> initCustomerCardPinChange(CustomerCardEntry card, LocationEntry location) {
        CustomerCardPinChangeRequest request = Stamper.prepare(new CustomerCardPinChangeRequest(card, location));
        return api.initCustomerCardPinChange(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerCardPinChangeSupplyResponse> supplyCustomerCardPinChange(String transRef, CustomerCardPinChangeSupplyRequest.DataResponse dataResponse) {
        CustomerCardPinChangeSupplyRequest request = Stamper.prepare(new CustomerCardPinChangeSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerCardPinChange(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerCardPinChange(String transRef) {
        CustomerCardPinChangeCompleteRequest request = Stamper.prepare(new CustomerCardPinChangeCompleteRequest(transRef));
        return api.completeCustomerCardPinChange(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerRepaymentCashToLoanResponse> initCustomerRepaymentCashToLoan(LocationEntry location, RepaymentInfoEntry repaymentInfo) {
        CustomerRepaymentCashToLoanRequest request = Stamper.prepare(new CustomerRepaymentCashToLoanRequest(repaymentInfo, location));
        return api.initCustomerRepaymentCashToLoan(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerRepaymentCashToLoanSupplyResponse> supplyCustomerRepaymentCashToLoan(String transRef, CustomerRepaymentCashToLoanSupplyRequest.DataResponse dataResponse) {
        CustomerRepaymentCashToLoanSupplyRequest request = Stamper.prepare(new CustomerRepaymentCashToLoanSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerRepaymentCashToLoan(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerRepaymentCashToLoan(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        CustomerRepaymentCashToLoanCompleteRequest request = Stamper.prepare(new CustomerRepaymentCashToLoanCompleteRequest(transRef, dataResponse));
        return api.completeCustomerRepaymentCashToLoan(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerRepaymentFromAccountResponse> initCustomerLoanPaymentFromAccount(CustomerCardEntry sourceCard, LocationEntry location) {
        CustomerRepaymentFromAccountRequest request = Stamper.prepare(new CustomerRepaymentFromAccountRequest(sourceCard, location));
        return api.initCustomerLoanPaymentFromAccount(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerRepaymentFromAccountSupplyResponse> supplyCustomerLoanPaymentFromAccount(String transRef, CustomerRepaymentFromAccountSupplyRequest.DataResponse dataResponse) {
        CustomerRepaymentFromAccountSupplyRequest request = Stamper.prepare(new CustomerRepaymentFromAccountSupplyRequest(transRef, dataResponse));
        return api.supplyCustomerLoanPaymentFromAccount(request)
                .compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerLoanPaymentFromAccount(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        CustomerRepaymentFromAccountCompleteRequest request = Stamper.prepare(new CustomerRepaymentFromAccountCompleteRequest(transRef, dataResponse));
        return api.completeCustomerLoanPaymentFromAccount(request)
                .compose(this.applyTransforms());
    }

    //=========Information Services =======================================
    @Override
    public Observable<RatesResponse> getRates() {
        return api.getRates().compose(this.applyTransforms());
    }

    @Override
    public Observable<CapabilitiesResponse> getServerCapabilities() {
        return api.getCapabilities().compose(this.applyTransforms());
    }

    @Override
    public Observable<ProfileResponse> getAgentProfile() {
        return api.getAgentProfile().compose(this.applyTransforms());
    }

    @Override
    public Observable<MainResponse> postDeviceInfo() {
        DeviceInfoRequest request = Stamper.prepare(new DeviceInfoRequest());
        return api.postDeviceInfo(request).compose(this.applyTransforms());
    }

    @Override
    public Observable<CardsResponse> getCards() {
        return api.getCards(Stamper.prepare(new CardRequest())).compose(this.applyTransforms());
    }

    @Override
    public Observable<AccountsResponse> getAccounts() {
        return api.getAccounts(Stamper.prepare(new AccountRequest())).compose(this.applyTransforms());
    }

    @Override
    public Observable<DevicesResponse> getDevices() {
        return api.getDevices(Stamper.prepare(new StampedRequest())).compose(this.applyTransforms());
    }

    @Override
    public Observable<MainResponse> deviceDetach(DeviceDetachRequest request) {
        return api.deviceDetach(Stamper.prepare(request)).compose(this.applyTransforms());
    }

    @Override
    public Observable<ImageUploadResponse> uploadImage(File imageFile) {
        RequestBody body = RequestBody.create(MediaType.parse("image/png"), imageFile);
        return api.uploadDataImage(body).compose(this.applyTransforms());
    }

    @Override
    public Observable<PaymentCategoriesResponse> getPaymentCategories(List<String> cids, String nameFilter, LocationEntry location) {
        PaymentCategoriesRequest request = Stamper.prepare(new PaymentCategoriesRequest(cids, nameFilter, location));
        return api.getPaymentCategories(request)
                .compose(this.applyCacheTransformer(PaymentCategoriesResponse.class, PaymentCategoriesResponse.class.getSimpleName(), 10 * CacheTime.MINUTE));
    }

    @Override
    public Observable<PaymentProvidersResponse> getPaymentProviders(List<String> pids, List<String> cids, String nameFilter, LocationEntry location) {
        PaymentProvidersRequest request = Stamper.prepare(new PaymentProvidersRequest(pids, cids, nameFilter, location));

        Observable.Transformer<? super PaymentProvidersResponse, ? extends PaymentProvidersResponse> transformer;
        transformer = TextUtils.isEmpty(nameFilter)
                ? this.applyCacheTransformer(PaymentProvidersResponse.class, PaymentProvidersResponse.class.getSimpleName(), 10 * CacheTime.MINUTE)
                : this.applyTransforms();

        return api.getPaymentProviders(request)
                .compose(transformer);
    }

    @Override
    public Observable<CustomerOperationsHistoryResponse> getCustomerOperationsHistory(CustomerCardEntry card, List<OperationInfoEntry.OperationType> operationTypes) {
        CustomerOperationsHistoryRequest request = Stamper.prepare(new CustomerOperationsHistoryRequest(card, operationTypes));
        return api.getCustomerOperationsHistory(request)
                .compose(this.applyCacheTransformer(CustomerOperationsHistoryResponse.class, CustomerOperationsHistoryResponse.class.getSimpleName(), 10 * CacheTime.MINUTE));
    }

    @Override
    public Observable<CardDetailResponse> getCard(String id) {
        return api.getCard(Stamper.prepare(new CardDetailRequest(id))).compose(this.applyTransforms());
    }

    @Override
    public Observable<AccountDetailResponse> getAccount(String id) {
        return api.getAccount(Stamper.prepare(new AccountDetailRequest(id))).compose(this.applyTransforms());
    }

    @Override
    public Observable<CardHistoryResponse> getCardHistory(String number, int page, String startDate, String endDate) {
        return api.getCardHistory(new CardHistoryRequest(number, page, startDate, endDate)).compose(this.applyTransforms());
    }

    @Override
    public Observable<AccountHistoryResponse> getAccountHistory(String id, int page, Date from, Date to) {
        return api.getAccountHistory(new AccountHistoryRequest(id, page, from, to)).compose(this.applyTransforms());
    }


    @Override
    public Observable<CustomerMinistatementResponse> initCustomerMinistatement(CustomerCardEntry card, LocationEntry locationEntry) {
        return api.initCustomerMinistatement(Stamper.prepare(new CustomerMinistatementRequest(card, locationEntry))).compose(this.applyTransforms());
    }

    @Override
    public Observable<CustomerMinistatementSupplyResponse> supplyCustomerMinistatement(String transRef, String sourceAccount) {
        return api.supplyCustomerMinistatement(Stamper.prepare(new CustomerMinistatementSupplyRequest(transRef, sourceAccount))).compose(this.applyTransforms());
    }

    @Override
    public Observable<OperationCompleteResponse> completeCustomerMinistatement(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        return api.completeCustomerMinistatemnt(Stamper.prepare(new CustomerCompleteRequest(transRef, dataResponse))).compose(this.applyTransforms());
    }


    @Override
    public Observable<MainResponse> cardRename(String id, String newName) {
        return api.cardChange(new CardChangeRequest(id, newName)).compose(this.applyTransforms());
    }

    @Override
    public Observable<MainResponse> getAccountRenameRequest(String id, String newName) {
        return api.accountChange(new AccountChangeRequest(id, newName)).compose(this.applyTransforms());
    }

    @Override
    public void removeAllDataFromCache() {
        webRequestCache.remove();
    }

    @Override
    public void removeDataFromCache(Class clazz) {
        webRequestCache.remove(clazz);
    }

    @Override
    public void removeDataFromCache(Class clazz, String cacheTag) {
        webRequestCache.remove(clazz, cacheTag);
    }

    private static <T extends CommonResponse> Observable<T> createGetCacheObservable(final Class<T> clazz, @Nullable final String cacheTag, final long expirationTime) {
        return Observable.create((Observable.OnSubscribe<T>) subscriber -> {
            subscriber.onNext(webRequestCache.get(clazz, cacheTag, expirationTime));
            subscriber.onCompleted();
        }).onErrorReturn(throwable -> {
            Log.e(TAG, "Cannot get from cache", throwable);
            return null;
        })
                .subscribeOn(Schedulers.from(webRequestsExecutor))
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static <T extends CommonResponse> Func1<T, Observable<T>> createReturnCacheFunction(final Observable<T> webRequestObservable) {
        return cachedResponse -> cachedResponse == null ? webRequestObservable : Observable.just(cachedResponse);
    }
}
