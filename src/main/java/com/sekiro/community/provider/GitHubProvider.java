package com.sekiro.community.provider;

import com.alibaba.fastjson.JSON;
import com.sekiro.community.dto.AccessTokenDto;
import com.sekiro.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.rmi.server.ExportException;

/**
 * @Author dell
 * @create 2020/1/16 11:28
 */
@Component
public class GitHubProvider {

    //   要求传递的参数比较多的时候，不要一个一个放在形参，而是把它们封装成一个对象
    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto), mediaType);
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string=response.body().string();
                String token=string.split("&")[0].split("=")[1];
                return token;
            } catch (ExportException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    public GitHubUser getUser(String accesstoken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accesstoken)
                .build();

        try{
            Response response = client.newCall(request).execute();
            String string=response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
        }
        return null;
    }
}

