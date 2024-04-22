package rsi.ticketswebservice.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import rsi.ticketswebservice.dto.TicketDto;
import rsi.ticketswebservice.entities.Ticket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfService {
    public static byte[] GenerateBookingConfirmationPdf(TicketDto ticket) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.TIMES_BOLD, 24);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Flight Booking Confirmation");
                contentStream.endText();

                // Add a divider
                contentStream.setLineWidth(1f);
                contentStream.moveTo(50, 680);
                contentStream.lineTo(550, 680);
                contentStream.stroke();

                contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 650);
                contentStream.showText("Ticket ID: " + ticket.getId());
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Passenger: " + ticket.getPassengerFirstName() + " " + ticket.getPassengerSurname());
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Departure City: " + ticket.getFlight().getDepartureCity().getName());
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Arrival City: " + ticket.getFlight().getArrivalCity().getName());
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Departure Time: " + LocalDateTime.parse(ticket.getFlight().getDepartureTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                contentStream.endText();

                // Add another divider
                contentStream.setLineWidth(1f);
                contentStream.moveTo(50, 500);
                contentStream.lineTo(550, 500);
                contentStream.stroke();
            }

            try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                document.save(output);
                return output.toByteArray();
            }
        }
    }
}
