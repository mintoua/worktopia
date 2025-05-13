package com.logonedigital.worktopia.common;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.logonedigital.worktopia.paie.PaySlipRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalTime;

@Service
public class PdfService {
    @Value("${application.upload-payslip-dir}")
    private String PAY_DIR;

    public String generatePaySlipPdf(PaySlipRequest slipRequest) throws FileNotFoundException, DocumentException {
        int var10000 = LocalTime.now().getMinute();
        String fileName = "Payslip_" + var10000 + LocalTime.now().getNano() + slipRequest.getEmployee().getFullName() + ".pdf";
        File directory = new File(this.PAY_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = this.PAY_DIR + File.separator + fileName;
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 30.0F, 1);
        Paragraph title = new Paragraph("Bulletin de Paie", titleFont);
        title.setAlignment(1);
        document.add(title);
        document.add(new Paragraph("\nFullName: " + slipRequest.getEmployee().getFullName()));
        document.add(new Paragraph("Email: " + slipRequest.getEmployee().getEmail()));
        document.add(new Paragraph("Phone: " + slipRequest.getEmployee().getPhone()));
        document.add(new Paragraph("Address: " + slipRequest.getEmployee().getAddress()));
        document.add(new Paragraph("Department: " + String.valueOf(slipRequest.getEmployee().getDepartment())));
        document.add(new Paragraph("Position: " + slipRequest.getEmployee().getPosition()));
        document.add(new Paragraph("\n"));
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100.0F);
        table.setSpacingBefore(10.0F);
        table.setSpacingAfter(10.0F);
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 11.0F, 1, BaseColor.WHITE);
        PdfPCell cell1 = new PdfPCell(new Phrase("Base Salary", headerFont));
        cell1.setBackgroundColor(new BaseColor(70, 130, 180));
        table.addCell(cell1);
        table.addCell(slipRequest.getBaseSalary().toString());
        PdfPCell cell3 = new PdfPCell(new Phrase("Bonus", headerFont));
        cell3.setBackgroundColor(new BaseColor(26, 54, 59));
        table.addCell(cell3);
        table.addCell(slipRequest.getBonus().toString());
        PdfPCell cell5 = new PdfPCell(new Phrase("Deduction", headerFont));
        cell5.setBackgroundColor(new BaseColor(26, 54, 59));
        table.addCell(cell5);
        table.addCell(slipRequest.getDeduction().toString());
        PdfPCell cell7 = new PdfPCell(new Phrase("Net Salary", headerFont));
        cell7.setBackgroundColor(new BaseColor(26, 54, 59));
        table.addCell(cell7);
        table.addCell(String.valueOf(slipRequest.getBaseSalary() + slipRequest.getBonus() - slipRequest.getDeduction()));
        document.add(table);
        Paragraph signature = new Paragraph();
        signature.setAlignment(2);
        signature.add(new Chunk("Directeur", new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 1)));
        signature.add(Chunk.NEWLINE);
        signature.add(new Chunk(" _                                  _ _       _ _        _\n| | ___   __ _  ___  _ __   ___  __| (_) __ _(_) |_ __ _| |\n| |/ _ \\ / _` |/ _ \\| '_ \\ / _ \\/ _` | |/ _` | | __/ _` | |\n| | (_) | (_| | (_) | | | |  __/ (_| | | (_| | | || (_| | |\n|_|\\___/ \\__, |\\___/|_| |_|\\___|\\__,_|_|\\__, |_|\\__\\__,_|_|\n         |___/                          |___/", new Font(Font.FontFamily.TIMES_ROMAN, 12.0F)));
        document.add(signature);
        document.close();
        return filePath;
    }
}
