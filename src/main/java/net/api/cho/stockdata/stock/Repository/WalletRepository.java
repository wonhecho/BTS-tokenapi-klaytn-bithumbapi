package net.api.cho.stockdata.stock.Repository;

import net.api.cho.stockdata.stock.Domain.Wallet;
import net.api.cho.stockdata.stock.WalletDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
}
