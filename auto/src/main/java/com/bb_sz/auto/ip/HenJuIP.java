package com.bb_sz.auto.ip;

import android.util.Log;

import com.bb_sz.auto.http.Api;
import com.bb_sz.lib.http.Http;
import com.bb_sz.lib.http.HttpEntry;
import com.bb_sz.lib.http.HttpResponse;
import com.bb_sz.lib.http.IHttpCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/24.
 */

public class HenJuIP {

    private static final String HOST_HTTP = "http://10.150.0.7";
    public final String HOST = "10.150.0.7";
    private static final String END = "\r\n";
    private static final int PORT = 80;

    public static final String method = "/goform/formReConnect";

    public void reCall() {
        String baseUrl = HOST_HTTP + method;
        Map<String, String> header = new HashMap<>();
        header.put("Host", HOST);
        header.put("Connection", "keep-alive");


        String data = "delstr=&id=&Isp_Name=2&Isp_Type=";
        Map<String, String> body = new HashMap<>();
        body.put("delstr", "");
        body.put("id", "");
        body.put("Isp_Name", "2");
        body.put("Isp_Type", "");

        header.put("Content-Length", "" + data.length());
        header.put("Cache-Control", "max-age=0");
//        header.put("Authorization", "Digest username=\"admin\", realm=\"UTT\", nonce=\"a2b765eb7af3ddff6a306d7fa416270a\", uri=\"/goform/formReConnect\", algorithm=MD5, response=\"b345a9d340cbd105e3178c863fe0f7f8\", opaque=\"5ccc069c403ebaf9f0171e9517f40e41\", qop=auth, nc=0000017c, cnonce=\"de00bf7e5ff5dce1\"");
//        header.put("Authorization", "Digest username=\"admin\", realm=\"UTT\", nonce=\"a2b765eb7af3ddff6a306d7fa416270a\", uri=\"/goform/formReConnect\", algorithm=MD5, response=\"edeb43169c34bd7b7c0c9e5cb5ea4db7\", opaque=\"5ccc069c403ebaf9f0171e9517f40e41\", qop=auth, nc=00000190, cnonce=\"cbf9d8140cf1a157\"");
        header.put("Authorization", "Digest username=\"admin\", realm=\"UTT\", nonce=\"a2b765eb7af3ddff6a306d7fa416270a\", uri=\"/goform/formReConnect\", algorithm=MD5, response=\"d2ef3e7726bb878bfdb2cc8a5f2c60c2\", opaque=\"5ccc069c403ebaf9f0171e9517f40e41\", qop=auth, nc=000001b4, cnonce=\"ad87ccaa40899217\"\n");
        header.put("Origin", HOST_HTTP);
        header.put("Upgrade-Insecure-Requests", "1");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        header.put("Referer", "http://10.150.0.7/WANConfig.asp");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-CN,zh;q=0.8");
        header.put("Cookie", "language=zhcn; utt_bw_rdevType=");
        IHttpCallback callback = new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    Log.i("SKY", "res:" + httpResponse.getBody().getString());
                }
            }
        };


        HttpEntry entry = new HttpEntry();
        entry.setType(Http.POST);
        entry.setHeader(header);
        entry.setCallback(callback);
        entry.setBaseUrl(baseUrl);

        entry.setBody(body);

        Api.getInstance().ipHenJu(entry);
    }
}
