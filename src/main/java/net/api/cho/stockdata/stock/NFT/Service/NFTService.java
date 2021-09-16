package net.api.cho.stockdata.stock.NFT.Service;

import net.api.cho.stockdata.stock.NFT.Domain.NFT;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface NFTService {
    Object makeNFT() throws ParseException;
    Object checkNFT(String account) throws ParseException;
    Object sendNFT(NFT nft) throws ParseException;

}
