package com.business.intelligence.service.controller;

import com.business.intelligence.service.model.PersonShifts;
import com.business.intelligence.service.model.QrEntity;
import com.business.intelligence.service.repository.PersonDayShiftRepository;
import com.business.intelligence.service.service.QrCodeService;
import com.business.intelligence.service.utils.JsonUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.time.Instant;

import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/qr")
public class QrController {

    final PersonDayShiftRepository personDayShiftRepository;
    final QrCodeService qrCodeService;

    public QrController(final PersonDayShiftRepository personDayShiftRepository,
                        final QrCodeService qrCodeService) {
        this.personDayShiftRepository = personDayShiftRepository;
        this.qrCodeService = qrCodeService;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getCurrent(@RequestHeader final HttpHeaders headers) throws Exception {

        final QrEntity qr = new QrEntity();
        qr.setProjectId(100500);
        qr.setPassword("password");
        qr.setTime(Instant.now());

        String medium = JsonUtils.getJsonMapper().writeValueAsString(qr);

        byte[] image = getQRCodeImage(medium, 250, 250);
        return ResponseEntity.ok(image);
    }

    @RequestMapping(value = "/work-register", method = RequestMethod.POST, produces = IMAGE_PNG_VALUE)
    public ResponseEntity<?> scan(@RequestHeader final HttpHeaders headers,
                                  @RequestBody QrEntity qrEntity) throws Exception {
        final Integer userID = Integer.valueOf(headers.get("USER_ID").get(0));

        if (!qrCodeService.getPassword().equals(qrEntity.getPassword())) {
            return ResponseEntity.badRequest().body("Old qr code");
        }
        personDayShiftRepository.save(new PersonShifts(-1, userID, Instant.now()));

        return ResponseEntity.ok().build();
    }

    private static byte[] getQRCodeImage(String text, int width, int height) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0x00000000);

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
        return pngOutputStream.toByteArray();
    }
}
