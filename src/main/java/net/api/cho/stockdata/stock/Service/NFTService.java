package net.api.cho.stockdata.stock.Service;

import net.api.cho.stockdata.stock.Domain.NFT;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public interface NFTService {
    Object init() throws IOException, ParseException;
    Object check() throws IOException;
    Object create() throws IOException;
    Optional<Double> much(String account) throws IOException,ParseException;
    Object send() throws ParseException;
    Object makeNFT() throws ParseException;
    Object checkNFT(String account) throws ParseException;
    Object sendNFT(NFT nft) throws ParseException;

}
