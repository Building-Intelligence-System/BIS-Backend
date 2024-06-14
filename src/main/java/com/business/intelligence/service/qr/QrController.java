package com.business.intelligence.service.qr;

import com.business.intelligence.service.utils.JsonUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.time.Instant;

import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/qr")
public class QrController {

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getById(@RequestHeader final HttpHeaders headers) throws Exception {

        final QrEntity qr = new QrEntity();
        qr.setBuildingId(100500);
        qr.setPassword("password");
        qr.setTime(Instant.now());

        String medium = JsonUtils.getJsonMapper().writeValueAsString(qr);

        byte[] image = getQRCodeImage(medium, 250, 250);
        return ResponseEntity.ok(image);

    }

    private static byte[] getQRCodeImage(String text, int width, int height) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0x00000000);

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }

}
