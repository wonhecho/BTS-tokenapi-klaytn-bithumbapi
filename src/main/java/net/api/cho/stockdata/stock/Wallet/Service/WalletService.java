package net.api.cho.stockdata.stock.Wallet.Service;

import net.api.cho.stockdata.stock.Wallet.Dto.KlayDto;
import net.api.cho.stockdata.stock.Wallet.Dto.UserDto;
import net.api.cho.stockdata.stock.Wallet.Dto.WalletDto;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@Component
public interface WalletService {

    Object CheckWallet() throws IOException;
    HashMap<String, String> CreateWallet(UserDto userDto) throws IOException;
    HashMap<String, Optional<Double>> muchWallet(String account) throws IOException, ParseException;
    Object send(KlayDto klayDto) throws ParseException;

}
