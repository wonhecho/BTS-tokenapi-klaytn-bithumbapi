package net.api.cho.stockdata.stock.NFT.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Likedto {
    private String nftid;
    private String name;
    private String imagepath;
    private String user;
    private String owner;

}
