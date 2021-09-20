package net.api.cho.stockdata.stock.NFT.Service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.NFT.Domain.MakeNFT;
import net.api.cho.stockdata.stock.NFT.Domain.MakeNFTdto;
import net.api.cho.stockdata.stock.NFT.Domain.NFTdto;
import net.api.cho.stockdata.stock.NFT.Repository.NFTRepository;
import net.api.cho.stockdata.stock.Wallet.Api.WalletApi;
import net.api.cho.stockdata.stock.NFT.Api.NFTapi;
import net.api.cho.stockdata.stock.Price.Priceapi;
import net.api.cho.stockdata.stock.Wallet.Api.SendKlay;
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
        System.out.println(makeNFT);
        return NFTapi.makeNFT(makeNFT);
    }
    @Override
    public Object checkNFT(String account) throws ParseException{
        return nftRepository.findByowner(account);
    }
    @Override
    public Object sendNFT(NFTdto NFTdto) throws ParseException{
        return NFTapi.sendNFT(NFTdto);
    }

    @Override
    public Object findByid(String id) {
//        List<MakeNFT> findNFTS = nftRepository.findById(id);
//        String json = new Gson().toJson(findNFTS);
//        return json;
        Object obj = nftRepository.findById(id);
        return obj;
    }

    @Override
    public Object allNFT() {
        return nftRepository.findAll();
    }


}
