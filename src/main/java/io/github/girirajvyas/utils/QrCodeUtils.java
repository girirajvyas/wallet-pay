package io.github.girirajvyas.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QrCodeUtils {

  private static final String DEFAULT_PATH = "src/main/resources/images/output/";

  private QrCodeUtils() {

  }

  public static byte[] createQRCodeDefaultSize(String qrContent) {
    // return createQRCode(qrContent, 500, 500);
    // return createQRCodeColoured(qrContent, 500, 500, Color.BLUE, Color.GREEN);
    return createQRCodeColouredWithLogo(qrContent, 500, 500, Color.CYAN, Color.WHITE);
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

  public static byte[] createQRCodeColoured(String qrContent, int width, int height, Color onColor,
      Color offColor) {
    try {
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, width, height);
      // Color is set here in ARGB value
      log.info("QR code colours int value: {} , {}", onColor.getRGB(), offColor.getRGB());
      MatrixToImageConfig conf = new MatrixToImageConfig(onColor.getRGB(), offColor.getRGB());
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream, conf);
      return byteArrayOutputStream.toByteArray();
    } catch (WriterException | IOException e) {
      log.error(e.getMessage(), e);
      return new byte[0];
    }
  }

  public static byte[] createQRCodeColouredWithLogo(String qrContent, int width, int height,
      Color onColor, Color offColor) {
    try {
      // Create new configuration that specifies the error correction
      Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
      hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      BitMatrix bitMatrix =
          qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, width, height, hints);
      // Color is set here in ARGB value
      log.info("QR code colours int value: {} , {}", onColor.getRGB(), offColor.getRGB());
      MatrixToImageConfig conf = new MatrixToImageConfig(onColor.getRGB(), offColor.getRGB());

      // Load QR image
      BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, conf);

      // Load LOGO image
      BufferedImage logo = ImageIO.read(new File("src/main/resources/images/logo/gpay.png"));

      // Calculate the delta height and width between QR code and logo
      int deltaHeight = qrImage.getHeight() - logo.getHeight();
      int deltaWidth = qrImage.getWidth() - logo.getWidth();

      // Initialize combined image
      BufferedImage combined =
          new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
      Graphics2D combinedGraphics2d = (Graphics2D) combined.getGraphics();

      // Write QR code to new image at position 0/0
      combinedGraphics2d.drawImage(qrImage, 0, 0, null);
      combinedGraphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

      // Write logo into combine image at position (deltaWidth / 2) and
      // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
      // the same space for the logo to be centered
      combinedGraphics2d.drawImage(logo, (int) Math.round(deltaWidth / 2),
          (int) Math.round(deltaHeight / 2), null);

      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      // MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream, conf);
      // return byteArrayOutputStream.toByteArray();

      // Write combined image as PNG to OutputStream
      ImageIO.write(combined, "png", byteArrayOutputStream);

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

  private static void getArgbColourCode() {
    System.err.println("Color=" + new java.awt.Color(0, 0, 255, 0).getRGB());
    // gives 255 as you expected - note that this is a fully transparent blue

    System.err.println("Color=" + Color.RED.getRGB());
    // gives -65536, as the alpha channel value is 255 making the int negative.
  }


}
