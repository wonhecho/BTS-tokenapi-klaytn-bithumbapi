package net.api.cho.stockdata.stock.NFT.Service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.NFT.Domain.*;
import net.api.cho.stockdata.stock.NFT.Repository.LikeRepository;
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
    private final LikeRepository likeRepository;
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
    @Override
    public Object likeNFT(Likedto likedto){
        boolean flag = likeRepository.existsByNftidAndUser(likedto.getNftid(),likedto.getUser());
        if(flag == true)
        {
            return "already exists";
        }
        else {
            Like likelist = new Like();
            likelist.setNftid(likedto.getNftid());
            likelist.setUser(likedto.getUser());
            likelist.setOwner(likedto.getOwner());
            likelist.setImagepath(likedto.getImagepath());
            likelist.setName(likedto.getName());
            System.out.println(likelist);
            Object obj = likeRepository.save(likelist);
            return obj;
        }
    }
    @Override
    public Object likelist(String user){
        Object obj = likeRepository.findByuser(user);
        return obj;
    }

    @Override
    public Object deletelike(Likedto likedto) {
        Like likelist = likeRepository.findByNftidAndUser(likedto.getNftid(),likedto.getUser());
        likeRepository.delete(likelist);
        return "delete";
    }

    @Override
    public Integer countlike(String nft) {
        Integer countlike = likeRepository.countByNftid(nft);
        return countlike;
    }

    @Override
    public Object deleteNFT(Deletedto deletedto) throws ParseException{
        String nft = deletedto.getId();
        String from = deletedto.getFrom();
        String status = NFTapi.deleteNFT(from,nft);
        if(status.toString().equals("OK"))
        {
            nftRepository.deleteByid(nft);
            if(likeRepository.existsByNftid(nft))
            {
                List<Like> likelist = likeRepository.findByNftid(nft);
                likeRepository.deleteAll(likelist);
            }
        }
        else
            return 0;

        return null;
    }

}
