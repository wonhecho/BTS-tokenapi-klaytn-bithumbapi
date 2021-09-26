package net.api.cho.stockdata.stock.NFT.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "likelist")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="NO")
    private Integer No;
    @Column(name = "Nftid")
    private String nftid;
    @Column(name = "owner")
    private String owner;
    @Column(name = "user")
    private String user;
    @Column(name = "name")
    private String name;
    @Column(name = "imagepath")
    private String imagepath;

    public Like(String nftid, String owner, String user){
        this.nftid = nftid;
        this.owner = owner;
        this.user = user;
    }


}
