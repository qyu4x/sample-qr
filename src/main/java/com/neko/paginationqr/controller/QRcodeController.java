package com.neko.paginationqr.controller;

import com.google.zxing.WriterException;
import com.neko.paginationqr.repository.QRcodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/")
public class QRcodeController {

    private static final String QR_CODE_IMAGE_PATH = "D:/Development/LEARN/spring-boot/pagination-qr/src/main/resources/static/QRCode.png";

    private final QRcodeGeneratorService qRcodeGeneratorService;

    @Autowired
    public QRcodeController(QRcodeGeneratorService qRcodeGeneratorService) {
        this.qRcodeGeneratorService = qRcodeGeneratorService;
    }

    @GetMapping("/qr")
    public String getQRCode(Model model){
        String kaguya = "https://github.com/qyu4x";
        String ai ="Hello, i love you - with love from ai hayasaka";

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = qRcodeGeneratorService.getQRCodeImage(kaguya,250,250);

            // Generate and Save Qr Code Image in static/image folder
            qRcodeGeneratorService.genereateQRCodeImage(ai,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException exception) {
            exception.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("medium",kaguya);
        model.addAttribute("github",ai);
        model.addAttribute("qrcode",qrcode);

        return qrcode;
    }
}

