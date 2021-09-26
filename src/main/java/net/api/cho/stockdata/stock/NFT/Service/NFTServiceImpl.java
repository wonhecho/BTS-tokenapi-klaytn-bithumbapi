package net.api.cho.stockdata.stock.NFT.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.NFT.Domain.*;
import net.api.cho.stockdata.stock.NFT.Repository.LikeRepository;
import net.api.cho.stockdata.stock.NFT.Repository.NFTRepository;
import net.api.cho.stockdata.stock.Wallet.Api.WalletApi;
import net.api.cho.stockdata.stock.NFT.Api.NFTapi;
import net.api.cho.stockdata.stock.Price.Priceapi;
import net.api.cho.stockdata.stock.Wallet.Api.SendKlay;
import net.api.cho.stockdata.stock.AWSS3.S3Service.S3uploader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
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
    private final S3uploader s3uploader;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public HashMap<String,String> makeNFT(MakeNFTdto makeNFTdto, MultipartFile file) throws IOException, ParseException{
        String imagepath = s3uploader.upload(file,"static");
        MakeNFT insertNFT = new MakeNFT();
        insertNFT.setDescription(makeNFTdto.getDescription());
        insertNFT.setImage(makeNFTdto.getImage());
        insertNFT.setOwner(makeNFTdto.getOwner());
        insertNFT.setName(makeNFTdto.getName());
        insertNFT.setImagepath(imagepath);
        nftRepository.save(insertNFT);
        System.out.println(insertNFT);
        HashMap<String,String> result = new HashMap<>();
        String set = NFTapi.makeNFT(insertNFT);
        if(set.equals("Submitted")){
            result.put("status","OK");
        }
        else
            result.put("status","Fail");
        return result;
    }
    @Override
    public String checkNFT(String account) throws IOException, ParseException{
        Object find = nftRepository.findByowner(account);
        String result = mapper.writeValueAsString(find);
        return result;
    }
    @Override
    public HashMap<String,String> sendNFT(NFTdto NFTdto) throws ParseException{
        String set = NFTapi.sendNFT(NFTdto);
        HashMap<String,String> result = new HashMap<>();
        if(set.equals("Submitted")){
            result.put("status","OK");
        }
        else
            result.put("status","Fail");
        return result;
    }

    @Override
    public HashMap<String,String> findByid(String id) {
        MakeNFT findNFTS = nftRepository.findById(id);
        HashMap<String,String> result = new HashMap<>();
        result.put("id",findNFTS.getId());
        result.put("name",findNFTS.getName());
        result.put("description",findNFTS.getDescription());
        result.put("image",findNFTS.getImage());
        result.put("owner",findNFTS.getOwner());
        result.put("imagepath",findNFTS.getImagepath());
        result.put("date",findNFTS.getDate());
        return result;
    }

    @Override
    public String allNFT() throws IOException{
        Object find = nftRepository.findAll();
        String result = mapper.writeValueAsString(find);
        return result;
    }
    @Override
    public HashMap<String,String> likeNFT(Likedto likedto){
        boolean flag = likeRepository.existsByNftidAndUser(likedto.getNftid(),likedto.getUser());
        HashMap<String,String> result = new HashMap<>();
        if(flag == true)
        {
            result.put("status","already exists");
            return result;
        }
        else {
            Like likelist = new Like();
            likelist.setNftid(likedto.getNftid());
            likelist.setUser(likedto.getUser());
            likelist.setOwner(likedto.getOwner());
            likelist.setImagepath(likedto.getImagepath());
            likelist.setName(likedto.getName());
            likeRepository.save(likelist);
            result.put("status","OK");
            return result;
        }
    }
    @Override
    public String likelist(String user) throws IOException{
        Object obj = likeRepository.findByuser(user);
        String result = mapper.writeValueAsString(obj);
        return result;
    }

    @Override
    public HashMap<String,String> deletelike(Likedto likedto) {
        Like likelist = likeRepository.findByNftidAndUser(likedto.getNftid(),likedto.getUser());
        likeRepository.delete(likelist);
        HashMap<String,String> result = new HashMap<>();
        result.put("status","OK");
        return result;
    }

    @Override
    public HashMap<String,Integer> countlike(String nft) {
        HashMap<String,Integer> result = new HashMap<>();
        Integer countlike = likeRepository.countByNftid(nft);
        result.put("count",countlike);
        return result;
    }

    @Override
    public HashMap<String,String> deleteNFT(Deletedto deletedto) throws ParseException{
        String nft = deletedto.getId();
        String from = deletedto.getFrom();
        HashMap<String,String> result = new HashMap<>();
        String status = NFTapi.deleteNFT(from,nft);
        if(status.toString().equals("OK"))
        {
            nftRepository.deleteByid(nft);
            if(likeRepository.existsByNftid(nft))
            {
                List<Like> likelist = likeRepository.findByNftid(nft);
                likeRepository.deleteAll(likelist);
            }

            result.put("status","OK");
        }
        else {
            result.put("status","Fail");
            return result;
        }
        return result;
    }

}
