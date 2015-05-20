import org.apache.commons.codec.binary.Base64;

public class Base64Converter {

public String encodetoBase64(String keyString){

    return( new String(Base64.encodeBase64(keyString.getBytes())));

    }

public String encodetoBase64(byte[] keyByteString){

    return( new String(Base64.encodeBase64(keyByteString)));

    }
    

    public String decodeBase64ToString(String base64){

    return( new String(Base64.decodeBase64(base64.getBytes())));

    }

    public byte[] decodeBase64ToByte(String base64){

    return( Base64.decodeBase64(base64.getBytes()));

    }


}