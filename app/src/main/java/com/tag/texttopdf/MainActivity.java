package com.tag.texttopdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final Font CAT_BOLD_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static final Font CAT_NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL);
	private static final Font LARGE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
	private static final Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD
			| Font.UNDERLINE, BaseColor.GRAY);
	private static final Font MEDIUM_BOLD_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
	private static final Font MEDIUM_NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL);
	private static final Font SMALL_BOLD_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static final Font SMALL_NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

	private static final Font HEADING_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String FILE = Environment.getExternalStorageDirectory().toString()
				+ "/PDF/" + "Name.pdf";

		// Add Permission into Manifest.xml
		// <uses-permission
		// android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

		// Create New Blank Document
		Document document = new Document(PageSize.A4);

		// Create Directory in External Storage
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/PDF");
		myDir.mkdirs();

		// Create Pdf Writer for Writting into New Created Document
		try {
			PdfWriter.getInstance(document, new FileOutputStream(FILE));

			// Open Document for Writting into document
			document.open();

			// User Define Method
			addMetaData(document);
//			addTitlePage(document);
			addBodyPart(document);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Close Document after writting all content
		document.close();

		Toast.makeText(this, "PDF File is Created. Location : " + FILE,
				Toast.LENGTH_LONG).show();
	}

	// Set PDF document Properties
	public void addMetaData(Document document)

	{
		document.addTitle("RESUME");
		document.addSubject("Person Info");
		document.addKeywords("Personal,	Education, Skills");
		document.addAuthor("TAG");
		document.addCreator("TAG");
	}

	public void addTitlePage(Document document) throws DocumentException {

		// Start New Paragraph
		Paragraph prHead = new Paragraph();
		// Set Font in this Paragraph
		prHead.setFont(TITLE_FONT);
		// Add item into Paragraph
		prHead.add("RESUME – Name\n");

		// Create Table into Document with 1 Row
		PdfPTable myTable = new PdfPTable(1);
		// 100.0f mean width of table is same as Document size
		myTable.setWidthPercentage(100.0f);

		// Create New Cell into Table
		PdfPCell myCell = new PdfPCell(new Paragraph(""));
		myCell.setBorder(Rectangle.BOTTOM);

		// Add Cell into Table
		myTable.addCell(myCell);

		prHead.setFont(CAT_BOLD_FONT);
		prHead.add("\nName1 Name2\n");
		prHead.setAlignment(Element.ALIGN_CENTER);

		// Add all above details into Document
		document.add(prHead);
		document.add(myTable);

		document.add(myTable);

		// Now Start another New Paragraph
		Paragraph prPersinalInfo = new Paragraph();
		prPersinalInfo.setFont(SMALL_BOLD_FONT);
		prPersinalInfo.add("Address 1\n");
		prPersinalInfo.add("Address 2\n");
		prPersinalInfo.add("City: SanFran. State: CA\n");
		prPersinalInfo.add("Country: USA Zip Code: 000001\n");
		prPersinalInfo
				.add("Mobile: 9999999999 Fax: 1111111 Email: john_pit@gmail.com \n");

		prPersinalInfo.setAlignment(Element.ALIGN_CENTER);

		document.add(prPersinalInfo);
		document.add(myTable);

		document.add(myTable);

		Paragraph prProfile = new Paragraph();
		prProfile.setFont(SMALL_BOLD_FONT);
		prProfile.add("\n \n Profile : \n ");
		prProfile.setFont(SMALL_NORMAL_FONT);
		prProfile
				.add("\nI am Mr. XYZ. I am Android Application Developer at TAG.");

		prProfile.setFont(SMALL_BOLD_FONT);
		document.add(prProfile);

		// Create new Page in PDF
		document.newPage();
	}

	void addBodyPart(Document document) throws DocumentException{

		Paragraph prHead = new Paragraph();
		prHead.setFont(LARGE_FONT);
		prHead.setAlignment(Element.ALIGN_LEFT);
		prHead.add("Shubham Chauhan");
		prHead.setFont(CAT_NORMAL_FONT);
		prHead.add("\ngurugram haryana");
		prHead.add("\nCollege of engineering roorkee");
		prHead.add("\nshubham.chauhan@kelltontech.com");
		prHead.add("\n9927175628\n\n");
		document.add(prHead);

		Paragraph objective = new Paragraph("CAREER OBJECTIVE",LARGE_FONT);
		objective.setAlignment(Element.ALIGN_LEFT);
		PdfPTable pdfPTable=new PdfPTable(1);
		pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell pdfPCell=new PdfPCell();
		pdfPCell.addElement(objective);
		pdfPCell.setBackgroundColor(BaseColor.GRAY);
		pdfPTable.addCell(pdfPCell);
		document.add(pdfPTable);

		Paragraph objectiveText = new Paragraph();
		objectiveText.setFont(MEDIUM_NORMAL_FONT);
		objectiveText.setAlignment(Element.ALIGN_LEFT);
		objectiveText.add("\nTo obtain a position within organization that will allow me to utilize my skills ,experience and willingness.\n\n");
		document.add(objectiveText);

		Paragraph acadmicHeading = new Paragraph("BASIC ACADEMIC CREDENTIALS",LARGE_FONT);
		acadmicHeading.setAlignment(Element.ALIGN_LEFT);
		PdfPTable tableAcadmicHeading=new PdfPTable(1);
		tableAcadmicHeading.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell cellAcadmicHeading=new PdfPCell();
		cellAcadmicHeading.addElement(acadmicHeading);
		cellAcadmicHeading.setBackgroundColor(BaseColor.GRAY);
		tableAcadmicHeading.addCell(cellAcadmicHeading);
		document.add(tableAcadmicHeading);

		document.add(new Paragraph("\n"));

		PdfPTable tableAcadmic=new PdfPTable(4);
		tableAcadmic.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell cellAcadmic=new PdfPCell(new Phrase("Qualification",MEDIUM_BOLD_FONT));
		cellAcadmic.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("Board/University",MEDIUM_BOLD_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("Year",MEDIUM_BOLD_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("Percentage",MEDIUM_BOLD_FONT));
		tableAcadmic.addCell(cellAcadmic);

		cellAcadmic=new PdfPCell(new Phrase("10th",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("Jawahar Navodaya Vidyalaya",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("2011",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("91.2%",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);

		cellAcadmic=new PdfPCell(new Phrase("12th",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("Jawahar Navodaya Vidyalaya",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("2013",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("81.4%",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);

		cellAcadmic=new PdfPCell(new Phrase("B.tech",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("College Of Engineering Roorkee",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("2017",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);
		cellAcadmic=new PdfPCell(new Phrase("79%",SMALL_NORMAL_FONT));
		tableAcadmic.addCell(cellAcadmic);

		document.add(tableAcadmic);

	}
}
