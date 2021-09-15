package net.api.cho.stockdata.stock.Domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
