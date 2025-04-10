package com.hmsapp.service;

import com.hmsapp.entity.Booking;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

@Service
public class PDFGenerator {
    public void generatePdf(String path, Booking booking ){
        try {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Greeting paragraph
            document.add(new Paragraph("Hello, Sajesh! This is your PDF with custom column widths."));

            // Define custom column widths: First column = 2x wider than second
            float[] columnWidths = {2, 3};
            Table table = new Table(columnWidths);

            // Add header cells
            table.addHeaderCell("Name");        // wider column
            table.addHeaderCell("No of Days");  // narrower column

            // Add data rows
            table.addCell(booking.getGuestName());
            table.addCell(booking.getMobile());

            table.addCell("Rosh");
            table.addCell("3");


            // Add the table to the document
            document.add(table);

            // Close the document
            document.close();
            System.out.println("PDF with custom column widths created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
