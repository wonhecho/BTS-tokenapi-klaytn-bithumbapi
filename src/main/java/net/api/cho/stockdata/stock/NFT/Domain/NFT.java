package net.api.cho.stockdata.stock.NFT.Domain;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class NFT {

    private String id;
    private String from;
    private String to;

    public NFT(String id, String from,String to){
        this.id = id;
        this.from = from;
        this.to = to;
    }
}
