package net.api.cho.stockdata.stock.NFT.Service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.NFT.Domain.MakeNFT;
import net.api.cho.stockdata.stock.NFT.Domain.NFT;
import net.api.cho.stockdata.stock.NFT.Repository.NFTRepository;
import net.api.cho.stockdata.stock.Wallet.Repository.WalletRepository;
import net.api.cho.stockdata.stock.Wallet.Api.WalletApi;
import net.api.cho.stockdata.stock.NFT.Api.NFTapi;
import net.api.cho.stockdata.stock.Price.Priceapi;
import net.api.cho.stockdata.stock.Wallet.Api.SendKlay;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NFTServiceImpl implements NFTService {
    private final Priceapi priceapi;
    private final WalletApi wallet;
    private final NFTRepository nftRepository;
    private final SendKlay sendKlay;
    private final NFTapi NFTapi;

    @Override
    public Object makeNFT(MakeNFT makeNFT) throws ParseException{
        nftRepository.save(makeNFT);
        return NFTapi.makeNFT(makeNFT);
    }
    @Override
    public Object checkNFT(String account) throws ParseException{
        return NFTapi.checkNFT(account);
    }
    @Override
    public Object sendNFT(NFT nft) throws ParseException{
        return NFTapi.sendNFT(nft);
    }

    @Override
    public String findByid(String id) {
        List<MakeNFT> findNFTS = nftRepository.findById(id);
        String json = new Gson().toJson(findNFTS);
        return json;
    }


}
