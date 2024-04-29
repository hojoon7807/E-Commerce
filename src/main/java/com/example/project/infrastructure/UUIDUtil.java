package com.example.project.infrastructure;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UUIDUtil {

  public static String encodeToShort(UUID uuid) {
    ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
    byteBuffer.putLong(uuid.getMostSignificantBits());
    byteBuffer.putLong(uuid.getLeastSignificantBits());
    return Base64.getUrlEncoder().withoutPadding().encodeToString(byteBuffer.array());
  }

  public static UUID decodeFromShort(String shortUuid) {
    byte[] bytes = Base64.getUrlDecoder().decode(shortUuid);
    ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    long mostSignificantBits = byteBuffer.getLong();
    long leastSignificantBits = byteBuffer.getLong();
    return new UUID(mostSignificantBits, leastSignificantBits);
  }

  private UUIDUtil() {
  }
}
