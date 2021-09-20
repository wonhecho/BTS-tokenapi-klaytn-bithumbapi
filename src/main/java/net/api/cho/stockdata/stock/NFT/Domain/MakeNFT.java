package net.api.cho.stockdata.stock.NFT.Domain;

import lombok.Builder;
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
    @Column(name = "imagepath")
    private String imagepath;

    @Builder
    public MakeNFT( String name, String description, String owner)
    {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

}
