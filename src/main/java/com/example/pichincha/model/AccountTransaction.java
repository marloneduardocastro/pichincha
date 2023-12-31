package com.example.pichincha.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "AccountTransaction")
public class AccountTransaction {
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute(attributeName ="accountNumber")
    private String accountNumber;

    @DynamoDBAttribute(attributeName ="transactionType")
    private String transactionType;

    @DynamoDBAttribute(attributeName ="amount")
    private double amount;

    @DynamoDBAttribute(attributeName ="tax")
    private String tax;

    @DynamoDBAttribute(attributeName ="transactionValue")
    private double transactionValue;

    @DynamoDBAttribute(attributeName ="transactionSource")
    private String transactionSource;

    @DynamoDBAttribute(attributeName ="date")
    private String date;

}
