package net.api.cho.stockdata.stock.NFT.Service;

import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.NFT.Domain.NFT;
import net.api.cho.stockdata.stock.Wallet.Repository.WalletRepository;
import net.api.cho.stockdata.stock.Wallet.Api.WalletApi;
import net.api.cho.stockdata.stock.NFT.Api.NFTapi;
import net.api.cho.stockdata.stock.Price.Priceapi;
import net.api.cho.stockdata.stock.api.sendKlay;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class NFTServiceImpl implements NFTService {
    private final Priceapi priceapi;
    private final WalletApi wallet;
    private final WalletRepository walletRepository;
    private final sendKlay sendKlay;
    private final NFTapi NFTapi;
    @Transactional(readOnly = true)
    public Object init() throws IOException, ParseException {
        return priceapi.callApi();
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
