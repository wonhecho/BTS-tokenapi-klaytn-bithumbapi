package net.api.cho.stockdata.stock.Wallet.Service;

import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.Wallet.Api.SendKlay;
import net.api.cho.stockdata.stock.Wallet.Api.WalletApi;
import net.api.cho.stockdata.stock.Wallet.Domain.Wallet;
import net.api.cho.stockdata.stock.Wallet.Dto.KlayDto;
import net.api.cho.stockdata.stock.Wallet.Dto.WalletDto;
import net.api.cho.stockdata.stock.Wallet.Repository.WalletRepository;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService{
    private final WalletApi wallet;
    private final WalletRepository walletRepository;
    private final SendKlay sendKlay;

    @Override
    public Object CheckWallet() throws IOException {
         return wallet.CheckWallet();
    }

    @Override
    public WalletDto CreateWallet() throws IOException {
        WalletDto walletinfo = wallet.CreateWallet();
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

    @Override
    public Optional<Double> muchWallet(String account) throws IOException, ParseException {
        Optional<Double> klay = wallet.muchWallet(account);
        return klay;
    }

    @Override
    public Object send(KlayDto klayDto) throws ParseException{
        return sendKlay.send(klayDto);
    }
}
