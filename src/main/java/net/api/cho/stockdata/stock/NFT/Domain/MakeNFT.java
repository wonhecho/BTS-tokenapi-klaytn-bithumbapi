package net.api.cho.stockdata.stock.NFT.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "NFT")
@NoArgsConstructor
public class MakeNFT {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NO")
    private Integer NO;
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "owner")
    private String owner;
    @Column(name = "Date")
    private String date;

    public MakeNFT( String name, String description, String image, String owner)
    {
        this.name = name;
        this.description = description;
        this.image = image;
        this.owner = owner;
    }

}
