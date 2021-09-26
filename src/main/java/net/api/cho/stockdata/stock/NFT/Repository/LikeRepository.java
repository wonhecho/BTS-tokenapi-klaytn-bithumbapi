package net.api.cho.stockdata.stock.NFT.Repository;

import net.api.cho.stockdata.stock.NFT.Domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findByNftid(String nft);
    List<Like> findByuser(String user);
    boolean existsByNftid(String nft);
    boolean existsByNftidAndUser(String nft, String user);
    Like findByNftidAndUser(String nft,String user);
    Integer countByNftid(String nft);

}
