package ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ProcessedTransactionDTO {
    @JsonProperty
    public Long id;
    @JsonProperty
    public String  cryptoCurrency;
    @JsonProperty
    public Double cryptoAmount;
    @JsonProperty
    public Double cryptoPrice;
    @JsonProperty
    public Double cryptoArsPrice;
    @JsonProperty
    public Double transactionPrice;
    @JsonProperty
    public UserTransactionDTO user;
    @JsonProperty
    public UserTransactionDTO interestedUSer;
    @JsonProperty
    public Transaction.Type type;
    @JsonProperty
    public Transaction.Status status;
    @JsonProperty
    public String date;
    @JsonProperty
    public Integer wallet;
    @JsonProperty
    public String cvu;

    public static ProcessedTransactionDTO from(Transaction transaction, UserTransactionDTO interestedUser) {
        return new ProcessedTransactionDTO(CryptoDTO.from(transaction.getCryptoCurrency()),transaction.cryptoAmount, transaction.cryptoPrice,
                                  transaction.cryptoArsPrice, UserDTO.from(transaction.getUser()),
                                  transaction.type, transaction.status, interestedUser, transaction.getId());
    }

    public ProcessedTransactionDTO(CryptoDTO cryptoCurrency, Double cryptoAmount, Double cryptoPrice, Double cryptoArsPrice,
                                   UserDTO user, Transaction.Type type, Transaction.Status status, UserTransactionDTO interestedUSer, Long id) {
        this.id = id;
        this.cryptoCurrency = cryptoCurrency.getSymbol();
        this.cryptoAmount = cryptoAmount;
        this.cryptoPrice = cryptoPrice;
        this.cryptoArsPrice = cryptoArsPrice;
        this.transactionPrice =  cryptoArsPrice * cryptoAmount;
        this.user = UserTransactionDTO.from(user);
        this.interestedUSer = interestedUSer;
        this.type = type;
        this.date = formatted_date();
        this.wallet = user_wallet(user.getWallet(), type);
        this.cvu = user_cvu(user.getCvu(), type);
        this.status = status;

    }

    public String formatted_date(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date raw_date = new Date();
        String formatted_date = formatter.format(raw_date);
        return formatted_date;
    }

    public String user_cvu(String user_cvu, Transaction.Type type){
        if(type == Transaction.Type.SALE){ return user_cvu;}
        else { return null; }
    }

    public Integer user_wallet(Integer user_wallet, Transaction.Type type){
        if(type == Transaction.Type.PURCHASE){ return user_wallet;}
        else { return null; }
    }
    }