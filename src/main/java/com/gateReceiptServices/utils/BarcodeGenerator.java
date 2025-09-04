package com.gateReceiptServices.utils;

import com.gateReceiptServices.configuration.LoginUser;
import com.gateReceiptServices.model.SerialBatchNumber;
import com.gateReceiptServices.repository.CodeMapperRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;

@Component
public class BarcodeGenerator {
    @Autowired
    LoginUser loginUser;

    @Autowired
    CodeMapperRepository codeMapperRepository;
    public static String generateRandomBarcode() {
        String regex = "^[0-9A-Z]{7}[0-9]{3}$";
        Random random = new Random();
        StringBuilder barcode = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int randomCharIndex = random.nextInt(36); // Numbers (0-9) + Uppercase Letters (A-Z)
            char randomChar = (char) (randomCharIndex < 10 ? '0' + randomCharIndex : 'A' + randomCharIndex - 10);
            barcode.append(randomChar);
        }
        for (int i = 0; i < 3; i++) {
            int randomDigit = random.nextInt(10);
            barcode.append(randomDigit);
        }
        return barcode.toString();
    }

    public static byte[] generateBarcode(String content) {
        try {
            int width = 300;
            int barcodeHeight = 100; // Fixed height for the barcode
            int spaceBelowBarcode = 30; // Fixed space below the barcode for content
            int totalHeight = barcodeHeight + spaceBelowBarcode;

            // Generate barcode image
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.PDF_417, width, barcodeHeight);
            BufferedImage barcodeImage = bitMatrixToImage(bitMatrix);

            // Create a new image with fixed height
            BufferedImage combined = new BufferedImage(width, totalHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = combined.createGraphics();

            // Draw the barcode image
            graphics.drawImage(barcodeImage, 0, 0, null);

            // Add the barcode string below the barcode
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 10));
            FontMetrics fontMetrics = graphics.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(content);

            // Calculate the x position for centering the content below the barcode
            int xPosition = (width - stringWidth) / 2;

            // Draw the content below the barcode
            graphics.drawString(content, xPosition, barcodeHeight + fontMetrics.getHeight());

            // Dispose of the graphics object
            graphics.dispose();

            // Write the combined image to ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(combined, "png", outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage bitMatrixToImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0x000000 : 0xFFFFFF);
            }
        }
        return image;
    }

    public static String getRandomNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(90000000) + 10000000);
    }

    public static Integer getRemainingDays(Date requiredDate) {
        // Convert java.sql.Date to java.util.Date
        java.util.Date utilDate = new java.util.Date(requiredDate.getTime());

        // Convert java.util.Date to java.time.LocalDate
        Instant instant = utilDate.toInstant();
        LocalDate requiredLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        // Get today's date
        LocalDate today = LocalDate.now();

        // Calculate the difference in days
        long daysDifference = ChronoUnit.DAYS.between(today, requiredLocalDate);

        // Ensure the result is positive (if requiredDate is in the future)
        return Math.toIntExact(Math.max(0, daysDifference));
    }

    public static byte[] generatePdfWithBarcodes(List<SerialBatchNumber> serialNumbers) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();

            // Add barcode images with serial numbers
            for (SerialBatchNumber serialNumber : serialNumbers) {
                // Add barcode image
                Image barcodeImage = createBarcodeImage(serialNumber.getSerialBatchNumber());
                barcodeImage.setAlignment(Element.ALIGN_CENTER);
                document.add(barcodeImage);

                // Add some spacing between barcode images
                document.add(Chunk.NEWLINE);
            }

            document.close();
            return outputStream.toByteArray();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Image createBarcodeImage(String data) throws DocumentException, IOException {
        // Use the generateBarcode method
        byte[] barcodeBytes = generateBarcode(data);

        // Create an Image object from the generated barcode image
        Image image = Image.getInstance(barcodeBytes);
        image.scalePercent(50); // Adjust the scale as needed
        image.setAlignment(Element.ALIGN_CENTER);
        return image;
    }

    public String getCinNumber() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        Long currentCounter = codeMapperRepository.countBySubOrganizationIdAndIsDeletedAndCreatedOnYearAndCreatedOnMonth(loginUser.getSubOrgId(), false, year, month);

        String cinSection = "CIN";
        String dateSection = today.format(DateTimeFormatter.ofPattern("MM-yyyy"));
        String counterSection = String.format("%04d", currentCounter+1);

        return String.format("%s-%s-%s-%s", loginUser.getSubOrganizationCode(), cinSection, dateSection, counterSection);
    }
}
