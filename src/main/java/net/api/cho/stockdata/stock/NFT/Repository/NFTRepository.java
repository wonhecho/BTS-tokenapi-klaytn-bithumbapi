package net.api.cho.stockdata.stock.NFT.Repository;

import net.api.cho.stockdata.stock.NFT.Domain.MakeNFT;
import net.api.cho.stockdata.stock.Wallet.Domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface NFTRepository extends JpaRepository<MakeNFT,Long> {
    List<MakeNFT> findById(String id);
}
