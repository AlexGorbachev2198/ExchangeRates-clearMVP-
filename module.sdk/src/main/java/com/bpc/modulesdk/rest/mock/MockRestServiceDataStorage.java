package com.bpc.modulesdk.rest.mock;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.bpc.modulesdk.rest.DtoObjectMapper;
import com.bpc.modulesdk.rest.dto.pojo.Account;
import com.bpc.modulesdk.rest.dto.pojo.AccountDetail;
import com.bpc.modulesdk.rest.dto.pojo.AccountOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.BooleanParameter;
import com.bpc.modulesdk.rest.dto.pojo.Card;
import com.bpc.modulesdk.rest.dto.pojo.CardDetail;
import com.bpc.modulesdk.rest.dto.pojo.CardOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.CardOrAccountParameter;
import com.bpc.modulesdk.rest.dto.pojo.Device;
import com.bpc.modulesdk.rest.dto.pojo.OperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.OperationStatus;
import com.bpc.modulesdk.rest.dto.pojo.RateInformation;
import com.bpc.modulesdk.rest.dto.pojo.SupportedCurrencyRecord;
import com.bpc.modulesdk.rest.dto.pojo.SupportedFunctionsRecord;
import com.bpc.modulesdk.rest.dto.pojo.SupportedRegionRecord;
import com.bpc.modulesdk.rest.dto.pojo.entries.CurrencyConversionInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerAccountEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.FeeEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;
import com.bpc.modulesdk.rest.dto.response.CustomerCashReceiveResponse;
import com.bpc.modulesdk.rest.dto.response.DevicesResponse;
import com.bpc.modulesdk.security.SecurityProviderFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Smolyaninov on 19.01.2017.
 */

public class MockRestServiceDataStorage {

    private static MockRestServiceDataStorage instance;

    private final String TAG = "MockDataStorage";

    private List<RateInformation> rates;
    private List<SupportedCurrencyRecord> supportedCurrencies;
    private List<SupportedFunctionsRecord> supportedFunctions;
    private List<SupportedRegionRecord> supportedRegions;
    private List<AccountDetail> accounts;
    private List<CardDetail> cards;
    private Map<String, List<OperationRecord>> cardsHistory;
    private Map<String, List<OperationRecord>> accountsHistory;
    private List<Device> devices;
    private List<CustomerAccountEntry> customerSourceAccounts;
    private List<CustomerAccountEntry> customerTargetAccounts;

    private CustomerCashReceiveResponse.RequiredData cashReceiveRequiredData;

    private OperationDetailsEntry cashReceiveOperationDetails = new OperationDetailsEntry();

    public static MockRestServiceDataStorage getInstance() {
        if (instance == null) {
            instance = new MockRestServiceDataStorage();
        }
        return instance;
    }

    public OperationDetailsEntry initCashReceiveOperationDetails(CustomerAccountEntry target, MoneyEntry entry) {
        this.cashReceiveOperationDetails.setOperationAmount(entry);
        this.cashReceiveOperationDetails.setConversionInfo(createCashReceiveConversionRecord(
                target,
                entry
        ));
        this.cashReceiveOperationDetails.setFeeInfo(createFeeInfo(entry));
        this.cashReceiveOperationDetails.setTotalAmount(createTotalAmount(
                this.cashReceiveOperationDetails.getFeeInfo(), entry
        ));

        return this.cashReceiveOperationDetails;
    }

    public OperationDetailsEntry initOperationDetails(OperationDetailsEntry operationDetails) {
        operationDetails.setConversionInfo(
                createConversionRecord(
                        operationDetails.getSourceAccount(),
                        operationDetails.getTargetAccount(),
                        operationDetails.getOperationAmount())
        );
        operationDetails.setFeeInfo(createFeeInfo(operationDetails.getOperationAmount()));
        operationDetails.setTotalAmount(createTotalAmount(operationDetails.getFeeInfo(), operationDetails.getOperationAmount()));
        return operationDetails;
    }

    private CurrencyConversionInfoEntry createConversionRecord(CustomerAccountEntry source, CustomerAccountEntry target, MoneyEntry amount) {
        CurrencyConversionInfoEntry conversionRecord = new CurrencyConversionInfoEntry();
        conversionRecord.setForwardRate(1.1f);//TODO algorithm
        conversionRecord.setBackwardRate(0.9f);//TODO algorithm

        if (target != null) {
            if (!source.getCurrency().equals(target.getCurrency())) {

                BigDecimal sum = amount.getAmount();
                BigDecimal afterConversion = sum.multiply(BigDecimal.valueOf(conversionRecord.getForwardRate()));
                MoneyEntry targetEntry = new MoneyEntry();
                targetEntry.setCurrency(target.getCurrency());
                targetEntry.setAmount(afterConversion);

                conversionRecord.setSourceFunds(amount);
                conversionRecord.setTargetFunds(targetEntry);

                return conversionRecord;
            } else return null;
        } else {
            if (!source.getCurrency().equals(amount.getCurrency())) {
                BigDecimal sum = amount.getAmount();
                BigDecimal afterConversion = sum.multiply(BigDecimal.valueOf(conversionRecord.getForwardRate()));
                MoneyEntry targetEntry = new MoneyEntry();
                targetEntry.setCurrency(amount.getCurrency());
                targetEntry.setAmount(afterConversion);

                conversionRecord.setSourceFunds(amount);
                conversionRecord.setTargetFunds(targetEntry);

                return conversionRecord;
            } else return null;
        }
    }


    private CurrencyConversionInfoEntry createCashReceiveConversionRecord(CustomerAccountEntry target, MoneyEntry amount) {
        CurrencyConversionInfoEntry conversionRecord = new CurrencyConversionInfoEntry();
        conversionRecord.setForwardRate(1.1f);//TODO algorithm
        conversionRecord.setBackwardRate(0.9f);//TODO algorithm

        if (amount.getCurrency() != null && target.getCurrency() != null && !amount.getCurrency().equals(target.getCurrency())) {
            BigDecimal sum = amount.getAmount();
            BigDecimal afterConversion = sum.multiply(BigDecimal.valueOf(conversionRecord.getForwardRate()));
            MoneyEntry targetEntry = new MoneyEntry();
            targetEntry.setCurrency(target.getCurrency());
            targetEntry.setAmount(afterConversion);

            conversionRecord.setSourceFunds(amount);
            conversionRecord.setTargetFunds(targetEntry);

            return conversionRecord;
        } else {
            return null;
        }
    }

    private MoneyEntry createTotalAmount(List<FeeEntry> fees, MoneyEntry sourceFunds) {

        MoneyEntry totalFee = new MoneyEntry();
        totalFee.setCurrency(sourceFunds.getCurrency());

        BigDecimal total = new BigDecimal(0);
        for (FeeEntry fee : fees) {
            total = total.add(fee.getFeeAmount().getAmount());
        }
        totalFee.setAmount(total.add(sourceFunds.getAmount()));

        return totalFee;
    }

    private List<FeeEntry> createFeeInfo(MoneyEntry amount) {

        List<FeeEntry> feeInfo = new ArrayList<>();

        FeeEntry entry1 = new FeeEntry();
        entry1.setDescription("Tax1");
        MoneyEntry fee1 = new MoneyEntry();
        fee1.setCurrency(amount.getCurrency());
        fee1.setAmount(BigDecimal.valueOf(0.3));
        entry1.setFeeAmount(fee1);
        feeInfo.add(entry1);

        FeeEntry entry2 = new FeeEntry();
        entry2.setDescription("Tax2");
        MoneyEntry fee2 = new MoneyEntry();
        fee2.setCurrency(amount.getCurrency());
        fee2.setAmount(BigDecimal.valueOf(0.93));
        entry2.setFeeAmount(fee2);
        feeInfo.add(entry2);

        return feeInfo;
    }


    private MockRestServiceDataStorage() {
        initRates();
        initRegions();
        initSupportedCurrencies();
        initSupportedFunctions();
        initAccounts();
        initCards();
        initCardsHistory();
        initAccountsHistory();
        initDevices();

        initCustomerSourceAccounts();
        initCustomerTargetAccounts();
        initCashReceiveRequiredData();
    }

    public CustomerCashReceiveResponse.RequiredData getCashReceiveRequiredData() {
        return cashReceiveRequiredData;
    }

    public List<RateInformation> getRates() {
        return rates;
    }

    public List<SupportedCurrencyRecord> getSupportedCurrencies() {
        return supportedCurrencies;
    }

    public List<SupportedFunctionsRecord> getSupportedFunctions() {
        return supportedFunctions;
    }

    public List<SupportedRegionRecord> getSupportedRegions() {
        return supportedRegions;
    }

    public List<Account> getAccounts() {
        return new ArrayList<Account>(accounts);
    }

    public List<CustomerAccountEntry> getCustomerSourceAccounts() {
        return customerSourceAccounts;
    }

    public List<CustomerAccountEntry> getCustomerTargetAccounts() {
        return customerTargetAccounts;
    }

    public List<Card> getCards() {
        return new ArrayList<Card>(cards);
    }

    public CardDetail getCard(String id) {
        if (id == null)
            return null;
        for (CardDetail card : cards)
            if (id.equals(card.getId()))
                return card;
        return null;
    }

    public AccountDetail getAccount(String id) {
        if (id == null)
            return null;
        for (AccountDetail account : accounts)
            if (id.equals(account.getId()))
                return account;
        return null;
    }

    public void changeCard(String scid, String cardName, Card.Status status, Card.Active activate, Integer securityFlag) {
        if (scid == null)
            return;
        CardDetail editableCard = null;
        for (CardDetail cardDetail : cards) {
            if (scid.equals(cardDetail.getId())) {
                editableCard = cardDetail;
                break;
            }
        }
        if (editableCard == null)
            return;

        if (cardName != null)
            editableCard.setName(cardName);
        if (status != null)
            editableCard.setStatus(status);
        if (activate != null)
            editableCard.setActive(activate);
        if (securityFlag != null) {
            int size = editableCard.getDetails().size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    CardOrAccountParameter param = editableCard.getDetails().get(i);
                    if (param.getStereotype().equals("card.securityFlags")) {
                        ((BooleanParameter) param).setValue(securityFlag == 1);
                        break;
                    }
                }
            }
        }
    }

    public void changeAccount(String scid, String accountName, Account.Status status, Account.Active activate, Integer securityFlag) {
        if (scid == null)
            return;
        AccountDetail editableAccount = null;
        for (AccountDetail accountDetail : accounts) {
            if (scid.equals(accountDetail.getId())) {
                editableAccount = accountDetail;
                break;
            }
        }
        if (editableAccount == null)
            return;

        if (accountName != null)
            editableAccount.setName(accountName);
        if (status != null)
            editableAccount.setStatus(status);
        if (activate != null)
            editableAccount.setActive(activate);
        if (securityFlag != null) {
            int size = editableAccount.getDetails().size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    CardOrAccountParameter param = editableAccount.getDetails().get(i);
                    if (param.getStereotype().equals("account.securityFlags")) {
                        ((BooleanParameter) param).setValue(securityFlag == 1);
                        break;
                    }
                }
            }
        }
    }

    public List<? extends OperationRecord> getCardHistory(String id) {
        return cardsHistory.get(id);
    }

    public List<? extends OperationRecord> getAccountHistory(String id) {
        return accountsHistory.get(id);
    }

    public List<Device> getDevices() {
        return new ArrayList<Device>(devices);
    }

    public void deviceDetach(List<String> devids) {
        List<Integer> positions = new ArrayList<>(devids.size());
        for (int i = 0; i < devices.size(); i++)
            if (devids.contains(devices.get(i).getDevid()))
                positions.add(i);
        for (int i = positions.size() - 1; i >= 0; i--)
            devices.remove(i);

    }


    private void initCashReceiveRequiredData() {
        cashReceiveRequiredData = new CustomerCashReceiveResponse.RequiredData();
        cashReceiveRequiredData.setReceiverPhoneNumber(true);
        cashReceiveRequiredData.setSelectAgentAccount(this.getCustomerTargetAccounts());
    }


    private void initAccounts() {
        if (accounts == null) accounts = new ArrayList<>();

        AccountDetail detail = new AccountDetail();
        detail.setActive(Account.Active.YES);
        detail.setBalance(BigDecimal.valueOf(1000));
        detail.setCurrency("RUB");
        detail.setId("99");
        detail.setName("Account name 1");
        detail.setNumber("455555******7777");
        detail.setStatus(Account.Status.VALID);
        detail.setType(Account.Type.CREDIT);
        accounts.add(detail);

        AccountDetail detail2 = new AccountDetail();
        detail2.setActive(Account.Active.YES);
        detail2.setBalance(BigDecimal.valueOf(5000));
        detail2.setCurrency("USD");
        detail2.setId("14");
        detail2.setName("Account name 2");
        detail2.setNumber("455555******8888");
        detail2.setStatus(Account.Status.PERM_BLOCK);
        detail2.setType(Account.Type.CHECKING);
        accounts.add(detail2);

        AccountDetail detail3 = new AccountDetail();
        detail3.setActive(Account.Active.YES);
        detail3.setBalance(BigDecimal.valueOf(15000));
        detail3.setCurrency("RUB");
        detail3.setId("18");
        detail3.setName("Account name 3");
        detail3.setNumber("455555******8889");
        detail3.setStatus(Account.Status.VALID);
        detail3.setType(Account.Type.DEPOSIT);
        accounts.add(detail3);

        AccountDetail detail4 = new AccountDetail();
        detail4.setActive(Account.Active.YES);
        detail4.setBalance(BigDecimal.valueOf(25000));
        detail4.setCurrency("USD");
        detail4.setId("88");
        detail4.setName("Account name 4");
        detail4.setNumber("455555******9999");
        detail4.setStatus(Account.Status.VALID);
        detail4.setType(Account.Type.DEPOSIT);
        accounts.add(detail4);
    }

    private void initCustomerSourceAccounts() {
        customerSourceAccounts = new ArrayList<>();

        CustomerAccountEntry acc1 = new CustomerAccountEntry();
        acc1.setCurrency("USD");
        acc1.setId("9999");
        acc1.setName("Name1");
        acc1.setNumber("1111 1111 1111 1111");
        acc1.setType(CustomerAccountEntry.Type.CHECKING);
        MoneyEntry entry1 = new MoneyEntry(acc1.getCurrency(), BigDecimal.valueOf(10000));
        acc1.setAvailableBalance(entry1);

        customerSourceAccounts.add(acc1);

        CustomerAccountEntry acc2 = new CustomerAccountEntry();
        acc2.setCurrency("EUR");
        acc2.setId("8888");
        acc2.setName("Name2");
        acc2.setNumber("2222 2222 2222 2222");
        acc2.setType(CustomerAccountEntry.Type.CHECKING);
        MoneyEntry entry2 = new MoneyEntry(acc2.getCurrency(), BigDecimal.valueOf(999999));
        acc2.setAvailableBalance(entry2);

        customerSourceAccounts.add(acc2);

        CustomerAccountEntry acc3 = new CustomerAccountEntry();
        acc3.setCurrency("RUB");
        acc3.setId("7777");
        acc3.setName("Name3");
        acc3.setNumber("3333 3333 3333 3333");
        acc3.setType(CustomerAccountEntry.Type.DEPOSIT);

        MoneyEntry entry3 = new MoneyEntry(acc3.getCurrency(), BigDecimal.valueOf(555));
        acc3.setAvailableBalance(entry3);

        customerSourceAccounts.add(acc3);

    }

    private void initCustomerTargetAccounts() {
        customerTargetAccounts = new ArrayList<>();
        if (customerSourceAccounts != null) {
            customerTargetAccounts.addAll(customerSourceAccounts);
        }

        CustomerAccountEntry acc3 = new CustomerAccountEntry();
        acc3.setCurrency("RUB");
        acc3.setId("6666");
        acc3.setName("Name5");
        acc3.setNumber("5555 5555 5555 5555");
        acc3.setType(CustomerAccountEntry.Type.CREDIT);

        MoneyEntry entry3 = new MoneyEntry(acc3.getCurrency(), BigDecimal.valueOf(1488));
        acc3.setAvailableBalance(entry3);

        customerTargetAccounts.add(acc3);
    }

    private void initRates() {
        if (rates == null) {
            rates = new ArrayList<>();
        }
        RateInformation ri1 = new RateInformation();
        ri1.setName("USD/RUR");
        ri1.setOrder(1);
        ri1.setBase(BigDecimal.valueOf(55.33));
        ri1.setBuy(BigDecimal.valueOf(55.02));
        ri1.setSell(BigDecimal.valueOf(55.66));

        rates.add(ri1);

        RateInformation ri2 = new RateInformation();
        ri2.setName("EUR/RUR");
        ri2.setOrder(2);
        ri2.setBase(BigDecimal.valueOf(66.66));
        ri2.setBuy(BigDecimal.valueOf(66.33));
        ri2.setSell(BigDecimal.valueOf(66.99));

        rates.add(ri2);

        /*RateInformation ri3 = new RateInformation();
        ri3.setName("EUR/USD");
        ri3.setOrder(3);
        ri3.setBase(BigDecimal.valueOf(1.23));
        ri3.setBuy(BigDecimal.valueOf(1.13));
        ri3.setSell(BigDecimal.valueOf(1.33));

        rates.add(ri3);*/
    }

    private void initRegions() {
        if (supportedRegions == null) {
            supportedRegions = new ArrayList<>();
        }
        SupportedRegionRecord record1 = new SupportedRegionRecord();
        record1.setRid("300");
        record1.setName("Moscow");
        record1.setName("Moscow and Voskresensk");

        SupportedRegionRecord record2 = new SupportedRegionRecord();
        record2.setRid("200");
        record2.setName("SPB");
        record2.setDescription("Piter");

        SupportedRegionRecord record3 = new SupportedRegionRecord();
        record3.setRid("100");
        record3.setName("Orel");
        record3.setDescription("Orel Orel Orel");
        supportedRegions.add(record1);
        supportedRegions.add(record2);
        supportedRegions.add(record3);
    }

    private void initSupportedCurrencies() {

        if (supportedCurrencies == null) {
            supportedCurrencies = new ArrayList<>();
        }

        SupportedCurrencyRecord record1 = new SupportedCurrencyRecord();
        record1.setNumCode(978);
        record1.setSymCode("EUR");
        record1.setDecimal(2);
        record1.setDescription("Euro");

        SupportedCurrencyRecord record2 = new SupportedCurrencyRecord();
        record2.setNumCode(840);
        record2.setSymCode("USD");
        record2.setDecimal(2);
        record2.setDescription("Dollar");

        SupportedCurrencyRecord record3 = new SupportedCurrencyRecord();
        record3.setNumCode(643);
        record3.setSymCode("RUB");
        record3.setDecimal(2);
        record3.setDescription("Ruble");

        supportedCurrencies.add(record1);
        supportedCurrencies.add(record2);
        supportedCurrencies.add(record3);

    }

    private void initSupportedFunctions() {
        if (supportedFunctions == null) {
            supportedFunctions = new ArrayList<>();
        }
        SupportedFunctionsRecord record = new SupportedFunctionsRecord();
        record.setBalanceInquiry(1);
        record.setCashDeposit(1);
        record.setCashWithdrawal(1);
        supportedFunctions.add(record);
    }

    private void initCards() {
        cards = new ArrayList<>();

        CardDetail card = new CardDetail();
        card.setBalance(new BigDecimal(206206400));
        card.setBrand("VISA_CLASSIC");
        card.setCurrency("RUB");
        card.setExpDate("201809");
        card.setNumber("455555******0064");
        card.setId("64");
        card.setActive(Card.Active.YES);
        card.setStatus(Card.Status.VALID);
        card.setType(Card.Type.CREDIT);

        card.setLinkedAccounts(Arrays.asList("40817810900001120760", "40817810900001120761", "832946289364289364"));

        /*List<CardOrAccountParameter> list = new ArrayList<>();
        BooleanParameter p = new BooleanParameter();
        p.setValue(true);
        p.setId("card.securityFlags");
        p.setStereotype("card.securityFlags");
        list.add(p);
        card.setDetails(list);
        //Todo new card response
        card.setHold(new BigDecimal(0));
        card.setCredit(new BigDecimal(5000));
        card.setUsedCredit(new BigDecimal(0));*/
        cards.add(card);

        card = new CardDetail();
        card.setBalance(new BigDecimal(206206230100f));
        card.setBrand("MASTERCARD_GOLD");
        card.setCurrency("RUB");
        card.setExpDate("201809");
        card.setNumber("555555******0065");
        card.setId("5QHcBkbmHC8b FMGdf0J1rzCAzRRh/xLxg3Foue/G80=");
        card.setActive(Card.Active.NO);
        card.setStatus(Card.Status.VALID);
        card.setType(Card.Type.CREDIT);
        card.setLinkedAccounts(Collections.singletonList("832946289364289364"));
        //Todo new card response
        /*card.setHold(new BigDecimal(0));
        card.setCredit(new BigDecimal(5000));
        card.setUsedCredit(new BigDecimal(0));*/
        cards.add(card);

        card = new CardDetail();
        card.setBalance(new BigDecimal(206206206139400f));
        card.setBrand("MASTERCARD_GPN");
        card.setCurrency("EUR");
        card.setExpDate("201809");
        card.setNumber("555555******0066");
        card.setId("66");
        card.setActive(Card.Active.NO);
        card.setStatus(Card.Status.VALID);
        card.setType(Card.Type.DEBIT);
        //Todo new card response
/*        card.setHold(new BigDecimal(0));
        card.setCredit(new BigDecimal(5000));
        card.setUsedCredit(new BigDecimal(0));*/
        cards.add(card);

        card = new CardDetail();
        card.setBalance(new BigDecimal(206206206206206202800f));
        card.setBrand("VISA_TM");
        card.setCurrency("USD");
        card.setExpDate("201809");
        card.setNumber("455555******0067");
        card.setId("67");
        card.setActive(Card.Active.NO);
        card.setStatus(Card.Status.VALID);
        card.setType(Card.Type.DEBIT);
        //Todo new card response

/*        card.setHold(new BigDecimal(0));
        card.setCredit(new BigDecimal(5000));
        card.setUsedCredit(new BigDecimal(0));*/
        cards.add(card);

        card = new CardDetail();
        card.setBalance(new BigDecimal(800));
        card.setBrand("MASTERCARD_STANDARD");
        card.setCurrency("USD");
        card.setExpDate("201809");
        card.setNumber("555555******0068");
        card.setId("68");
        card.setActive(Card.Active.YES);
        card.setStatus(Card.Status.VALID);
        card.setType(Card.Type.DEBIT);
        //Todo new card response

/*        card.setHold(new BigDecimal(0));
        card.setCredit(new BigDecimal(5000));
        card.setUsedCredit(new BigDecimal(0));*/
        cards.add(card);

        card = new CardDetail();
        card.setBalance(new BigDecimal(0));
        card.setBrand("UNIONPAY_GLAD");
        card.setCurrency("USD");
        card.setExpDate("201809");
        card.setNumber("355555******0069");
        card.setId("69");
        card.setActive(Card.Active.YES);
        card.setStatus(Card.Status.VALID);
        card.setType(Card.Type.CREDIT);
        //Todo new card response

/*        card.setHold(new BigDecimal(0));
        card.setCredit(new BigDecimal(5000));
        card.setUsedCredit(new BigDecimal(0));*/
        cards.add(card);

        card = new CardDetail();
        card.setBalance(new BigDecimal(2));
        card.setBrand("MASTERCARD_GOLD");
        card.setCurrency("USD");
        card.setExpDate("201809");
        card.setNumber("555555******0070");
        card.setId("70");
        card.setActive(Card.Active.YES);
        card.setStatus(Card.Status.EXPIRED);
        card.setType(Card.Type.DEBIT);
        //Todo new card response

/*        card.setHold(new BigDecimal(0));
        card.setCredit(new BigDecimal(5000));
        card.setUsedCredit(new BigDecimal(0));*/
        cards.add(card);

        card = new CardDetail();
        card.setBalance(new BigDecimal(2800));
        card.setBrand("MASTERCARD_GOLD");
        card.setCurrency("USD");
        card.setExpDate("201809");
        card.setNumber("555555******0170");
        card.setId("170");
        card.setActive(Card.Active.YES);
        card.setStatus(Card.Status.VALID);
        card.setType(Card.Type.DEBIT);
        card.setCategory(Card.Category.VIRTUAL);
        //Todo new card response

/*        card.setHold(new BigDecimal(0));
        card.setCredit(new BigDecimal(5000));
        card.setUsedCredit(new BigDecimal(0));*/
        cards.add(card);

        card = new CardDetail();
        card.setBalance(new BigDecimal(202800));
        card.setBrand("VISA_CLASSIC");
        card.setCurrency("USD");
        card.setExpDate("201809");
        card.setNumber("555555******0171");
        card.setId("171");
        card.setActive(Card.Active.YES);
        card.setStatus(Card.Status.EXPIRED);
        card.setType(Card.Type.DEBIT);
        card.setCategory(Card.Category.VIRTUAL);
        //Todo new card response

/*        card.setHold(new BigDecimal(0));
        card.setCredit(new BigDecimal(5000));
        card.setUsedCredit(new BigDecimal(0));*/
        cards.add(card);
    }

    private void initCardsHistory() {
        cardsHistory = new ArrayMap<>(cards.size());
        for (CardDetail card : cards)
            cardsHistory.put(card.getId(), createCardOperationRecordList());
    }

    private void initAccountsHistory() {
        accountsHistory = new ArrayMap<>(accounts.size());
        for (AccountDetail account : accounts)
            accountsHistory.put(account.getId(), createAccountOperationRecordList());
    }

    private void initDevices() {
        try {
            DevicesResponse response = DtoObjectMapper.create().readValue(JsonStore.DEVICES, DevicesResponse.class);
            devices = response.getDevices();
            if (devices != null && !devices.isEmpty())
                devices.get(0).setDevid(SecurityProviderFactory.getProvider().getDeviceId());
        } catch (IOException e) {
            Log.e(TAG, "initDevices: ", e);
        }
    }

    private List<OperationRecord> createCardOperationRecordList() {
        List<OperationRecord> list = new ArrayList<>();

        CardOperationRecord operationRecord = new CardOperationRecord();
        operationRecord.setAmount(new BigDecimal(-600));
        operationRecord.setCurrency("RUB");
        operationRecord.setDescription("Transferring");
        operationRecord.setFeeAmount(new BigDecimal(0));
        operationRecord.setFeeCurrency("RUB");
        operationRecord.setTid("2721");
        operationRecord.setTimestamp("20150927023952");
        operationRecord.setType("Transfer");
        List<OperationStatus> operationStatuses = new ArrayList<>();
        OperationStatus status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("2");
        status.setCancellable(false);
        status.setTimestamp("20150927024052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("1");
        status.setCancellable(true);
        status.setTimestamp("20150927023952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);
        list.add(operationRecord);

        operationRecord = new CardOperationRecord();
        operationRecord.setAmount(new BigDecimal(1800));
        operationRecord.setCurrency("RUB");
        operationRecord.setDescription("Transferring");
        operationRecord.setFeeAmount(new BigDecimal(0));
        operationRecord.setFeeCurrency("RUB");
        operationRecord.setTid("2720");
        operationRecord.setTimestamp("20150926023951");
        operationRecord.setType("Transfer");
        operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.REJECTED);
        status.setId("4");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("3");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);
        list.add(operationRecord);

        operationRecord = new CardOperationRecord();
        operationRecord.setAmount(new BigDecimal(-400));
        operationRecord.setCurrency("RUB");
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new BigDecimal(0));
        operationRecord.setFeeCurrency("RUB");
        operationRecord.setTid("2719");
        operationRecord.setTimestamp("20150925023952");
        operationRecord.setType("Payment");
        operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("5");
        status.setCancellable(true);
        status.setTimestamp("20150925024252");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);
        list.add(operationRecord);

        operationRecord = new CardOperationRecord();
        operationRecord.setAmount(new BigDecimal(500));
        operationRecord.setCurrency("RUB");
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new BigDecimal(0));
        operationRecord.setFeeCurrency("RUB");
        operationRecord.setTid("2718");
        operationRecord.setTimestamp("20150924023952");
        operationRecord.setType("Payment");
        operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.CANCELLED);
        status.setId("7");
        status.setCancellable(false);
        status.setTimestamp("20150927034252");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("6");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);
        list.add(operationRecord);

        operationRecord = new CardOperationRecord();
        operationRecord.setAmount(new BigDecimal(600));
        operationRecord.setCurrency("RUB");
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new BigDecimal(0));
        operationRecord.setFeeCurrency("RUB");
        operationRecord.setTid("2717");
        operationRecord.setTimestamp("20150923023952");
        operationRecord.setType("Payment");
        operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("9");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("8");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);
        list.add(operationRecord);

        operationRecord = new CardOperationRecord();
        operationRecord.setAmount(new BigDecimal(600));
        operationRecord.setCurrency("RUB");
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new BigDecimal(0));
        operationRecord.setFeeCurrency("RUB");
        operationRecord.setTid("2716");
        operationRecord.setTimestamp("20150923023952");
        operationRecord.setType("Payment");
        operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("9");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("8");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);
        list.add(operationRecord);

        operationRecord = new CardOperationRecord();
        operationRecord.setAmount(new BigDecimal(600));
        operationRecord.setCurrency("RUB");
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new BigDecimal(0));
        operationRecord.setFeeCurrency("RUB");
        operationRecord.setTid("2715");
        operationRecord.setTimestamp("20150923023952");
        operationRecord.setType("Payment");
        operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("9");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("8");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);
        list.add(operationRecord);


        operationRecord = new CardOperationRecord();
        operationRecord.setAmount(new BigDecimal(600));
        operationRecord.setCurrency("RUB");
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new BigDecimal(0));
        operationRecord.setFeeCurrency("RUB");
        operationRecord.setTid("2714");
        operationRecord.setTimestamp("20150923023952");
        operationRecord.setType("Payment");
        operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("9");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("8");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);
        list.add(operationRecord);

        operationRecord = new CardOperationRecord();
        operationRecord.setAmount(new BigDecimal(600));
        operationRecord.setCurrency("RUB");
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new BigDecimal(0));
        operationRecord.setFeeCurrency("RUB");
        operationRecord.setTid("2713");
        operationRecord.setTimestamp("20150923023952");
        operationRecord.setType("Payment");
        operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("9");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("8");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);
        list.add(operationRecord);

        return list;
    }

    private List<OperationRecord> createAccountOperationRecordList() {
        List<OperationRecord> list = new ArrayList<>();

        AccountOperationRecord operationRecord = new AccountOperationRecord();
        operationRecord.setOperationAmount(new MoneyEntry("RUB", new BigDecimal(-600)));
        operationRecord.setDescription("Transferring");
        operationRecord.setFeeAmount(new MoneyEntry("RUB", new BigDecimal(0)));
        operationRecord.setTid("2721");
        operationRecord.setTimestamp("2016-05-01T10:00:00.500Z");
        operationRecord.setType("Transfer");
        /*List<OperationStatus> operationStatuses = new ArrayList<>();
        OperationStatus status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("2");
        status.setCancellable(false);
        status.setTimestamp("20150927024052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("1");
        status.setCancellable(true);
        status.setTimestamp("20150927023952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);*/
        operationRecord.setExecutionStatus(OperationStatus.Type.PROCESSING.toString());
        list.add(operationRecord);

        operationRecord = new AccountOperationRecord();
        operationRecord.setOperationAmount(new MoneyEntry("RUB",new BigDecimal(1800)));
        operationRecord.setDescription("Transferring");
        operationRecord.setFeeAmount(new MoneyEntry("RUB", new BigDecimal(0)));
        operationRecord.setTid("2720");
        operationRecord.setTimestamp("2016-05-01T14:00:00.500Z");
        operationRecord.setType("Transfer");
        /*operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.REJECTED);
        status.setId("4");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("3");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);*/
        operationRecord.setExecutionStatus(OperationStatus.Type.PROCESSING.toString());
        list.add(operationRecord);

        operationRecord = new AccountOperationRecord();
        operationRecord.setOperationAmount(new MoneyEntry("RUB",new BigDecimal(-400)));
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new MoneyEntry("RUB", new BigDecimal(0)));
        operationRecord.setTid("2719");
        operationRecord.setTimestamp("2016-05-02T11:00:00.500Z");
        operationRecord.setType("Payment");
        /*operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("5");
        status.setCancellable(true);
        status.setTimestamp("2016-05-02T12:00:00.500Z");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);*/
        operationRecord.setExecutionStatus(OperationStatus.Type.PROCESSING.toString());
        list.add(operationRecord);

        operationRecord = new AccountOperationRecord();
        operationRecord.setOperationAmount(new MoneyEntry("RUB", new BigDecimal(500)));
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new MoneyEntry("RUB", new BigDecimal(0)));
        operationRecord.setTid("2718");
        operationRecord.setTimestamp("2016-05-02T13:00:00.500Z");
        operationRecord.setType("Payment");
        /*operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.CANCELLED);
        status.setId("7");
        status.setCancellable(false);
        status.setTimestamp("2016-05-02T14:00:00.500Z");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("6");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);*/
        operationRecord.setExecutionStatus(OperationStatus.Type.PROCESSING.toString());
        list.add(operationRecord);

        operationRecord = new AccountOperationRecord();
        operationRecord.setOperationAmount(new MoneyEntry("RUB", new BigDecimal(600)));
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new MoneyEntry("RUB", new BigDecimal(0)));
        operationRecord.setTid("2717");
        operationRecord.setTimestamp("2016-05-02T15:20:00.500Z");
        operationRecord.setType("Payment");
        /*operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("9");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("8");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);*/
        operationRecord.setExecutionStatus(OperationStatus.Type.PROCESSING.toString());
        list.add(operationRecord);

        operationRecord = new AccountOperationRecord();
        operationRecord.setOperationAmount(new MoneyEntry("RUB", new BigDecimal(600)));
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new MoneyEntry("RUB", new BigDecimal(0)));
        operationRecord.setTid("2716");
        operationRecord.setTimestamp("2016-05-02T10:00:33.500Z");
        operationRecord.setType("Payment");
        /*operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("9");
        status.setCancellable(false);
        status.setTimestamp("2016-05-02T17:00:00.500Z");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("8");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);*/
        operationRecord.setExecutionStatus(OperationStatus.Type.COMPLETED.toString());
        list.add(operationRecord);

        operationRecord = new AccountOperationRecord();
        operationRecord.setOperationAmount(new MoneyEntry("RUB", new BigDecimal(600)));
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new MoneyEntry("RUB", new BigDecimal(0)));
        operationRecord.setTid("2715");
        operationRecord.setTimestamp("2016-06-02T11:00:00.500Z");
        operationRecord.setType("Payment");
        /*operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("9");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("8");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);*/
        operationRecord.setExecutionStatus(OperationStatus.Type.PROCESSING.toString());
        list.add(operationRecord);


        operationRecord = new AccountOperationRecord();
        operationRecord.setOperationAmount(new MoneyEntry("RUB", new BigDecimal(600)));
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new MoneyEntry("RUB", new BigDecimal(0)));
        operationRecord.setTid("2714");
        operationRecord.setTimestamp("2013-05-02T11:00:00.500Z");
        operationRecord.setType("Payment");
        /*operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("9");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("8");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);*/
        operationRecord.setExecutionStatus(OperationStatus.Type.COMPLETED.toString());
        list.add(operationRecord);

        operationRecord = new AccountOperationRecord();
        operationRecord.setOperationAmount(new MoneyEntry("RUB", new BigDecimal(600)));
        operationRecord.setDescription("Payment to Internet Provider");
        operationRecord.setFeeAmount(new MoneyEntry("RUB", new BigDecimal(0)));
        operationRecord.setTid("2713");
        operationRecord.setTimestamp("2017-05-02T11:00:00.500Z");
        operationRecord.setType("Payment");
        /*operationStatuses = new ArrayList<>();
        status = new OperationStatus();
        status.setType(OperationStatus.Type.COMPLETED);
        status.setId("9");
        status.setCancellable(false);
        status.setTimestamp("20150927034052");
        operationStatuses.add(status);
        status = new OperationStatus();
        status.setType(OperationStatus.Type.PROCESSING);
        status.setId("8");
        status.setCancellable(false);
        status.setTimestamp("20150927030952");
        operationStatuses.add(status);
        operationRecord.setOperationStatuses(operationStatuses);*/
        operationRecord.setExecutionStatus(OperationStatus.Type.REJECTED.toString());
        list.add(operationRecord);

        return list;
    }

}
