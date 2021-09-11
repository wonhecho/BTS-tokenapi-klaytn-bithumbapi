package net.api.cho.stockdata.stock.Controller;


import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.Service.JsonService;
import net.api.cho.stockdata.stock.WalletDto;
import net.api.cho.stockdata.stock.api.Createwallet;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PriceController {
    private final JsonService jsonService;
    private final Createwallet createwallet;
    @GetMapping("/api")
    public Object nowprice() throws IOException, ParseException {
        return jsonService.init();
    }
    @GetMapping("/wallet")
    public Object check() throws IOException{
        return jsonService.check();
    }
    @GetMapping("/createwallet")
    public Object create() throws IOException{
        return jsonService.create();
    }
    @GetMapping("/Balancewallet")
    public String much() throws IOException,ParseException{
        return jsonService.much();
    }

}
