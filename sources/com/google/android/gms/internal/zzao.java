package com.google.android.gms.internal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public final class zzao implements zzan {
    private final zzap zzaB;
    private final SSLSocketFactory zzaC;

    public zzao() {
        this(null);
    }

    private zzao(zzap zzap) {
        this(null, null);
    }

    private zzao(zzap zzap, SSLSocketFactory sSLSocketFactory) {
        this.zzaB = null;
        this.zzaC = null;
    }

    private static HttpEntity zza(HttpURLConnection httpURLConnection) {
        InputStream errorStream;
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            errorStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            errorStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(errorStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    private static void zza(HttpURLConnection httpURLConnection, zzp<?> zzp) throws IOException, zza {
        byte[] zzg = zzp.zzg();
        if (zzg != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", zzp.zzf());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzg);
            dataOutputStream.close();
        }
    }

    public final HttpResponse zza(zzp<?> zzp, Map<String, String> map) throws IOException, zza {
        String str;
        String url = zzp.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(zzp.getHeaders());
        hashMap.putAll(map);
        if (this.zzaB != null) {
            str = this.zzaB.zzg(url);
            if (str == null) {
                String str2 = "URL blocked by rewriter: ";
                String valueOf = String.valueOf(url);
                throw new IOException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
        } else {
            str = url;
        }
        URL url2 = new URL(str);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int zzi = zzp.zzi();
        httpURLConnection.setConnectTimeout(zzi);
        httpURLConnection.setReadTimeout(zzi);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        "https".equals(url2.getProtocol());
        for (String str3 : hashMap.keySet()) {
            httpURLConnection.addRequestProperty(str3, (String) hashMap.get(str3));
        }
        switch (zzp.getMethod()) {
            case -1:
                break;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                break;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                zza(httpURLConnection, zzp);
                break;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                zza(httpURLConnection, zzp);
                break;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                break;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                break;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                break;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                break;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                zza(httpURLConnection, zzp);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (httpURLConnection.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        BasicStatusLine basicStatusLine = new BasicStatusLine(protocolVersion, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage());
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(basicStatusLine);
        int method = zzp.getMethod();
        int statusCode = basicStatusLine.getStatusCode();
        if ((method == 4 || (100 <= statusCode && statusCode < 200) || statusCode == 204 || statusCode == 304) ? false : true) {
            basicHttpResponse.setEntity(zza(httpURLConnection));
        }
        for (Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }
}
