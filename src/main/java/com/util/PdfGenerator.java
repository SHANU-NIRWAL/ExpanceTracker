package com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.bean.ExpenseBean;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator {
	
	public static ByteArrayInputStream generateExpense(List<ExpenseBean>expenseDetails)
	{
		 Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        
	        
	        
	        try {

	            PdfPTable table = new PdfPTable(9);
	            table.setWidthPercentage(90);
	            table.setWidths(new int[]{3, 4, 4,4,4,4,3,4,4});

	            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

	            PdfPCell hcell;
	            hcell = new PdfPCell(new Phrase("Id", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Payee Name", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Amount", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	            hcell = new PdfPCell(new Phrase("Time", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Date", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	            hcell = new PdfPCell(new Phrase("Description", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Account Type", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	            hcell = new PdfPCell(new Phrase("Category", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("SubCategory", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            for (ExpenseBean expbean : expenseDetails) {

	                PdfPCell cell;

	                cell = new PdfPCell(new Phrase(String.valueOf(expbean.getExpenseId())));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(expbean.getPayeeName()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(String.valueOf(expbean.getAmmount())));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(expbean.getTimeexp()));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(expbean.getDateexp().toString()));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(expbean.getDescription()));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(expbean.getAccountName().getName()));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(expbean.getCategorydatalist()));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(expbean.getSubcategorydatalist()));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	            }

	            PdfWriter.getInstance(document, out);
	            document.open();
	            document.add(table);

	            document.close();

	        } catch (DocumentException ex) {

	          System.out.println("error"+ex);
	        }

	        return new ByteArrayInputStream(out.toByteArray());
	    }
	
}
