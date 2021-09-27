package net.api.cho.stockdata.stock.Wallet.Controller;


import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.Wallet.Dto.KlayDto;
import net.api.cho.stockdata.stock.Wallet.Dto.UserDto;
import net.api.cho.stockdata.stock.Wallet.Service.WalletService;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Wallet")
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/check")
    public ResponseEntity<Object> check() throws IOException {
        return ResponseEntity.ok(walletService.CheckWallet());
    }
    @GetMapping("/create")
    public ResponseEntity<HashMap<String, String>> create(@RequestBody UserDto userDto) throws IOException{
        return ResponseEntity.ok(walletService.CreateWallet(userDto));
    }
    @GetMapping("/much/{account}")
    public ResponseEntity<HashMap<String, Optional<Double>>> much(@PathVariable String account) throws IOException, ParseException {
        return ResponseEntity.ok(walletService.muchWallet(account));
    }
    @PostMapping("/sendklay")
    public ResponseEntity<Object> send(@RequestBody KlayDto klayDto) throws ParseException{
        return ResponseEntity.ok(walletService.send(klayDto));
    }

}
