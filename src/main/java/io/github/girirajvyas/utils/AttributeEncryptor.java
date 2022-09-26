package io.github.girirajvyas.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AttributeEncryptor implements AttributeConverter<String, String> {

  private static final String CIPHER = "AES";

  //key sizes of 16, 24 or 32 bytes
  @Value("${app.encryption.secret: secret-key-12345}")
  private String secret;

  @Override
  public String convertToDatabaseColumn(String attribute) {
    if (StringUtils.isNotBlank(attribute)) {
      try {
        Cipher cipher = Cipher.getInstance(CIPHER);
        Key key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), CIPHER);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes(StandardCharsets.UTF_8)));
      } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
          | IllegalBlockSizeException | BadPaddingException e) {
        log.error("Exception occured while encoding: ", e);
        throw new IllegalStateException(e);
      }
    }

    return attribute;
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    
    try {
    if(StringUtils.isNotBlank(dbData)) {
      Cipher cipher = Cipher.getInstance(CIPHER);
      Key key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), CIPHER);
      cipher.init(Cipher.DECRYPT_MODE, key);
      return new String(cipher.doFinal(Base64.getDecoder().decode(dbData))
          ,StandardCharsets.UTF_8);
    }
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
        | IllegalBlockSizeException | BadPaddingException e) {
      log.error("Exception occured while encoding: ", e);
      throw new IllegalStateException(e);
    }
    
    return dbData;
  }


}
