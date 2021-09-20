package net.api.cho.stockdata.stock.Wallet.Service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.Wallet.Api.SendKlay;
import net.api.cho.stockdata.stock.Wallet.Api.WalletApi;
import net.api.cho.stockdata.stock.Wallet.Domain.Wallet;
import net.api.cho.stockdata.stock.Wallet.Dto.KlayDto;
import net.api.cho.stockdata.stock.Wallet.Dto.WalletDto;
import net.api.cho.stockdata.stock.Wallet.Repository.WalletRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public HashMap<String, String> CreateWallet() throws IOException {
        WalletDto walletinfo = wallet.CreateWallet();
        Wallet wallet = new Wallet();
        wallet.setAddress(walletinfo.getAddress());
        wallet.setChainId(walletinfo.getChainId());
        wallet.setCreatedAt(walletinfo.getCreatedAt());
        wallet.setKeyId(walletinfo.getKeyId());
        wallet.setKrn(walletinfo.getKrn());
        wallet.setPublicKey(walletinfo.getPublicKey());
        wallet.setUpdatedAt(walletinfo.getUpdatedAt());
        String json = new Gson().toJson(walletinfo);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("address",walletinfo.getAddress());
//        walletRepository.save(wallet);
        return hashMap;
    }

    @Override
    public HashMap<String, Optional<Double>> muchWallet(String account) throws IOException, ParseException {
        Optional<Double> klay = wallet.muchWallet(account);
        HashMap<String,Optional<Double>> hashMap = new HashMap<>();
        hashMap.put("klay", klay);
        return hashMap;
    }

    @Override
    public Object send(KlayDto klayDto) throws ParseException{
        return sendKlay.send(klayDto);
    }
}
