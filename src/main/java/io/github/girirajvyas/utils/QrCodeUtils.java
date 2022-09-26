package io.github.girirajvyas.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QrCodeUtils {

  private static final String DEFAULT_PATH = "src/main/resources/output/";

  private QrCodeUtils() {

  }

  public static byte[] createQRCodeDefaultSize(String qrContent) {
    return createQRCode(qrContent, 500, 500);
  }

  public static byte[] createQRCode(String qrContent, int width, int height) {
    try {
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, width, height);
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
      return byteArrayOutputStream.toByteArray();
    } catch (WriterException | IOException e) {
      log.error(e.getMessage(), e);
      return new byte[0];
    }
  }

  public static String decodeQRCode(byte[] qrCodeBytes) {
    try {
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(qrCodeBytes);
      BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
      BufferedImageLuminanceSource bufferedImageLuminanceSource =
          new BufferedImageLuminanceSource(bufferedImage);
      HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
      BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
      MultiFormatReader multiFormatReader = new MultiFormatReader();
      Result result = multiFormatReader.decode(binaryBitmap);

      return result.getText();
    } catch (NotFoundException | IOException e) {
      log.error(e.getMessage(), e);
    }

    return null;
  }

  public static void createQRCodeFile(byte[] qrCode, String fileName) {
    String fileNameWithExtension = getFileNameWithJpegExtension(fileName);

    try (FileOutputStream fos =
        new FileOutputStream(new File(DEFAULT_PATH + fileNameWithExtension))) {
      fos.write(qrCode);
      log.info("{} File created ", fileNameWithExtension);
    } catch (IOException exception) {
      log.error("File cannot be created");
    }
  }

  public static String decodeQRCodeFile(String fileName) {
    byte[] byteArray = readQRCodeFile(fileName);
    String decodedQR = decodeQRCode(byteArray);
    log.info("Decoded URL:" + decodedQR);
    return decodedQR;
  }

  public static byte[] readQRCodeFile(String fileName) {
    String fileNameWithExtension = getFileNameWithJpegExtension(fileName);
    ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();

    try {
      BufferedImage image = ImageIO.read(new File(DEFAULT_PATH + fileNameWithExtension));
      ImageIO.write(image, "jpg", outStreamObj);
    } catch (IOException exception) {
      log.error("File cannot be created");
    }
    return outStreamObj.toByteArray();
  }

  private static String getFileNameWithJpegExtension(String fileName) {
    return fileName + ".jpeg";
  }

}
