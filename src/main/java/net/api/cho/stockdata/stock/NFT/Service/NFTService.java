package net.api.cho.stockdata.stock.NFT.Service;

import net.api.cho.stockdata.stock.NFT.Domain.*;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public interface NFTService {
    Object makeNFT(MakeNFT makeNFT) throws ParseException;
    Object checkNFT(String account) throws ParseException;
    Object sendNFT(NFTdto NFTdto) throws ParseException;
    Object findByid(String id);
    Object allNFT();
    Object likeNFT(Likedto likedto);
    Object likelist(String user);
    Object deletelike(Likedto likedto);
    Integer countlike(String nft);
    Object deleteNFT(Deletedto deletedto) throws ParseException;
}