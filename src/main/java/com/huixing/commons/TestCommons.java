package com.huixing.commons;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.*;

import javax.net.ssl.SSLContext;

/**
 *
 */
public class TestCommons {
    @Test
    public void testStringUtils() {
        String str1 = "str1";
        String str2 = "str2";
        List<String> strings = Arrays.asList("111", "222", "333");

        StringUtils.isBlank(str1);
        StringUtils.isEmpty(str2);
        System.out.println("equals:" + StringUtils.equals(str1, str2));
        System.out.println("left:" + StringUtils.left(str1, 2)); // 截取左边2个字符
        System.out.println("leftPad ' ':" + StringUtils.leftPad(str1, 5)); // 左边补空格
        System.out.println("leftPad '*':" + StringUtils.leftPad(str1, 5, "*")); // 左边补*
        System.out.println("join:" + StringUtils.join(strings, "-"));

        System.out.println(StringEscapeUtils.unescapeJava("\u4E2D\u6587"));// unicode 转中文
        System.out.println(StringEscapeUtils.escapeJava("中文"));// 中文转unicode
    }

    @Test
    public void testExceptionUtils() {
        Exception e = new Exception();
        System.out.println(ExceptionUtils.getStackTrace(e));// 获取异常堆栈信息
        ExceptionUtils.getRootCauseMessage(e);
        ExceptionUtils.getMessage(e);
    }

    @Test
    public void testRandomUtils() {
        System.out.println(RandomUtils.nextInt(100000, 999999)); // 随机数生成器
        System.out.println(RandomUtils.nextLong(1, 10));
        System.out.println(RandomUtils.nextDouble(1, 10));
    }

    @Test
    public void testCollectionUtils() {
        Collection collections = CollectionUtils.EMPTY_COLLECTION;
        System.out.println(CollectionUtils.isEmpty(collections)); // 集合判断空值
    }

    @Test
    public void testMapUtils() {
        Map map = new HashMap();
        map.put("key", "value");
        System.out.println(MapUtils.isEmpty(map)); // map 判断空
        Object key = "key";
        System.out.println(MapUtils.getString(map, key));
    }

    @Test
    public void testDigestUtils() throws Exception {
        System.out.println(DigestUtils.md5Hex("123456"));// md5
        // jdk8 之前, 默认是 UTF-8
        String encodeBase64String = org.apache.commons.codec.binary.Base64.encodeBase64String("123456".getBytes());
        byte[] decodeBase64 = org.apache.commons.codec.binary.Base64.decodeBase64(encodeBase64String);

        System.out.println(encodeBase64String);
        System.out.println(new String(decodeBase64, StandardCharsets.UTF_8));


        // jdk8 默认是 iso-8859-1
        encodeBase64String = Base64.getEncoder().encodeToString("123456".getBytes());
        decodeBase64 = Base64.getDecoder().decode(encodeBase64String);
        System.out.println(encodeBase64String);
        System.out.println(new String(decodeBase64, Charset.forName("UTF-8")));
    }

    @Test
    public void testBeanUtils() throws Exception {
        Object orig = new Object();
        Object dest = new Object();
        Map map = MapUtils.EMPTY_MAP;

        BeanUtils.copyProperties(dest, orig);
        BeanUtils.populate(dest, map);
    }

}
