package net.api.cho.stockdata.stock.Price;


import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.NFT.Domain.NFT;
import net.api.cho.stockdata.stock.NFT.Service.NFTService;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController

public class PriceController {
    private final NFTService NFTService;
    private final PriceService priceService;

    @GetMapping("/api")
    public ResponseEntity nowprice() throws IOException, ParseException {
        return ResponseEntity.ok(priceService.init());
    }
    @GetMapping("/sendklay")
    public ResponseEntity send() throws ParseException{
        return ResponseEntity.ok(NFTService.send());
    }

}