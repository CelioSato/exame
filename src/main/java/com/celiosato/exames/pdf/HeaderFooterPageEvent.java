package com.celiosato.exames.pdf;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

	public void onStartPage(PdfWriter writer, Document document) {

		PdfPTable header = new PdfPTable(new float[] { 50f, 40f, 50f });

		header.setTotalWidth(50);

		Font headerFont = FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.BLUE);

		DateFormat sf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		Date data = new Date();

		PdfPCell cell2 = new PdfPCell(new Phrase());
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setVerticalAlignment(Element.ALIGN_TOP);
		cell2.setBorder(0);
		cell2.setBackgroundColor(BaseColor.PINK);
		header.addCell(cell2);

		header.writeSelectedRows(0, 70, 20, 760, writer.getDirectContent());

		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
				new Phrase("ACESSO DO PACIENTE - Último acesso:  " + sf.format(data), headerFont), 155, 780, 0);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Right"), 540, 780,
				0);

	}

	public void onEndPage(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
				new Phrase("http://www.sato.com.br"), 90, 50, 0);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
				new Phrase("página " + document.getPageNumber()), 540, 50, 0);
	}

}

