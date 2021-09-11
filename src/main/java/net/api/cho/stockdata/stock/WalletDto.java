package net.api.cho.stockdata.stock;

import lombok.Data;

@Data
public class WalletDto {
    String address;
    String chainId;
    String createdAt;
    String keyId;
    String krn;
    String publicKey;
    String updatedAt;
}
