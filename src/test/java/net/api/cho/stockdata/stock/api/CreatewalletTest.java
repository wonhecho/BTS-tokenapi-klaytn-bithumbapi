package net.api.cho.stockdata.stock.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CreatewalletTest {

    @Test
    double hexCalculator(String hex) {
        double result = 0;
        for (int i=0; i<=hex.length()-1;i++)
        {
            if(hex.charAt(i)>='A' && hex.charAt(i)<='F')
                result = result * 16 + hex.charAt(i) - 'A' + 10;
            else if(hex.charAt(i)>='a' && hex.charAt(i) <= 'f')
                result = result * 16 + hex.charAt(i) -'a' + 10;
            else if (hex.charAt(i) >='0' && hex.charAt(i) <= '9')
                result = result * 16 + hex.charAt(i) - '0';
        }
        double checkresult = result/Math.pow(10,18);
        return checkresult;
    }
    @Test
    public void InttoHex(){
        String hex = changeNumber(2*Math.pow(10,18));
        double checkresult = hexCalculator(hex);
        System.out.println(hex.toLowerCase());
        System.out.println(checkresult);
        assertThat(hex.toLowerCase(),is(equalTo("0x1bc16d674ec80000")));
        assertThat(checkresult,is(2.0));
    }
    public static String changeNumber(double num) {
        String answer = "";
        int remainder = 0;
        while(num != 0) {
            remainder = (int) (num%16);
            if(num%16 < 10) {
                answer = remainder + answer;
            } else {
                answer = (char)(remainder + 55) + answer;
            }
            num /= 16;
        }
        String resultdata = "";
        for (int i = 0; i<answer.length()-1;i++){
            if(answer.charAt(i) == '0'){
                continue;
            }
            else {
                System.out.println(i);
                resultdata = answer.substring(i,answer.length());
                break;
            }
        }
        System.out.println("return data =" + resultdata);
        return "0x"+resultdata;
    }
}