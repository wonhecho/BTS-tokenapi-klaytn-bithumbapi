package net.api.cho.stockdata.stock.Controller;


import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.Domain.NFT;
import net.api.cho.stockdata.stock.Service.NFTServiceImpl;
import net.api.cho.stockdata.stock.Service.NFTService;
import net.api.cho.stockdata.stock.api.Createwallet;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PriceController {
    private final NFTService NFTService;
    private final Createwallet createwallet;

    @GetMapping("/api")
    public ResponseEntity nowprice() throws IOException, ParseException {
        return ResponseEntity.ok(NFTService.init());
    }
    @GetMapping("/wallet")
    public ResponseEntity<Object> check() throws IOException{
        return ResponseEntity.ok(NFTService.check());
    }
    @GetMapping("/createwallet")
    public ResponseEntity create() throws IOException{
        return ResponseEntity.ok(NFTService.create());
    }
    @GetMapping("/balancewallet/{account}")
    public ResponseEntity<Optional<Double>> much(@PathVariable String account) throws IOException,ParseException{
        return ResponseEntity.ok(NFTService.much(account));
    }
    @GetMapping("/sendklay")
    public ResponseEntity send() throws ParseException{
        return ResponseEntity.ok(NFTService.send());
    }
    @GetMapping("/makeNFT")
    public ResponseEntity makeNFT() throws  ParseException{
        return ResponseEntity.ok(NFTService.makeNFT());
    }
    @GetMapping("/checkNFT/{account}")
    public ResponseEntity checkNFT(@PathVariable String account) throws ParseException{
        return ResponseEntity.ok(NFTService.checkNFT(account));
    }
    @PostMapping("/sendNFT")
    public ResponseEntity sendNFT(@RequestBody NFT nft) throws ParseException{
        return ResponseEntity.ok(NFTService.sendNFT(nft));
    }

}
