package net.api.cho.stockdata.stock.Service;

import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.Domain.NFT;
import net.api.cho.stockdata.stock.Domain.Wallet;
import net.api.cho.stockdata.stock.Repository.WalletRepository;
import net.api.cho.stockdata.stock.WalletDto;
import net.api.cho.stockdata.stock.api.Createwallet;
import net.api.cho.stockdata.stock.api.NFTapi;
import net.api.cho.stockdata.stock.api.Priceapi;
import net.api.cho.stockdata.stock.api.sendKlay;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NFTServiceImpl implements NFTService {
    private final Priceapi priceapi;
    private final Createwallet createwallet;
    private final WalletRepository walletRepository;
    private final sendKlay sendKlay;
    private final NFTapi NFTapi;

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
    public Optional<Double> much(String account) throws IOException,ParseException{
        Optional<Double> klay = createwallet.muchWallet(account);
        return klay;
    }
    public Object send() throws ParseException{
        return sendKlay.send();
    }
    public Object makeNFT() throws ParseException{
        return NFTapi.makeNFT();
    }
    public Object checkNFT(String account) throws ParseException{
        return NFTapi.checkNFT(account);
    }
    public Object sendNFT(NFT nft) throws ParseException{
        return NFTapi.sendNFT(nft);
    }

}
