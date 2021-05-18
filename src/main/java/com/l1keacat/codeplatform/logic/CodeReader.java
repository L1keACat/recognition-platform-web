package com.l1keacat.codeplatform.logic;

import com.l1keacat.codeplatform.model.RootObject;
import com.google.gson.Gson;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class CodeReader {

    public String decodeCode(File Codeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(Codeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            String json_result;
            Result result = new MultiFormatReader().decode(bitmap);
            System.out.println(result.getBarcodeFormat());
            switch (result.getBarcodeFormat()) {
                case AZTEC:
                case DATA_MATRIX:
                case QR_CODE:
                case MAXICODE:
                case RSS_14:
                case RSS_EXPANDED:
                case PDF_417:
                    json_result = "{\"type_format\": \"" + result.getBarcodeFormat() + "\"," +
                            "\"type_dimension\": \"2D\"," +
                            "\"content\": \"" + result.getText() + "\"" +
                            "}";
                    break;
                default:
                    json_result = "{\"type_format\": \"" + result.getBarcodeFormat() + "\"," +
                            "\"type_dimension\": \"1D\"," +
                            "\"content\": \"" + result.getText() + "\"" +
                            "}";
            }
            return json_result;
        } catch (NotFoundException e) {
            System.out.println("There is no code in the image");
            return null;
        }
    }

    public String getInfoByCode(String code) {
        String exception_msg;
        try {
            String apiKey = "ldar0jik7cp8ojrpolmgplj17szouf";
            URL url = new URL("https://api.barcodelookup.com/v2/products?barcode=" + code + "&formatted=y&key=" + apiKey);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            StringBuilder data = new StringBuilder();
            while (null != (str = br.readLine())) {
                data.append(str);
                //System.out.println(str);
            }

            /*Gson g = new Gson();

            RootObject value = g.fromJson(String.valueOf(data), RootObject.class);
            String barcode = value.products[0].barcode_number;
            System.out.print("Barcode Number: ");
            System.out.println(barcode);

            String name = value.products[0].product_name;
            System.out.print("Product Name: ");
            System.out.println(name);

            System.out.println("Gson = " + value);*/
            return data.toString();
        } catch (FileNotFoundException e) {
            exception_msg = "Product hasn't been found. There is no product with such code in database.";
            System.out.print(e);
            return "{\"products\":[" +
                    "{" +
                    "\"barcode_number\":\"" + code + "\"," +
                    "\"error\":\"" + exception_msg + "\"" +
                    "}" +
                    "]}";
        } catch (Exception e) {
            exception_msg = "Internal server error.";
            System.out.print(e);
            return "{\"products\":[" +
                    "{" +
                    "\"barcode_number\":\"" + code + "\"," +
                    "\"error\":\"" + exception_msg + "\"" +
                    "}" +
                    "]}";
        }

    }
}
