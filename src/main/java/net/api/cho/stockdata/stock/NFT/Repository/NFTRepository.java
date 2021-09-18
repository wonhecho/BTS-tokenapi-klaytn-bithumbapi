package net.api.cho.stockdata.stock.NFT.Repository;

import net.api.cho.stockdata.stock.NFT.Domain.MakeNFT;
import net.api.cho.stockdata.stock.NFT.Domain.NFT;
import net.api.cho.stockdata.stock.Wallet.Domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface NFTRepository extends JpaRepository<MakeNFT,Long> {
    List<MakeNFT> findById(String id);
    Optional<MakeNFT> findById(Integer id);
    Optional<MakeNFT> findByNO(Integer no);
    List<MakeNFT> findTop1ByOrderByIdAsc();
    List<MakeNFT> findByowner(String owner);
    void deleteByNO(Integer no);

}
