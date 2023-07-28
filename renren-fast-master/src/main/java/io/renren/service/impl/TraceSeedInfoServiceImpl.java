package io.renren.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.renren.dao.TraceSeedInfoDao;
import io.renren.entity.TraceSeedInfoEntity;
import io.renren.service.TraceSeedInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TraceSeedInfoDao;
import io.renren.entity.TraceSeedInfoEntity;
import io.renren.service.TraceSeedInfoService;

import javax.annotation.Resource;
import javax.imageio.ImageIO;


@Service("traceSeedInfoService")
public class TraceSeedInfoServiceImpl extends ServiceImpl<TraceSeedInfoDao, TraceSeedInfoEntity> implements TraceSeedInfoService {
    @Resource
    private TraceSeedInfoDao traceSeedInfoDao;
    @Value("${tracecode.path}")
    private String codePath;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TraceSeedInfoEntity> page = this.page(
                new Query<TraceSeedInfoEntity>().getPage(params),
                new QueryWrapper<TraceSeedInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, String>> getSeedInfoByCode(String code) {
        return traceSeedInfoDao.getSeedInfoByCode(code);
    }

    @Override
    public int updateTraceCode(String name, String producer) throws IOException, WriterException {
        System.out.println(name);

        System.out.println(producer);
        // 生成8位name码和8位producer码
        String nameCode = generateCode(name, 8);
        String producerCode = generateCode(producer, 8);

        // 生成8位时间码
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String timeCode = formatter.format(new Date());

        // 生成8位随机码
        String randomCode = generateRandomCode(8);

        // 组合生成32位防伪码
        String code = nameCode + producerCode + timeCode + randomCode;

        System.out.println(code);
        String filePath = codePath+"seed_platform_code/test01.png";
        System.out.println(filePath);
        convertToQRCode(code, filePath);

        return traceSeedInfoDao.updateTraceCode(name, producer,code,filePath);
    }

    public static String generateCode(String input, int length) {

        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] encodedBytes = Base64.getEncoder().encode(bytes);
        String encodedString = new String(encodedBytes, StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        int inputLength = encodedString.length();
        for (int i = 0; i < length; i++) {
            int index = i % inputLength;
            builder.append(encodedString.charAt(index));
        }
        return builder.toString();
    }

    public static String generateRandomCode(int length) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            builder.append(chars.charAt(index));
        }
        return builder.toString();
    }

    public static void convertToQRCode(String traceCode, String filePath) throws IOException, WriterException {
        // 设置二维码的大小
        int width = 300;
        int height = 300;

        // 设置二维码的参数
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(traceCode, BarcodeFormat.QR_CODE, width, height, getQRCodeHints());

        // 创建二维码图片
        BufferedImage qrCodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        qrCodeImage.createGraphics();
        Graphics2D graphics = (Graphics2D) qrCodeImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        // 将二维码图片保存到文件
        File qrCodeFile = new File(filePath);
        ImageIO.write(qrCodeImage, "png", qrCodeFile);
    }

    private static java.util.Map<EncodeHintType, Object> getQRCodeHints() {
        java.util.Map<EncodeHintType, Object> hints = new java.util.HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        return hints;
    }
}
