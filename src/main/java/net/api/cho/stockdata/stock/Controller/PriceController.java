package net.api.cho.stockdata.stock.Controller;


import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.Domain.NFT;
import net.api.cho.stockdata.stock.Service.NFTServiceImpl;
import net.api.cho.stockdata.stock.Service.NFTService;
import net.api.cho.stockdata.stock.api.Createwallet;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PriceController {
    private final NFTService NFTService;
    private final Createwallet createwallet;

    @GetMapping("/api")
    public Object nowprice() throws IOException, ParseException {
        return NFTService.init();
    }
    @GetMapping("/wallet")
    public Object check() throws IOException{
        return NFTService.check();
    }
    @GetMapping("/createwallet")
    public Object create() throws IOException{
        return NFTService.create();
    }
    @GetMapping("/balancewallet")
    public double much() throws IOException,ParseException{
        return NFTService.much();
    }
    @GetMapping("/sendklay")
    public Object send() throws ParseException{
        return NFTService.send();
    }
    @GetMapping("/makeNFT")
    public Object makeNFT() throws  ParseException{
        return NFTService.makeNFT();
    }
    @GetMapping("/checkNFT/{account}")
    public Object checkNFT(@PathVariable String account) throws ParseException{
        return NFTService.checkNFT(account);
    }
    @PostMapping("/sendNFT")
    public Object sendNFT(@RequestBody NFT nft) throws ParseException{
        return NFTService.sendNFT(nft);
    }

}
