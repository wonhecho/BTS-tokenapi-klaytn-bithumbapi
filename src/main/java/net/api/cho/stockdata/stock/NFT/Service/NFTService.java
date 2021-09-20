package net.api.cho.stockdata.stock.NFT.Service;

import net.api.cho.stockdata.stock.NFT.Domain.MakeNFT;
import net.api.cho.stockdata.stock.NFT.Domain.MakeNFTdto;
import net.api.cho.stockdata.stock.NFT.Domain.NFTdto;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public interface NFTService {
    Object makeNFT(MakeNFT makeNFT) throws ParseException;
    Object checkNFT(String account) throws ParseException;
    Object sendNFT(NFTdto NFTdto) throws ParseException;
    Object findByid(String id);
    Object allNFT();
}
