package cn.ldp.mail;

import org.junit.jupiter.api.Test;
import org.apache.commons.codec.binary.Base64;
import sun.misc.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Demo1 {
    @Test
    public void fun1() throws IOException {
        //BASE64编码
        String s = "13271315317";
        BASE64Encoder encoder = new BASE64Encoder();
        String str = encoder.encode(s.getBytes("utf-8"));
        System.out.println(str);

        //BASE64解码
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(str);
        System.out.println(new String(bytes, "UTF-8"));
    }

    @Test
    void fun2() throws UnsupportedEncodingException {
        String s = "13271315317";
        byte[] bytes = Base64.encodeBase64(s.getBytes("utf-8"));
        s = new String(bytes, "utf-8");
        System.out.println(s);
        bytes = s.getBytes("utf-8");
        bytes = Base64.decodeBase64(bytes);
        s = new String(bytes, "utf-8");
        System.out.println(s);
    }

    @Test
    void test3() throws MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.163.com");//设置服务器
        properties.setProperty("mail.smtp.auth", "true");//设置服务器登录是否要认证
        //创建认证器
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("wo2008shiji", "heib683700");
            }
        };
        Session session = Session.getInstance(properties, authenticator);
        /**
         * 创建MImeMessage
         */
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress("wo2008shiji@163.com"));//设置发件人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("690150057@qq.com"));//设置收件人
//        mimeMessage.addRecipient(MimeMessage.RecipientType.CC,new InternetAddress(""));//抄送CC
//        mimeMessage.addRecipient(MimeMessage.RecipientType.BCC,new InternetAddress(""));//暗送BCC
        mimeMessage.setSubject("这是主题");
        mimeMessage.setContent("这是一个测试邮件", "text/html;charset=utf-8");

        Transport.send(mimeMessage);
    }


    @Test
    void test4() throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.163.com");//设置服务器
        properties.setProperty("mail.smtp.auth", "true");//设置服务器登录是否要认证
        //创建认证器
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("wo2008shiji", "heib683700");
            }
        };
        Session session = Session.getInstance(properties, authenticator);
        /**
         * 创建MImeMessage
         */
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress("wo2008shiji@163.com"));//设置发件人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("690150057@qq.com"));//设置收件人
        mimeMessage.setSubject("还是一个测试邮件");

        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMessage.setContent(mimeMultipart);
        MimeBodyPart part1 = new MimeBodyPart();
        part1.setContent("又是一个测试邮件", "text/html;charset=utf-8");
        MimeBodyPart part2 = new MimeBodyPart();
        part2.attachFile(new File("C:\\Users\\69015\\Desktop\\架构.png"));
        part2.setFileName(MimeUtility.encodeText("呵呵.png"));
        mimeMultipart.addBodyPart(part1);
        mimeMultipart.addBodyPart(part2);

        Transport.send(mimeMessage);
    }

}
