package com.bpc.modulesdk.rest.dto.pojo.entries;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 5/25/17.
 */

public class OperationInfoEntry implements Serializable {
    private OperationType type;
    private String timestamp;
    private MoneyEntry operationAmount;
    private PaymentFullRecordsInfoEntry paymentInfo;

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public MoneyEntry getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(MoneyEntry operationAmount) {
        this.operationAmount = operationAmount;
    }

    public PaymentFullRecordsInfoEntry getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentFullRecordsInfoEntry paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public enum OperationType {
        PAYMENT("payment");

        private String type;

        OperationType(String type) {
            this.type = type;
        }

        @JsonCreator
        public static OperationType fromString(String value) {
            for (OperationType type : OperationType.values()) {
                String typeString = type.toString();
                if (typeString.equalsIgnoreCase(value.trim())) {
                    return type;
                }
            }

            return null;
        }

        @JsonValue
        public String toString() {
            return type;
        }
    }
}
