package co.sofka.appservice.encryption;

import javax.crypto.SecretKey;

public interface KeyPort {
    String encodeKey(SecretKey key);
    SecretKey decodeKey(String encodedKey);
}
