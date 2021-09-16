package net.api.cho.stockdata.stock.Wallet.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name = "Wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private long user_id;
    @Column(name="address")
    private String address;
    @Column(name = "chainId")
    private String chainId;
    @Column(name = "createdAt")
    private String createdAt;
    @Column(name="keyId")
    private String keyId;
    @Column(name="krn")
    private String krn;
    @Column(name="publicKey")
    private String publicKey;
    @Column(name="updatedAt")
    private String updatedAt;

}
