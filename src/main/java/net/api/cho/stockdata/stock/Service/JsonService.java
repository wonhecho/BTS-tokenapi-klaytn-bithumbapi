package net.api.cho.stockdata.stock.Service;

import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.Domain.Wallet;
import net.api.cho.stockdata.stock.Repository.WalletRepository;
import net.api.cho.stockdata.stock.WalletDto;
import net.api.cho.stockdata.stock.api.Createwallet;
import net.api.cho.stockdata.stock.api.Priceapi;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class JsonService {
    private final Priceapi priceapi;
    private final Createwallet createwallet;
    private final WalletRepository walletRepository;

    @Transactional(readOnly = true)
    public Object init() throws IOException, ParseException {
        return priceapi.callApi();
    }
    public Object check() throws IOException {
        return createwallet.CheckWallet();
    }
    public Object create() throws IOException{
        WalletDto walletinfo = createwallet.CreateWallet();
        Wallet wallet = new Wallet();
        wallet.setAddress(walletinfo.getAddress());
        wallet.setChainId(walletinfo.getChainId());
        wallet.setCreatedAt(walletinfo.getCreatedAt());
        wallet.setKeyId(walletinfo.getKeyId());
        wallet.setKrn(walletinfo.getKrn());
        wallet.setPublicKey(walletinfo.getPublicKey());
        wallet.setUpdatedAt(walletinfo.getUpdatedAt());
        walletRepository.save(wallet);
        return walletinfo;
    }
    public String much() throws IOException,ParseException{
        String klay = createwallet.muchWallet();
        return klay;
    }

}
