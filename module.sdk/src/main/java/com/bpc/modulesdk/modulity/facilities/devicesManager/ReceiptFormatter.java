package com.bpc.modulesdk.modulity.facilities.devicesManager;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.AccountToCashAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.AccountToCashCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.AgentAccountToAccountReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.AgentCashReceiveReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.AgentCustomerAccToAccReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.BalanceAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.BalanceCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.BillPaymentByCashAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.BillPaymentByCashCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.BillPaymentFromAccountAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.BillPaymentFromAccountCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CashDepositAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CashDepositCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CashToAccountAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CashToAccountCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CashToCashAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CashToCashCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CashWithdrawalAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CashWithdrawalCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CustomerAccToAccReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CustomerCardPinChangeAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CustomerCardPinChangeReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CustomerCashReceiveReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CustomerMinistatementAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CustomerMinistatementReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CustomerNewAccountAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CustomerNewAccountCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CustomerNewCardAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.CustomerNewCardCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.LoanRepaymentByCashAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.LoanRepaymentByCashCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.LoanRepaymentFromAccountAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.LoanRepaymentFromAccountCustomerReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.NewCustomerAgentReceipt;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.NewCustomerReceipt;

/**
 * Created by Samoylov on 23.01.2017.
 */

public interface ReceiptFormatter {
    byte[] formatBalanceAgentReceipt(Context context, BalanceAgentReceipt receipt) throws Exception;
    byte[] formatBalanceCustomerReceipt(Context context, BalanceCustomerReceipt receipt) throws Exception;
    byte[] formatAgentAccountToAccountReceipt(Context context, AgentAccountToAccountReceipt receipt) throws Exception;
    byte[] formatAccountToCashReceipt(Context context, AccountToCashCustomerReceipt receipt) throws Exception;
    byte[] formatAccountToCashReceipt(Context context, AccountToCashAgentReceipt receipt) throws Exception;
    byte[] formatCashToAccountReceipt(Context context, CashToAccountAgentReceipt receipt) throws Exception;
    byte[] formatCashToAccountReceipt(Context context, CashToAccountCustomerReceipt receipt) throws Exception;
    byte[] formatCashWithdrawalReceipt(Context context, CashWithdrawalCustomerReceipt receipt) throws Exception;
    byte[] formatCashWithdrawalReceipt(Context context, CashWithdrawalAgentReceipt receipt) throws Exception;
    byte[] formatCashDepositReceipt(Context context, CashDepositCustomerReceipt receipt) throws Exception;
    byte[] formatCashDepositReceipt(Context context, CashDepositAgentReceipt receipt) throws Exception;
    byte[] formatCashToCashReceipt(Context context, CashToCashAgentReceipt receipt) throws Exception;
    byte[] formatCashToCashReceipt(Context context, CashToCashCustomerReceipt receipt) throws Exception;
    byte[] formatCustomerNewAccountReceipt(Context context, CustomerNewAccountAgentReceipt receipt) throws Exception;
    byte[] formatCustomerNewAccountReceipt(Context context, CustomerNewAccountCustomerReceipt receipt) throws Exception;
    byte[] formatCustomerNewCardReceipt(Context context, CustomerNewCardAgentReceipt receipt) throws Exception;
    byte[] formatCustomerNewCardReceipt(Context context, CustomerNewCardCustomerReceipt receipt) throws Exception;
    byte[] formatNewCustomerReceipt(Context context, NewCustomerAgentReceipt receipt) throws Exception;
    byte[] formatNewCustomerReceipt(Context context, NewCustomerReceipt receipt) throws Exception;
    byte[] formatBillPaymentReceipt(Context context, BillPaymentByCashAgentReceipt receipt) throws Exception;
    byte[] formatBillPaymentReceipt(Context context, BillPaymentByCashCustomerReceipt receipt) throws Exception;
    byte[] formatBillPaymentReceipt(Context context, BillPaymentFromAccountAgentReceipt receipt) throws Exception;
    byte[] formatBillPaymentReceipt(Context context, BillPaymentFromAccountCustomerReceipt receipt) throws Exception;
    byte[] formatCustomerCardPinChangeReceipt(Context context, CustomerCardPinChangeAgentReceipt receipt) throws Exception;
    byte[] formatCustomerCardPinChangeReceipt(Context context, CustomerCardPinChangeReceipt receipt) throws Exception;

    byte[] formatCustomerMinisatementReceipt(Context context, CustomerMinistatementReceipt receipt) throws Exception;
    byte[] formatCustomerMinistatementAgentReceipt(Context context, CustomerMinistatementAgentReceipt receipt) throws Exception;

    byte[] formatCustomerCashReceiveReceipt(Context context, CustomerCashReceiveReceipt receipt) throws Exception;
    byte[] formatAgentCashReceiveReceipt(Context context, AgentCashReceiveReceipt receipt) throws Exception;

    byte[] formatCustomerAccToAccReceipt(Context context, CustomerAccToAccReceipt receipt) throws Exception;
    byte[] formatAgentCustomerAccToAccReceipt(Context context, AgentCustomerAccToAccReceipt receipt) throws Exception;

    byte[] formatLoanRepaymentByCashReceipt(Context context, LoanRepaymentByCashAgentReceipt receipt) throws Exception;
    byte[] formatLoanRepaymentByCashReceipt(Context context, LoanRepaymentByCashCustomerReceipt receipt) throws Exception;
    byte[] formatLoanRepaymentFromAccountReceipt(Context context, LoanRepaymentFromAccountAgentReceipt receipt) throws Exception;
    byte[] formatLoanRepaymentFromAccountReceipt(Context context, LoanRepaymentFromAccountCustomerReceipt receipt) throws Exception;
}
