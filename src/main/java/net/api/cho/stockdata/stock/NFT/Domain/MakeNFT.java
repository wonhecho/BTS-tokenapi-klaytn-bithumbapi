package net.api.cho.stockdata.stock.NFT.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "NFT")
@NoArgsConstructor
public class MakeNFT {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;

    public MakeNFT(String id, String name, String description, String image)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

}
