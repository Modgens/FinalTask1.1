package com.example.tt.services;

import com.example.tt.models.entities.Group;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PdfGenerator {

    private final Calculator calculator;

    public ByteArrayInputStream generatePdf(Group group) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            writer.getAcroForm().setNeedAppearances(true);

            // Завантажити файл кириличного шрифта, наприклад, DejaVuSans.ttf
            String fontPath = "C:\\Users\\Modgen\\IdeaProjects\\TT\\DejaVuSans.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            float fontSize = 12.0f;
            Font font = new Font(baseFont, fontSize, Font.NORMAL);

            document.open();

            addTextToPdf(document, "Готельний комплекс MyHotel Форма № 4-г", font);
            addTextToPdf(document, "Готель: МійГотель", font);
            addTextToPdf(document, "Адреса: м. Дніпро, Дніпропетровська область.", font);
            addTextToPdf(document, "№ кімнати: " + group.getRoom().getNumber(), font);
            addTextToPdf(document, "П. І. Б. клієнта: " + group.getMainVisitant().getLastName() +
                    " " + group.getMainVisitant().getFirstName() + " " + group.getMainVisitant().getMiddleName(), font);
            addTextToPdf(document, "Ціна проживання за день: " + group.getRoom().getPrice() + " грн", font);
            addTextToPdf(document, "Прибув: " + formatDate(group.getDateIn()), font);
            addTextToPdf(document, "Вибув: " + formatDate(group.getDateOut()), font);
            addTextToPdf(document, "Сума до сплати в грн: " +
                    calculator.getTotalAmount(group.getDateIn(), group.getDateOut(), group.getRoom().getPrice()), font);

            document.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static void addTextToPdf(Document document, String text, Font font) {
        try {
            document.add(new Paragraph(text, font));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return formatter.format(date);
    }
}
