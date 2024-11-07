package co.sofka.gateway.encryption;

public interface CipherPort {
    String encrypt(String plainText) throws Exception;
    String decrypt(String cipherText) throws Exception;
}
