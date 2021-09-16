package net.api.cho.stockdata.stock.Wallet.Service;

import net.api.cho.stockdata.stock.Wallet.Dto.WalletDto;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public interface WalletService {

    Object CheckWallet() throws IOException;
    WalletDto CreateWallet() throws IOException;
    Optional<Double> muchWallet(String account) throws IOException, ParseException;

}
