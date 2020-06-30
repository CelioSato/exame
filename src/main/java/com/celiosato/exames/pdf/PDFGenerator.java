package com.celiosato.exames.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.celiosato.exames.domain.Eritrograma;
import com.celiosato.exames.domain.Usuario;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator extends PdfPageEventHelper {

	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

	public static ByteArrayInputStream usuarioPDFReport(Usuario usuarios, Eritrograma eritrogramas) {

		Usuario usuario = usuarios;
		Eritrograma eritrograma = eritrogramas;

		// (PageSize.A4,esq, dir, sup, inf)
		Document document = new Document(PageSize.A4, 50, 50, 80, 80);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter writer = PdfWriter.getInstance(document, out);
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
			writer.setPageEvent(event);
			document.open();

			Font dadosFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLUE);
			Font negritoFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 10, BaseColor.BLACK);
			Font headerFont = FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.BLUE);
			Font headFont = FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.BLACK);
			Font blackFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
			Font timesItalica = FontFactory.getFont(FontFactory.TIMES_ITALIC, 10, BaseColor.BLACK);
			Font whiteLine = FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.WHITE);

			PdfPTable tableCima = new PdfPTable(new float[] { 100f, 150f });
			tableCima.setTotalWidth(550); // largura da tabela em uma folha A4
			tableCima.setLockedWidth(true);

			PdfPCell pac = new PdfPCell(new Paragraph("PACIENTE: " + usuario.getNomeCompleto(), dadosFont));
			pac.setColspan(2);
			pac.setHorizontalAlignment(Element.ALIGN_LEFT);
			pac.setVerticalAlignment(Element.ALIGN_TOP);
			pac.setBackgroundColor(BaseColor.WHITE);
			pac.setBorder(0);
			tableCima.addCell(pac);

			PdfPCell whiteCell = new PdfPCell(new Paragraph(".", whiteLine));
			whiteCell.setColspan(2);
			whiteCell.setPaddingTop(5);
			whiteCell.setBorder(0);
			tableCima.addCell(whiteCell);

			PdfPTable table = new PdfPTable(new float[] { 140f, 70f, 70f, 80f });
			table.setTotalWidth(550); // largura da tabela em uma folha A4
			table.setLockedWidth(true);

			PdfPCell texto = new PdfPCell(new Paragraph("EXAMES", headerFont));
			texto.setColspan(4);
			texto.setHorizontalAlignment(Element.ALIGN_CENTER);
			texto.setVerticalAlignment(Element.ALIGN_TOP);
			texto.setBackgroundColor(BaseColor.PINK);
			texto.setBorder(2);
			table.addCell(texto);

			PdfPCell whiteCell1 = new PdfPCell(new Paragraph(".", whiteLine));
			whiteCell1.setColspan(4);
			whiteCell1.setPaddingTop(10);
			whiteCell1.setBorder(0);
			table.addCell(whiteCell1);

			PdfPCell tableData = new PdfPCell(new Paragraph("HEMOGRAMA COMPLETO", negritoFont));
			tableData.setColspan(4);
			tableData.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableData.setVerticalAlignment(Element.ALIGN_RIGHT);
			tableData.setBorder(0);
			table.addCell(tableData);

			PdfPCell material = new PdfPCell(new Paragraph("MATERIAL: Sangue total", negritoFont));
			material.setColspan(2);
			material.setHorizontalAlignment(Element.ALIGN_LEFT);
			material.setVerticalAlignment(Element.ALIGN_RIGHT);
			material.setBorder(0);
			table.addCell(material);

			PdfPCell vlDeReferencia = new PdfPCell(new Paragraph("Valores de Referencia", timesItalica));
			vlDeReferencia.setColspan(1);
			vlDeReferencia.setHorizontalAlignment(Element.ALIGN_LEFT);
			vlDeReferencia.setVerticalAlignment(Element.ALIGN_RIGHT);
			vlDeReferencia.setBorder(0);
			table.addCell(vlDeReferencia);

			PdfPCell whiteCell0 = new PdfPCell(new Paragraph(".", whiteLine));
			whiteCell0.setColspan(4);
			whiteCell0.setPaddingTop(15);
			whiteCell0.setHorizontalAlignment(Element.ALIGN_CENTER);
			whiteCell0.setVerticalAlignment(Element.ALIGN_TOP);
			whiteCell0.setBorder(0);
			table.addCell(whiteCell0);

			PdfPCell heritrogramna = new PdfPCell(new Paragraph("ERITROGRAMA - SÉRIE VERMELHA", blackFont));
			heritrogramna.setColspan(4);
			heritrogramna.setPaddingTop(5);
			heritrogramna.setHorizontalAlignment(Element.ALIGN_LEFT);
			heritrogramna.setVerticalAlignment(Element.ALIGN_RIGHT);
			heritrogramna.setBorder(0);
			table.addCell(heritrogramna);
//--------------------------------------------------------------------------	

			PdfPCell eritrocitosCell = new PdfPCell(new Paragraph("Eritrocitos: ", headFont));
			eritrocitosCell.setColspan(1);
			eritrocitosCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			eritrocitosCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			eritrocitosCell.setBorderWidth(0);
			table.addCell(eritrocitosCell);

			PdfPCell eritrocitosBD = new PdfPCell(
			new Phrase(String.valueOf(eritrograma.getEritrocitos()) + " milhões/mm³", headFont));
			eritrocitosBD.setColspan(1);
			eritrocitosBD.setVerticalAlignment(Element.ALIGN_MIDDLE);
			eritrocitosBD.setHorizontalAlignment(Element.ALIGN_LEFT);
			eritrocitosBD.setBorderWidth(0);
			table.addCell(eritrocitosBD);

			PdfPCell eritrocitosVL = new PdfPCell(new Phrase("4,0 a 5,2 milhões/mm³", timesItalica));
			eritrocitosVL.setColspan(2);
			eritrocitosVL.setVerticalAlignment(Element.ALIGN_MIDDLE);
			eritrocitosVL.setHorizontalAlignment(Element.ALIGN_LEFT);
			eritrocitosVL.setBorderWidth(0);
			table.addCell(eritrocitosVL);
//--------------------------------------------------------------------------	

			PdfPCell hemoglobinaCell = new PdfPCell(new Paragraph("Hemoglobina: ", headFont));
			hemoglobinaCell.setColspan(1);
			hemoglobinaCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			hemoglobinaCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hemoglobinaCell.setBorderWidth(0);
			table.addCell(hemoglobinaCell);

			PdfPCell hemoglobinaBD = new PdfPCell(
					new Phrase(String.valueOf(eritrograma.getHemoglobina()) + " g/dl", headFont));
			hemoglobinaBD.setColspan(1);
			hemoglobinaBD.setVerticalAlignment(Element.ALIGN_MIDDLE);
			hemoglobinaBD.setHorizontalAlignment(Element.ALIGN_LEFT);
			hemoglobinaBD.setBorderWidth(0);
			table.addCell(hemoglobinaBD);

			PdfPCell hemoglobinaVL = new PdfPCell(new Phrase("12,0 a 16,0 g/dl", timesItalica));
			hemoglobinaVL.setColspan(2);
			hemoglobinaVL.setVerticalAlignment(Element.ALIGN_MIDDLE);
			hemoglobinaVL.setHorizontalAlignment(Element.ALIGN_LEFT);
			hemoglobinaVL.setBorderWidth(0);
			table.addCell(hemoglobinaVL);

//--------------------------------------------------------------------------

			PdfPCell obsCell = new PdfPCell(new Paragraph("Obs: " + String.valueOf(eritrograma.getObs()), headFont));
			obsCell.setColspan(4);
			obsCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			obsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			obsCell.setBorderWidth(0);
			table.addCell(obsCell);

			PdfPCell whiteCell2 = new PdfPCell(new Paragraph(".", whiteLine));
			whiteCell2.setColspan(4);
			whiteCell2.setPaddingTop(5);
			whiteCell2.setBorder(0);
			table.addCell(whiteCell2);
			
//*********************************************************************************

			PdfPCell leucogramna = new PdfPCell(new Paragraph("LEUCOGRAMA - SÉRIE BRANCA", blackFont));
			leucogramna.setColspan(4);
			leucogramna.setPaddingTop(5);
			leucogramna.setHorizontalAlignment(Element.ALIGN_LEFT);
			leucogramna.setVerticalAlignment(Element.ALIGN_RIGHT);
			leucogramna.setBorder(0);
			table.addCell(leucogramna);

			/*
			 * PdfPCell obsBD = new PdfPCell(new
			 * Phrase(String.valueOf(eritrograma.getObs()), headFont)); obsBD.setColspan(3);
			 * obsBD.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 * obsBD.setHorizontalAlignment(Element.ALIGN_LEFT); obsBD.setBorderWidth(0);
			 * table.addCell(obsBD);
			 * 
			 * PdfPCell obsVL= new PdfPCell(new Phrase(" 37 a 54 fL", timesItalica));
			 * obsVL.setColspan(2); obsVL.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 * obsVL.setHorizontalAlignment(Element.ALIGN_LEFT); obsVL.setBorderWidth(0);
			 * table.addCell(obsVL);
			 */
//--------------------------------------------------------------------------

			PdfPCell emailCell = new PdfPCell(new Phrase(usuario.getEmail(), dadosFont));
			emailCell.setPadding(4);
			emailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			emailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(emailCell);
			// }
			// }
			document.add(tableCima);
			document.add(table);

			document.close();

		} catch (DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());

	}

}
