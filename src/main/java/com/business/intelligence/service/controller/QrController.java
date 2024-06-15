package com.business.intelligence.service.controller;

import com.business.intelligence.service.model.shifts.PersonShifts;
import com.business.intelligence.service.model.shifts.QrEntity;
import com.business.intelligence.service.repository.PersonShiftsRepository;
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

    final PersonShiftsRepository personDayShiftRepository;
    final QrCodeService qrCodeService;

    public QrController(final PersonShiftsRepository personDayShiftRepository,
                        final QrCodeService qrCodeService) {
        this.personDayShiftRepository = personDayShiftRepository;
        this.qrCodeService = qrCodeService;
    }

    @RequestMapping(value = "/current/{projectId}", method = RequestMethod.GET, produces = IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getCurrent(@RequestHeader final HttpHeaders headers, @PathVariable("projectId") final int projectId) throws Exception {

        final QrEntity qr = new QrEntity();
        qr.setProjectId(projectId);
        qr.setPassword(qrCodeService.getPassword());
        qr.setTime(Instant.now());

        final Integer userID = Integer.valueOf(headers.get("USER_ID").get(0));
        personDayShiftRepository.save(new PersonShifts(-1, false, userID, qr.getTime()));
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
        personDayShiftRepository.save(new PersonShifts(-1, true, userID, qrEntity.getTime()));

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
