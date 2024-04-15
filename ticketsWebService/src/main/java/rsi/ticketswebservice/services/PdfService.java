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

public class PdfService {
    public static byte[] GenerateBookingConfirmationPdf(TicketDto ticket) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("Flight Booking Confirmation");
                contentStream.newLineAtOffset(0, -50);
                contentStream.setFont(PDType1Font.HELVETICA, 16);
                contentStream.showText("Ticket ID: " + ticket.getId());
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Passenger: " + ticket.getPassengerFirstName() + " " + ticket.getPassengerSurname());
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Flight ID: " + ticket.getFlight().getId());
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Departure City: " + ticket.getFlight().getDepartureCity().getName());
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Arrival City: " + ticket.getFlight().getArrivalCity().getName());
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Departure Time: " + ticket.getFlight().getDepartureTime());
                contentStream.endText();
            }

            try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                document.save(output);
                return output.toByteArray();
            }
        }
    }
}
