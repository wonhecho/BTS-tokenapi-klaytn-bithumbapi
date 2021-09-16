package net.api.cho.stockdata.stock.NFT.Service;

import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.NFT.Domain.NFT;
import net.api.cho.stockdata.stock.Wallet.Repository.WalletRepository;
import net.api.cho.stockdata.stock.Wallet.Api.WalletApi;
import net.api.cho.stockdata.stock.NFT.Api.NFTapi;
import net.api.cho.stockdata.stock.Price.Priceapi;
import net.api.cho.stockdata.stock.Wallet.Api.SendKlay;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NFTServiceImpl implements NFTService {
    private final Priceapi priceapi;
    private final WalletApi wallet;
    private final WalletRepository walletRepository;
    private final SendKlay sendKlay;
    private final NFTapi NFTapi;

    @Override
    public Object makeNFT() throws ParseException{
        return NFTapi.makeNFT();
    }
    @Override
    public Object checkNFT(String account) throws ParseException{
        return NFTapi.checkNFT(account);
    }
    @Override
    public Object sendNFT(NFT nft) throws ParseException{
        return NFTapi.sendNFT(nft);
    }

}
